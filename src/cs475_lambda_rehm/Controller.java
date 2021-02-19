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
	
	public void runProgram() {
		String userContinue = "y";
		
		while(!userContinue.equals("n")) {
			String lambdaStr = this.promptInput();
			
			LambdaExpr lambdaExpr = execute(lambdaStr);

			displayLambdaExpr(lambdaExpr);
			
			userContinue = promptContinue();
		}
	}
	
	protected LambdaExpr execute(String lambdaStr) {
		Parser parser = new Parser();
		Simulator simulator = new Simulator();
		LambdaExpr lambdaExpr = parser.parse(lambdaStr);
		lambdaExpr = simulator.betaReduce(lambdaExpr);
	
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
