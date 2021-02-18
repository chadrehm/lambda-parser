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
public class Parser {
	public LambdaExpr parse(String term) {
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
	
	private Abstraction parseAbstraction(String term) {
		Abstraction abstraction = new Abstraction();
		
		abstraction.setBoundVar(new Variable(term.charAt(2)));
		
		int period = term.indexOf(".");
		// Get the next character after the period
		String body = term.substring(period + 1, term.indexOf(")"));
		abstraction.setBody(body.trim());
		
		return abstraction;		
	}
	
	private Application parseApplication(String term) {
		Application application = new Application();
		int rightParenIndex = findBalancedRightParenPos(term);
		
		application.setOperand1(parse(term.substring(0,rightParenIndex + 1)));
		application.setOperand2(parse(term.substring(rightParenIndex + 1)));
		
		return application;
	} 
	
	private Variable parseVariable(String term) {
		return new Variable(term.charAt(1));
	}
	
	private int findBalancedRightParenPos(String term) {
		int parenCount = 0;
		int loopCount = 0;
		int rightParenIndex = -1;
		
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
				//throw new error
			}
			
			loopCount++;
		} while(loopCount <= term.length());
		
		if (loopCount != 0) {
			// throw new error
		}
		
		return rightParenIndex;
	}
}
