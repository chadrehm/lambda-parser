/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin  This class represents a lambda application
 * 
 */
package cs475_lambda_rehm;

public class Application implements LambdaExpr{
	private LambdaExpr operand1;

	public void setOperand1(LambdaExpr operand1) {
		this.operand1 = operand1;
	}

	public void setOperand2(LambdaExpr operand2) {
		this.operand2 = operand2;
	}
	private LambdaExpr operand2;

	public LambdaExpr getOperand1() {
		return operand1;
	}

	public LambdaExpr getOperand2() {
		return operand2;
	}

	@Override
	public String copy() {
		return this.toString();
	}

	@Override
	public LambdaExpr substitute(Variable var, LambdaExpr value) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public ExprKind type() {
		return ExprKind.APPLICATION;
	}
	
	protected StringBuilder flattenBody(Application app, StringBuilder str) {
		if (app.getOperand1().type() == ExprKind.APPLICATION) {
			str = flattenBody((Application)app.getOperand1(), str);
			
			if (app.getOperand2().type() == ExprKind.VARIABLE){
				str.append(String.format("%s ",((Variable)app.getOperand2()).toString()));
			}
		} else {
			if (app.getOperand1().type() == ExprKind.VARIABLE){
				str.append(String.format("%s ",((Variable)app.getOperand1()).toString()));
			}
			if (app.getOperand2().type() == ExprKind.VARIABLE){
				str.append(String.format("%s ",((Variable)app.getOperand2()).toString()));
			}
		}
		return str;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(); 
		
		if (operand1.type() == ExprKind.VARIABLE) {
			str.append(String.format("%s ", ((Variable)operand1).toString()));
		} else if (operand1.type() == ExprKind.ABSTRACTION) {
			str.append(String.format("%s ", ((Abstraction)operand1).toString()));
		} else {
			str = flattenBody((Application)operand1, str);
		}
		
		if (operand2.type() == ExprKind.VARIABLE) {
			str.append(((Variable)operand2).toString());
		} else if (operand2.type() == ExprKind.ABSTRACTION) {
			str.append(((Abstraction)operand2).toString());
		} else {
			str = flattenBody((Application)operand2, str);
		}
		
		return str.toString();
	}
}
