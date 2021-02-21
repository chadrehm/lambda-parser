/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin this class run a lambda simulation
 */
package cs475_lambda_rehm;

public class Simulator {
	private int loopCount = 0;
	
	/**
	 * Simulate Lambda expression.  Run Beta Reductions where needed.
 	 * 
	 * @param expr
	 * @return
	 * @throws DivergentException
	 */
	public LambdaExpr betaReduce(LambdaExpr expr) throws DivergentException{
		
		// Expressions that are Variables or Abstractions do not need to be reduced
		if (expr.type() == ExprKind.APPLICATION) {
			Application application = (Application)expr;
			LambdaExpr operand1 = application.getOperand1();
			LambdaExpr operand2 = application.getOperand2();
			
			// A b-reduction is needed when the operand 1 is an abstraction
			if (operand1.type() == ExprKind.ABSTRACTION) {
				if (operand2.type() == ExprKind.VARIABLE) {
					expr = operand1.substitute((Variable)operand2, null);
				} else if (operand2.type() == ExprKind.ABSTRACTION) {
					String copyExpr = expr.copy();
					expr = operand1.substitute(null, (Abstraction)operand2);
					
					loopCount++;
					// Check for loop
					if (expr.toString().equals(copyExpr) || loopCount > 25) {								
						throw new DivergentException("The function is divergent.");
					}
					
					// If the result of the b-reduction is another abstraction continue to
					// reduce
					if (operand1.type() == ExprKind.ABSTRACTION) {
						expr = betaReduce(expr);
					}
				}
			}
			
			// Loop to the bottom of the tree.
			if (operand1.type() == ExprKind.APPLICATION) {
				((Application)expr).setOperand1(betaReduce(operand1));
			}
		} 
		
		// Last catch.
		if (expr.type() == ExprKind.APPLICATION){
			if(((Application)expr).getOperand1().type() == ExprKind.ABSTRACTION) {
				expr = betaReduce(expr);
			}
		}
		
		return expr;
	}
}
