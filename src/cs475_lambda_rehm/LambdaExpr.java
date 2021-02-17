package cs475_lambda_rehm;

public interface LambdaExpr {
	public String copy();
	public LambdaExpr substitute(Variable var, LambdaExpr value);
	public ExprKind type();
}
