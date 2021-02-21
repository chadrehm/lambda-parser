/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin This class represents a lambda abstraction
 */
package cs475_lambda_rehm;

import java.util.ArrayList;
import java.util.Collections;

public class Abstraction implements LambdaExpr{
	private Variable boundVar;
	private LambdaExpr body;

	/**
	 * Setter for bound variable 
	 * 
	 * @param boundVar
	 */
	public void setBoundVar(Variable boundVar) {
		this.boundVar = boundVar;
	}
	
	/**
	 * Create a tree structure that represents the body of the abstraction
	 * 
	 * @param body
	 * @param root
	 * @param i
	 * @return
	 */
	protected Application buildBody(LambdaExpr[] body, Application root, int i) {
		if (1 < i) {
			Application temp;
			temp = buildBody(body, new Application(), i - 1);
			root.setOperand2(body[i]);
			root.setOperand1(temp);
		} else {
			root.setOperand1(body[i - 1]);
			root.setOperand2(body[i]);
		}
		return root;
	} 

	/**
	 * Setter for abstraction body
	 * 
	 * @param body
	 */
	public void setBody(LambdaExpr[] body) {
		if(body.length > 1) {
			this.body = buildBody(body, new Application(), body.length - 1);
		} else {
			this.body = body[0];
		}
	}

	/**
	 * Getter for abstraction bounded variable
	 * 
	 * @return
	 */
	public Variable getBoundVar() {
		return boundVar;
	}

	/**
	 *
	 * @return
	 */
	public LambdaExpr getBody() {
		return body;
	}

	/**
	 * Copy abstraction
	 * 
	 * @return
	 */
	@Override
	public String copy() {
		return this.toString();
	}
	
	/**
	 *	Convert tree to an Array list.  Used by substitution to replace bounded variable
	 * 
	 * @param app
	 * @param lambdaList
	 * @return
	 */
	protected ArrayList<LambdaExpr> flattenBody(Application app, ArrayList<LambdaExpr> lambdaList) {
		if (app.getOperand1().type() == ExprKind.APPLICATION) {
			lambdaList = flattenBody((Application)app.getOperand1(), lambdaList);
			
			if (app.getOperand2().type() == ExprKind.VARIABLE){
				lambdaList.add((Variable)app.getOperand2());
			}
		} else {
			if (app.getOperand1().type() == ExprKind.VARIABLE){
				lambdaList.add((Variable)app.getOperand1());
			}
			if (app.getOperand2().type() == ExprKind.VARIABLE){
				lambdaList.add((Variable)app.getOperand2());
			}
		}
		return lambdaList;
	}

	/**
	 * Substitute provided term for bounded variable
	 * 
	 * @param var
	 * @param value
	 * @return
	 */
	@Override
	public LambdaExpr substitute(Variable var, LambdaExpr value) {
		LambdaExpr lambdaExpr = null;
		
		if (body.type() == ExprKind.VARIABLE) {
			// This is the identity function
			if (boundVar.getName() == ((Variable)body).getName()) {
				lambdaExpr = var == null ? value : var;
			}
			// This is the constant function
			else {
				lambdaExpr = body;
			}
		} else {
			if (body.type() == ExprKind.APPLICATION) {
				// Replace bound variable with new term
				ArrayList<LambdaExpr> lambdaList = 
					flattenBody((Application)body, new ArrayList<>());
				Collections.replaceAll(lambdaList, boundVar, var == null ? value : var);
				LambdaExpr[] bodyArr = new LambdaExpr[lambdaList.size()];
		
				// Transition ArrayList values to Array
				for (int i = 0; i < lambdaList.size(); i++) {
					bodyArr[i] = lambdaList.get(i);
				}
				lambdaExpr = buildBody(bodyArr, new Application(), lambdaList.size() - 1);
			}
		}
		
		return lambdaExpr;
	}

	/**
	 * Type of Lambda expression component
	 * 
	 * @return
	 */
	@Override
	public ExprKind type() {
		return ExprKind.ABSTRACTION;
	}
	
	/**
	 * Flatten the body tree structure into a string
	 * 
	 * @param app
	 * @param str
	 * @return
	 */
	protected StringBuilder flattenBody(Application app, StringBuilder str) {
		if (app.getOperand1().type() == ExprKind.APPLICATION) {
			str = flattenBody((Application)app.getOperand1(), str);
			
			str.append(String.format("%s ",app.getOperand2().toString()));
		} else {
			str.append(String.format("%s %s ",
				app.getOperand1().toString(), app.getOperand2().toString()));
		}
		return str;
	}
	
	@Override
	public String toString() {
		 StringBuilder str = new StringBuilder(); 
		 str.append(String.format("L%s. ", boundVar.toString()));
		
		if (body.type() == ExprKind.APPLICATION) {
			str = flattenBody((Application)body, str);
		} else if (body.type() == ExprKind.VARIABLE) {
			str.append(body.toString());
		}
		return String.format("(%s)", str.toString().trim());
	}
}
