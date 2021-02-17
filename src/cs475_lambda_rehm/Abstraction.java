package cs475_lambda_rehm;

public class Abstraction implements LambdaExpr{
	private Variable boundVar;
	private String body;

	public void setBoundVar(Variable boundVar) {
		this.boundVar = boundVar;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Variable getBoundVar() {
		return boundVar;
	}

	public String getBody() {
		return body;
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
		return ExprKind.ABSTRACTION;
	}
	
}
