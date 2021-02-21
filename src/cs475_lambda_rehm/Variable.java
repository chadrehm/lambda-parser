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

	/**
	 * Getter for Variable name
	 * 
	 * @return
	 */
	public char getName() {
		return name;
	}

	/**
	 * Copy Variable
	 * 
	 * @return
	 */
	@Override
	public String copy() {
		return this.toString();
	}

	/**
	 * Substitute Variable
	 * 
	 * @param var
	 * @param value
	 * @return
	 */
	@Override
	public LambdaExpr substitute(Variable var, LambdaExpr value) {
		return var == null ? value : var;
	}

	/**
	 * Type of Lambda expression component
	 * 
	 * @return
	 */
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
