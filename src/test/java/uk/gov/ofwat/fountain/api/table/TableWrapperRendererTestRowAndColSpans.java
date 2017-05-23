/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package uk.gov.ofwat.fountain.api.table;

import junit.framework.TestCase;
import org.junit.Assert;
import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.api.table.reader.SectionLayout;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.FormEmptyCell;


public class TableWrapperRendererTestRowAndColSpans extends TestCase {

	private FormDisplayCell[][] formDisplayGrid;
	SectionLayout layout;

	public void setUp() {
		formDisplayGrid = getFormDisplayCell();
		layout = new SectionLayout(formDisplayGrid, formDisplayGrid.length, formDisplayGrid[0].length);
	}


	public void testFormDisplayCell() {

		String regressionString = "\n" +
		" C                    \n" +
		" R R R             v v\n" +
		" R R R             v v\n" +
		" R R R v v v v v v v v\n" +
		" R R R             v v\n" +
		" R R R             v v";
//		|C3  |~   |~   | 8  |~   |~   |~   |~   |~   |~   |~
//		|R   |R   |R   | 62 |~   |~   |~   |~   |~   X   X
//		|R   |R   |R   |~   |~   |~   |~   |~   |~   X   X
//		|R   |R   |R   |v   |v   |v   |v   |v   |v   |v   |v
//		|R   |R   |R   | 62 |~   |~   |~   |~   |~   |v   |v
//		|R   |R   |R   |~   |~   |~   |~   |~   |~   |v   |v

		Assert.assertEquals(regressionString, layout.printFormDisplayGrid());
	}

	public void testMarkColAndRowSpannedCellsAsRedundentNewTest() {
		layout.setRowAndColOnEmptyCells();
		layout.initialiseCellLayout();

		int row = 0;
		int col = 3;
		int rowCount = row;
		FormDisplayCell formDisplayCell = formDisplayGrid[rowCount][col];
		layout.setSpansOnEmptyCell(rowCount, col, formDisplayCell);
		layout.markSpannedCellsAsRedundent(rowCount, col, formDisplayCell);
		layout.setCellLayoutCode(rowCount, col, formDisplayCell);

		rowCount++;
		if (!layout.skipRedundantCell(rowCount, col, formDisplayCell)) {
			formDisplayCell = formDisplayGrid[rowCount][col];
			layout.setSpansOnEmptyCell(rowCount, col, formDisplayCell);
			layout.markSpannedCellsAsRedundent(rowCount, col, formDisplayCell);
			layout.setCellLayoutCode(rowCount, col, formDisplayCell);
		}

		rowCount++;
		if (!layout.skipRedundantCell(rowCount, col, formDisplayCell)) {
			formDisplayCell = formDisplayGrid[rowCount][col];
			layout.setSpansOnEmptyCell(rowCount, col, formDisplayCell);
			layout.markSpannedCellsAsRedundent(rowCount, col, formDisplayCell);
			layout.setCellLayoutCode(rowCount, col, formDisplayCell);
		}

//		Assert.assertEquals("row: " + (row) + " col: " + col, "| 8 ", layout.setCellLayoutCode(row, col, formDisplayCell));
		Assert.assertEquals("row: " + (row) + " col: " + col, "| 8 ", layout.getCellLayout()[row][col]);
		Assert.assertEquals("row: " + (row+1) + " col: " + col, "| 62", layout.getCellLayout()[row+1][col]);
		Assert.assertEquals("row: " + (row+2) + " col: " + col, "|~  ", layout.getCellLayout()[row+2][col]);
		for (int i=row; i<1; i++) {
			for (int j=col+1; j<11 ; j++) {
				Assert.assertEquals("row: " + i + " col: " + j, "|~  ", layout.getCellLayout()[i][j]);
			}
		}
	}



	private FormDisplayCell[][] getFormDisplayCell(){
		FormDisplayCell[][] formDisplayGrid = new FormDisplayCell[6][11];
		FormDisplayCell formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(3);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.COL_HEADING);
		formDisplayGrid[0][0] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][1] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][2] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][3] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][4] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][5] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][6] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][7] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][8] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][9] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[0][10] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(1);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[1][0] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(1);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(1);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[1][1] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(2);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(1);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[1][2] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[1][3] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[1][4] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[1][5] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[1][6] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[1][7] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[1][8] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(9);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(1);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[1][9] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(10);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(1);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[1][10] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(2);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[2][0] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(1);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(2);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[2][1] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(2);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(2);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[2][2] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[2][3] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[2][4] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[2][5] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[2][6] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[2][7] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[2][8] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(9);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(2);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[2][9] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(10);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(2);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[2][10] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[3][0] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(1);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[3][1] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(2);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[3][2] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(3);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.COPYCELL);
		formDisplayGrid[3][3] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(4);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.COPYCELL);
		formDisplayGrid[3][4] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(5);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.COPYCELL);
		formDisplayGrid[3][5] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(6);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.COPYCELL);
		formDisplayGrid[3][6] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(7);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.COPYCELL);
		formDisplayGrid[3][7] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(8);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.COPYCELL);
		formDisplayGrid[3][8] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(9);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[3][9] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(10);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(3);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[3][10] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(4);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[4][0] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(1);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(4);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[4][1] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(2);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(4);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[4][2] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[4][3] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[4][4] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[4][5] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[4][6] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[4][7] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[4][8] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(9);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(4);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[4][9] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(10);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(4);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[4][10] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(5);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[5][0] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(1);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(5);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[5][1] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(2);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(5);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.ROW_HEADING);
		formDisplayGrid[5][2] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[5][3] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[5][4] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[5][5] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[5][6] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[5][7] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(0);
		formDisplayCell.setColumnSpan(0);
		formDisplayCell.setRow(0);
		formDisplayCell.setRowSpan(0);
		formDisplayCell.setCellType(CellType.EMPTY);
		formDisplayGrid[5][8] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(9);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(5);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[5][9] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();
		formDisplayCell.setColumn(10);
		formDisplayCell.setColumnSpan(1);
		formDisplayCell.setRow(5);
		formDisplayCell.setRowSpan(1);
		formDisplayCell.setCellType(CellType.INPUT);
		formDisplayGrid[5][10] = formDisplayCell;
		formDisplayCell = new FormEmptyCell();

		return formDisplayGrid;
	}


}

