/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin  This class represents a lambda application
 * 
 */
package cs475_lambda_rehm;

public class Application implements LambdaExpr{
	private LambdaExpr operand1;

	/**
	 * Setter for operand one
	 * 
	 * @param operand1
	 */
	public void setOperand1(LambdaExpr operand1) {
		this.operand1 = operand1;
	}

	/**
	 * Setter for operand two
	 * 
	 * @param operand2
	 */
	public void setOperand2(LambdaExpr operand2) {
		this.operand2 = operand2;
	}
	private LambdaExpr operand2;

	/**
	 * Getter for operand one
	 * 
	 * @return
	 */
	public LambdaExpr getOperand1() {
		return operand1;
	}

	/**
	 * Getter for operand two
	 * 
	 * @return
	 */
	public LambdaExpr getOperand2() {
		return operand2;
	}

	/**
	 * Copy Application
	 * 
	 * @return
	 */
	@Override
	public String copy() {
		return this.toString();
	}

	/**
	 * Substitute application
	 * 
	 * @param var
	 * @param value
	 * @return
	 */
	@Override
	public LambdaExpr substitute(Variable var, LambdaExpr value) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	/**
	 * Type of Lambda expression component
	 * 
	 * @return
	 */
	@Override
	public ExprKind type() {
		return ExprKind.APPLICATION;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", operand1.toString(), operand2.toString());
	}
}
