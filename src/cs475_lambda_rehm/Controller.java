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
		Parser parser = new Parser();
		LambdaExpr lambdaExpr;
		Simulator simulator = new Simulator();
		String userContinue = "y";
		
		while(!userContinue.equals("n")) {
			String lambdaExp = this.promptInput();
			lambdaExpr = parser.parse(lambdaExp);
			lambdaExpr = simulator.betaReduce(lambdaExpr);

			displayLambdaExpr(lambdaExpr);
			
			userContinue = promptContinue();
		}
	}
	
	private void displayLambdaExpr(LambdaExpr lambdaExpr) {
		if (lambdaExpr.type() == ExprKind.ABSTRACTION) {
			Abstraction abstraction = (Abstraction)lambdaExpr;
		
			JOptionPane.showMessageDialog(frame, String.format("The Lambda Expression is: L%s. %s", 
				abstraction.getBoundVar().getName(), abstraction.getBody()));
		
		} else {
			JOptionPane.showMessageDialog(frame, String.format("The Lambda Expression is: %s",
				((Variable)lambdaExpr).getName()));
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
