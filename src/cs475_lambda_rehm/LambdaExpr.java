/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin this interface describes the lambda class contract
 */
package cs475_lambda_rehm;

public interface LambdaExpr {
	public String copy();
	public LambdaExpr substitute(Variable var, LambdaExpr value);
	public ExprKind type();
}
