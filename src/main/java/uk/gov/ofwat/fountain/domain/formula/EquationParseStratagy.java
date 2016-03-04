package uk.gov.ofwat.fountain.domain.formula;

public interface EquationParseStratagy {

	void processOperatorToken(FormulaContext context, OperatorToken operatorToken);
	void processCodeToken(FormulaContext context, CodeToken codeToken);
}
