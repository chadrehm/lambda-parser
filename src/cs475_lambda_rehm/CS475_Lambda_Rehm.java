/**
 * @author Chad Rehm
 * @data 1/11/21
 * @description CS475 Comp Theory assignment 1 - Build Pushdown Automata
 */
package cs475_lambda_rehm;

import javax.swing.SwingUtilities;

public class CS475_Lambda_Rehm {


	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = createAndShowGUI();
					controller.runProgram();
				} catch (Exception e) {
					System.out.println("An error occured");
					System.exit(0);
				}
			}
		});
		
		//System.exit(0);
	}	
 
	/**
	 * Initialize the view
	 * @throws Exception
	 */
	public static Controller createAndShowGUI() throws Exception {
		View view = new View();
		return new Controller(view.getFrame(), view.getTarea(), view.getPane());
	}
}
