/**
 * @author Chad Rehm
 * @date 1/13/21
 * @description This is the programs view
 */
package cs475_lambda_rehm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class View {
	JFrame frame;
	JTextArea  tarea;
	JOptionPane pane;
	
	View(){
		JFrame.setDefaultLookAndFeelDecorated(true);
    this.frame = new JFrame("Reduce Lambda");
		frame.setPreferredSize(new Dimension(350,300));
		frame.setLocation(100, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.tarea = new JTextArea(10, 10);

		this.pane = new JOptionPane();

    frame.getContentPane().add(tarea, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTextArea getTarea() {
		return tarea;
	}

	public JOptionPane getPane() {
		return pane;
	}
}
