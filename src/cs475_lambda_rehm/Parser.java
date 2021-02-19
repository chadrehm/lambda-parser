/**
 * @author Chad Rehm
 * @data 2/18/21
 * @descriptioin this class parses a lambda expression
 */
package cs475_lambda_rehm;

public class Parser {
	public LambdaExpr parse(String term)  throws ParseException {
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
		String[] bodyStr = term.substring(period + 1, term.indexOf(")")).trim().split(" ");
		LambdaExpr[] body = new LambdaExpr[bodyStr.length];
		
		for (int i = 0; i < bodyStr.length; i++) {
			body[i] = new Variable(bodyStr[i].charAt(0));
		}
		abstraction.setBody(body);
		
		return abstraction;		
	}
	
	private Application parseApplication(String term) throws ParseException{
		Application application = new Application();
		int rightParenIndex = findBalancedRightParenPos(term);
		
		application.setOperand1(parse(term.substring(0,rightParenIndex + 1)));
		application.setOperand2(parse(term.substring(rightParenIndex + 1)));
		
		return application;
	} 
	
	private Variable parseVariable(String term) {
		return new Variable(term.charAt(1));
	}
	
	private int findBalancedRightParenPos(String term) throws ParseException {
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
				throw new ParseException("No opening parenthesis found.");
			}
			
			loopCount++;
		} while(loopCount <= term.length());
		
		if (parenCount != 0) {
			throw new ParseException("No closing parenthesis found.");
		}
		
		return rightParenIndex;
	}
}
