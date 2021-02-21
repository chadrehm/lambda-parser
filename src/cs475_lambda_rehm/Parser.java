/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin this class parses a lambda expression
 */
package cs475_lambda_rehm;

import java.util.ArrayList;

public class Parser {

	public LambdaExpr parse(String term) throws ParseException {
		int rightParenIndex = findBalancedRightParenPos(term);
		LambdaExpr lambdaExpr = null;
		
		// If there are more then one term then the lenght will be greater then one
		// more than the right parenthesis index
		if (term.length() > rightParenIndex + 1) {
			lambdaExpr = parseApplication(term);	
		} else {
			if (term.charAt(1) == 'L') {
				lambdaExpr = parseAbstraction(term);
			} else {
				lambdaExpr = parseVariable(term);
			}
		}
		
		return lambdaExpr;
	}
	
	private Abstraction parseAbstraction(String term) throws ParseException {
		Abstraction abstraction = new Abstraction();
		
		abstraction.setBoundVar(new Variable(term.charAt(2)));
		
		int period = term.indexOf(".");
		if (period == -1) {
			throw new ParseException("Abstractions require a period after bounding Var.");
		}
		// Get the next character after the period
		String[] bodyStr = term.substring(period + 1, term.indexOf(")")).trim().split(" ");
		LambdaExpr[] body = new LambdaExpr[bodyStr.length];
		
		// All terms in body are of type Variable 
		for (int i = 0; i < bodyStr.length; i++) {
			body[i] = new Variable(bodyStr[i].charAt(0));
		}
		abstraction.setBody(body);
		
		return abstraction;		
	}
	
	private Application parseApplication(String term) throws ParseException {
		Application application = new Application();
		ArrayList<LambdaExpr> exprList = new ArrayList<>();

		exprList = buildExprList(exprList, term,0);
		
		LambdaExpr[] ExprArr = new LambdaExpr[exprList.size()];
		for (int i = 0; i < exprList.size(); i++) {
			ExprArr[i] = exprList.get(i);
		}
		application = buildExprTree(ExprArr, application, ExprArr.length - 1);
		
		return application;
	} 
	
	private Variable parseVariable(String term) throws ParseException {
		String str = term.replaceAll("[()]", "").trim();
		if(str.length() != 1) {
			throw new ParseException("Variables must a single character in parenthesis.");
		}
		
		return new Variable(str.charAt(0));
	}
	
	private int findBalancedRightParenPos(String term) throws ParseException {
		int parenCount = 0;
		int loopCount = 0;
		int rightParenIndex = -1;
		
		if (term.length() == 0) {
			throw new ParseException("Blank inputs are not valid.");
		}
		
		do {
			if(term.charAt(loopCount) == '(') {
				parenCount++;
			} else if(term.charAt(loopCount) == ')') {
				parenCount--;
				rightParenIndex = loopCount;
				
				// The closesing paren has been found
				if (parenCount == 0) {
					break;
				}
			}
			
			if (parenCount < 0) {
				throw new ParseException("No opening parenthesis found.");
			}
			
			loopCount++;
		} while(loopCount < term.length());
		
		if (parenCount != 0) {
			throw new ParseException("No closing parenthesis found.");
		} else if (rightParenIndex == -1) {
			throw new ParseException("All terms must be wrapped in parenthesis.");
		}
		
		return rightParenIndex;
	}
	
	public ArrayList<LambdaExpr> buildExprList(ArrayList<LambdaExpr> exprList, String term, int idx) 
		throws ParseException {
		
		int rightParenIndex = findBalancedRightParenPos(term);
		
		if (term.charAt(1) == 'L') {
			exprList.add(parseAbstraction(term));
		} else {
			exprList.add(parseVariable(term.substring(0,rightParenIndex)));
		}
		
		if (term.length() > rightParenIndex + 1) {
			exprList = buildExprList(exprList, term.substring(rightParenIndex + 1), rightParenIndex + 1);
		} 
	
		return exprList;
	}
	
	protected Application buildExprTree(LambdaExpr[] body, Application root, int i) {
		if (1 < i) {
			Application temp;
			temp = buildExprTree(body, new Application(), i - 1);
			root.setOperand2(body[i]);
			root.setOperand1(temp);
		} else {
			root.setOperand1(body[i - 1]);
			root.setOperand2(body[i]);
		}
		return root;
	}
	
}
