/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin this class run a lambda simulation
 */
package cs475_lambda_rehm;

public class Simulator {
	public LambdaExpr betaReduce(LambdaExpr expr) {
		
		if (expr.type() == ExprKind.APPLICATION) {
			Application application = (Application)expr;
			LambdaExpr operand1 = application.getOperand1();
			LambdaExpr operand2 = application.getOperand2();
			
			if (operand1.type() == ExprKind.ABSTRACTION) {
				if (operand2.type() == ExprKind.VARIABLE) {
					expr = operand1.substitute((Variable)operand2, null);
				} else if (operand2.type() == ExprKind.ABSTRACTION) {
					String copyExpr = expr.copy();
					expr = operand1.substitute(null, (Abstraction)operand2);
					
					// Check for loop
					if (copyExpr.equals(expr.toString())) {
						return expr;
					}
					if (operand1.type() == ExprKind.ABSTRACTION) {
						expr = betaReduce(expr);
					}
				}
			}
		} 
		
		return expr;
	}
}
