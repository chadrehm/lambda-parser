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
	
	
}
