/**
 * @author Chad Rehm
 * @date 1/27/21
 * @description This class controls the application.
 */
package cs475_lambda_rehm;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Controller{
	JFrame frame;
	JTextArea  tarea;
	JOptionPane pane;
	
	Controller(JFrame frame, JTextArea  tarea, JOptionPane pane) {
		this.tarea = tarea;
		this.frame = frame;
		this.pane = pane;
	}
	
	/**
	 * Start the program that will interact with the user.
	 * 
	 * @throws Exception
	 */
	public void runProgram() throws Exception {
		String userContinue = "y";
		
		while(!userContinue.equals("n")) {
			String lambdaStr = this.promptInput();
			
			lambdaStr = lambdaStr.replaceAll("(\\) \\()", ")(");
	
			LambdaExpr lambdaExpr = execute(lambdaStr);
			displayLambdaExpr(lambdaExpr);
			
			userContinue = promptContinue();
		}
	}
	
	/**
	 *	Actually execute the program.  This is split out so that the unit tests
	 * do not trigger UI unless an error is thrown.
	 * 
	 * @param lambdaStr
	 * @return
	 * @throws Exception
	 */
	protected LambdaExpr execute(String lambdaStr) throws Exception{
		Parser parser = new Parser();
		Simulator simulator = new Simulator();
		LambdaExpr lambdaExpr = null;
		
		try {
			lambdaExpr = parser.parse(lambdaStr);
			lambdaExpr = simulator.betaReduce(lambdaExpr);
		} catch (Exception error) {
			showErrorMessage(error.getMessage());
			throw new Error(error.getMessage());
		}
		return lambdaExpr;
	}
	
	private void displayLambdaExpr(LambdaExpr lambdaExpr) {
		if (lambdaExpr.type() == ExprKind.ABSTRACTION) {
			Abstraction abstraction = (Abstraction)lambdaExpr;
		
			JOptionPane.showMessageDialog(frame, String.format("The Lambda Expression is: %s", 
				abstraction.toString()));
		
		} else if(lambdaExpr.type() == ExprKind.VARIABLE) {
			JOptionPane.showMessageDialog(frame, String.format("The Lambda Expression is: %s",
				((Variable)lambdaExpr).toString()));
		} else {
			JOptionPane.showMessageDialog(frame, String.format("The Lambda Expression is: %s",
				((Application)lambdaExpr).toString()));
		}
	}
	
	/**
	 * Show error message
	 * @return
	 */
	private void showErrorMessage(String msg) {
		pane.showMessageDialog(frame, msg);
	}
	
	/**
	 * Open prompt and return input string
	 * @return
	 */
	private String promptInput() {
		return pane.showInputDialog(frame, "Enter a lambda expression.");
	}
	
	/**
	 *
	 * @return
	 */
	private String promptContinue() {
		return pane.showInputDialog(frame, "Enter another string? (type n to exit)");
	}
}
