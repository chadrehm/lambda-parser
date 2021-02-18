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
public class Simulator {
	public LambdaExpr betaReduce(LambdaExpr expr) {
		
		if (expr.type() == ExprKind.APPLICATION) {
			Application application = (Application)expr;
			LambdaExpr operand1 = application.getOperand1();
			LambdaExpr operand2 = application.getOperand2();
			
			if (operand1.type() == ExprKind.ABSTRACTION && operand1.type() == ExprKind.VARIABLE) {
				operand1.substitute((Variable)operand2, null);
			}
		} 
		
		return expr;
	}
}
