/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin  This class represents a lambda variable
 */
package cs475_lambda_rehm;

public class Variable implements LambdaExpr {
	private char name;
	
	Variable(char name) {
		this.name = name;
	}

	public char getName() {
		return name;
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
		return ExprKind.VARIABLE;
	}
	
	@Override
	public String toString() {
		return Character.toString(name);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Variable)) { 
			return false; 
		}
		return name == ((Variable)o).getName();
	}
	
	@Override
	public int hashCode () {
			return (int) this.name;
	}
}
