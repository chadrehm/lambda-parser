/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin  This class represents a lambda application
 * 
 */
package cs475_lambda_rehm;

public class Application implements LambdaExpr{
	private LambdaExpr operand1;

	public void setOperand1(LambdaExpr operand1) {
		this.operand1 = operand1;
	}

	public void setOperand2(LambdaExpr operand2) {
		this.operand2 = operand2;
	}
	private LambdaExpr operand2;

	public LambdaExpr getOperand1() {
		return operand1;
	}

	public LambdaExpr getOperand2() {
		return operand2;
	}

	@Override
	public String copy() {
		return this.toString();
	}

	@Override
	public LambdaExpr substitute(Variable var, LambdaExpr value) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ExprKind type() {
		return ExprKind.APPLICATION;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", operand1.toString(), operand2.toString());
	}
}
