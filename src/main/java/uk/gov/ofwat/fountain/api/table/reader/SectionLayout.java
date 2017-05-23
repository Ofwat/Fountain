package uk.gov.ofwat.fountain.api.table.reader;

import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;

/**
 * Created by Adam Edgar on 17/03/2017.
 */
public class SectionLayout {

    private FormDisplayCell[][] formDisplayGrid;
    private int sectionRows;
    private int sectionCols;
    private String[][] cellLayout;


    public SectionLayout(FormDisplayCell[][] formDisplayGrid, int sectionRows, int sectionCols) {
        this.formDisplayGrid = formDisplayGrid;
        this.sectionRows = sectionRows;
        this.sectionCols = sectionCols;
    }

    public String[][] getCellLayout() {
        if (null == cellLayout) {
            return makeCellLayout();
        }
        return cellLayout;
    }

    public String[][] makeCellLayout() {
        makeTestData();
        printFormDisplayGrid();
        setRowAndColOnEmptyCells();
        initialiseCellLayout();
        processSection();
        printSectionLayout();
        return cellLayout;
    }

    public void processSection() {
        for (int rowIndex=0; rowIndex<sectionRows; rowIndex++ ) {
            processRow(rowIndex);
        }
    }

    public void processRow(int rowIndex) {
        for (int colIndex=0; colIndex<sectionCols; colIndex++ ) {
            processCell(rowIndex, colIndex);
        }
    }

    public void processCell(int rowIndex, int colIndex) {
        FormDisplayCell formDisplayCell = formDisplayGrid[rowIndex][colIndex];
        if (skipRedundantCell(rowIndex, colIndex, formDisplayCell)) {
            return;
        }
        setSpansOnEmptyCell(rowIndex, colIndex, formDisplayCell);
        markSpannedCellsAsRedundent(rowIndex, colIndex, formDisplayCell);
        setCellLayoutCode(rowIndex, colIndex, formDisplayCell);
    }

    public String setCellLayoutCode(int rowIndex, int colIndex, FormDisplayCell formDisplayCell) {
        String colSpanPrint = " ";
        if (formDisplayCell.getColumnSpan() != 1) {
            colSpanPrint = "" + formDisplayCell.getColumnSpan();
        }
        String rowSpanPrint = " ";
        if (formDisplayCell.getRowSpan() != 1) {
            rowSpanPrint = "" + formDisplayCell.getRowSpan();
        }
        CellType cellType = formDisplayCell.getCellType();
        switch (cellType) {
            case CALC:
                cellLayout[rowIndex][colIndex] = "|c" + colSpanPrint + rowSpanPrint;
                break;
            case COPYCELL:
            case INPUT:
                cellLayout[rowIndex][colIndex] = "|v" + colSpanPrint + rowSpanPrint;
                break;
            case COL_HEADING:
                cellLayout[rowIndex][colIndex] = "|C" + colSpanPrint + rowSpanPrint;
                break;
            case ROW_HEADING:
            case ROW_HEADING_NON_REPEAT:
            case GROUP_ROW_HEADING:
                cellLayout[rowIndex][colIndex] = "|R" + colSpanPrint + rowSpanPrint;
                break;
            case EMPTY:
                cellLayout[rowIndex][colIndex] = "| " + colSpanPrint + rowSpanPrint;
                break;
        }
        return cellLayout[rowIndex][colIndex];
    }

    public void markSpannedCellsAsRedundent(int rowIndex, int colIndex, FormDisplayCell formDisplayCell) {
        markFirstColAsRedundent(rowIndex, colIndex, formDisplayCell);
        markRowsAsRedundent(rowIndex, colIndex, formDisplayCell);
    }

    private void markRowsAsRedundent(int rowIndex, int colIndex, FormDisplayCell formDisplayCell) {
        for (int colsSpanned=1; colsSpanned < formDisplayCell.getColumnSpan(); colsSpanned++) {
            markRowAsRedundent(rowIndex, colIndex, formDisplayCell, colsSpanned);
        }
    }

    private void markRowAsRedundent(int rowIndex, int colIndex, FormDisplayCell formDisplayCell, int colsSpanned) {
        markCellAsRedundent(rowIndex, colIndex+colsSpanned);
        markColAsRedundent(rowIndex, colIndex+colsSpanned, formDisplayCell);
    }

    private void markFirstColAsRedundent(int rowIndex, int colIndex, FormDisplayCell formDisplayCell) {
        markColAsRedundent(rowIndex, colIndex, formDisplayCell);
    }

    private void markColAsRedundent(int rowIndex, int colIndex, FormDisplayCell formDisplayCell) {
        for (int rowsSpanned=1; rowsSpanned < formDisplayCell.getRowSpan(); rowsSpanned++) {
            markCellAsRedundent(rowIndex+rowsSpanned, colIndex);
        }
    }

    private void markCellAsRedundent(int rowIndex, int colIndex) {
        cellLayout[rowIndex][colIndex] = "|~  ";
    }

    public void setSpansOnEmptyCell(int rowIndex, int colOffset, FormDisplayCell formDisplayCell) {
        if (formDisplayCell.getCellType().equals(CellType.EMPTY)) {
            int colSpanCount=0;
            int rowSpanCount=Integer.MAX_VALUE;
            try {
                while(  ((colOffset + colSpanCount) < sectionCols) &&
                        (formDisplayGrid[rowIndex][colOffset + colSpanCount].getCellType().equals(CellType.EMPTY)) &&
                        !(skipRedundantCell(rowIndex, colOffset + colSpanCount, formDisplayCell))) {
                    int currentCellRowSpan = getRowSpanForEmptyCell(formDisplayGrid[rowIndex][colOffset + colSpanCount], formDisplayGrid, rowIndex, colOffset + colSpanCount, sectionRows);
                    if (currentCellRowSpan < rowSpanCount) {
                        // Row span needs to be the smallest we find in the row.
                        rowSpanCount = currentCellRowSpan;
                    }
                    colSpanCount++;
                }
            } catch (Exception e) {
                System.out.println("rowIndex " + rowIndex);
                System.out.println("colOffset " + colOffset);
                System.out.println("colSpanCount " + colSpanCount);
                System.out.println("colOffset + colSpanCount " + (colOffset + colSpanCount));
            }
            formDisplayCell.setColumnSpan(colSpanCount);
            formDisplayCell.setRowSpan(rowSpanCount);
        }
    }

    public int getRowSpanForEmptyCell(FormDisplayCell formDisplayCell, FormDisplayCell[][] formDisplayGrid, int rowOffset, int colIndex, int sectionRows) {
        int rowSpanCount=0;
        try {
            while(  ((rowOffset + rowSpanCount) < sectionRows) &&
                    (formDisplayGrid[rowOffset + rowSpanCount][colIndex].getCellType().equals(CellType.EMPTY)) &&
                    (!skipRedundantCell(rowOffset + rowSpanCount, colIndex, formDisplayCell))) {
                rowSpanCount++;
            }
        } catch (Exception e) {
            System.out.println("colIndex " + colIndex);
            System.out.println("rowOffset " + rowOffset);
            System.out.println("rowSpanCount " + rowSpanCount);
            System.out.println("rowOffset + rowSpanCount " + (rowOffset + rowSpanCount));
        }
        return rowSpanCount;
    }

    public boolean skipRedundantCell(int rowIndex, int colIndex, FormDisplayCell formDisplayCell) {
        if (cellLayout[rowIndex][colIndex].equals("|~  ")) {
            if (!formDisplayCell.getCellType().equals(CellType.EMPTY)) {
                // conflict if cell type is not EMPTY
                System.out.println("Cell in conflict row: " + rowIndex + " col: " +colIndex);
                cellLayout[rowIndex][colIndex] = "X  ";
            }
            return true;
        }
        return false;
    }

    public void setRowAndColOnEmptyCells() {
        for (int gridRowIndex=0; gridRowIndex<sectionRows; gridRowIndex++ ) {
            for (int gridColIndex = 0; gridColIndex < sectionCols; gridColIndex++) {
                FormDisplayCell formDisplayCell = formDisplayGrid[gridRowIndex][gridColIndex];
                if (formDisplayCell.getCellType().equals(CellType.EMPTY)) {
                    formDisplayCell.setRow(gridRowIndex);
                    formDisplayCell.setColumn(gridColIndex);
                }
            }
        }
    }

    public void initialiseCellLayout() {
        cellLayout = new String[sectionRows][sectionCols];
        // Initialise
        for (int gridRowIndex=0; gridRowIndex<sectionRows; gridRowIndex++ ) {
            for (int gridColIndex=0; gridColIndex<sectionCols; gridColIndex++ ) {
                cellLayout[gridRowIndex][gridColIndex] = "u";
            }
        }
    }

    public void makeTestData() {
        for (int rowIndex=0; rowIndex < sectionRows; rowIndex++ ) {
            for (int colIndex = 0; colIndex < sectionCols; colIndex++) {
                FormDisplayCell formDisplayCell = formDisplayGrid[rowIndex][colIndex];
                System.out.println("formDisplayCell.setColumn(" + formDisplayCell.getColumn() + ");");
                System.out.println("formDisplayCell.setColumnSpan(" + formDisplayCell.getColumnSpan() + ");");
                System.out.println("formDisplayCell.setRow(" + formDisplayCell.getRow() + ");");
                System.out.println("formDisplayCell.setRowSpan(" + formDisplayCell.getRowSpan() + ");");
                System.out.println("formDisplayCell.setCellType(CellType." + formDisplayCell.getCellType() + ");");
                System.out.println("formDisplayGrid[" + rowIndex + "][" + colIndex +"] = formDisplayCell;");
                System.out.println("formDisplayCell = new FormEmptyCell();");
            }
        }
    }

    public String printFormDisplayGrid() {
        String grid = "";
        // Print out the FormDisplayCell as received
        String calc = " " + "c";
        String value = " " + "v";
        String colHeading = " " + "C";
        String rowHeading = " " + "R";
        String empty = " " + " ";
        for (int gridRowIndex=0; gridRowIndex<sectionRows; gridRowIndex++ ) {
            System.out.println("");
            grid = grid + "\n";
            for (int gridColIndex=0; gridColIndex<sectionCols; gridColIndex++ ) {
                FormDisplayCell formDisplayCell = formDisplayGrid[gridRowIndex][gridColIndex];
                if (null == formDisplayCell) {
                    continue;
                }
                switch (formDisplayCell.getCellType()) {
                    case CALC:
                        System.out.print(calc);
                        grid = grid + calc;
                        break;
                    case COPYCELL:
                    case INPUT:
                        System.out.print(value);
                        grid = grid + value;
                        break;
                    case COL_HEADING:
                        System.out.print(colHeading);
                        grid = grid + colHeading;
                        break;
                    case ROW_HEADING:
                    case ROW_HEADING_NON_REPEAT:
                    case GROUP_ROW_HEADING:
                        System.out.print(rowHeading);
                        grid = grid + rowHeading;
                        break;
                    case EMPTY:
                        System.out.print(empty);
                        grid = grid + empty;
                        break;
                }
            }
        }
        return grid;
    }

    public void printSectionLayout() {
        for (int rowIndex=0; rowIndex<sectionRows; rowIndex++ ) {
            System.out.println("");
            for (int colIndex=0; colIndex<sectionCols; colIndex++ ) {
                System.out.print(" " + cellLayout[rowIndex][colIndex]);
            }
        }
        System.out.println("");
    }

}
