/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_lambda_rehm;

/**
 *
 * @author Chad Rehm
 */
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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public LambdaExpr substitute(Variable var, LambdaExpr value) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ExprKind type() {
		return ExprKind.APPLICATION;
	}
	
}
