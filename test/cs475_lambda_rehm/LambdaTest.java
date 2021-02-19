package cs475_lambda_rehm;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 *
 * @author Chad Rehm
 */
public class LambdaTest {
	JFrame frame;
	JTextArea  tarea;
	JOptionPane pane;
	
	public LambdaTest() {
		
	}
	
	@Test
	public void testBuildBody_four() {
		Abstraction abs = new Abstraction();
		Application app = new Application();
		
		LambdaExpr [] in = {
			new Variable('x'), 
			new Variable('x'), 
			new Variable('x'), 
			new Variable('x')
		};
		
		Application result = abs.buildBody(in, app, in.length - 1);
		int x = 1;
	}
	
	@Test
	public void testBuildBody_three() {
		Abstraction abs = new Abstraction();
		Application app = new Application();
		
		LambdaExpr [] in = {new Variable('x'), new Variable('x'), new Variable('x')};
		
		Application result = abs.buildBody(in, app, in.length - 1);
		int x = 1;
	}
	
	@Test
	public void testBuildBody_two() {
		Abstraction abs = new Abstraction();
		Application app = new Application();
		
		LambdaExpr [] in = {new Variable('x'), new Variable('x')};
		
		Application result = abs.buildBody(in, app, in.length - 1);
		int x = 1;
	}
	
	// Exception testing
	@Test
	public void testParser_Error() throws Exception{
		Parser parser = new Parser();
		ParseException error = null;
		
		try {
			parser.parse("Lx. x x x)");
		} catch (ParseException e) {
			error = e;
		}
		
		assertNotNull(error);
	}
	
	@Test
	public void testParser_Error_2() throws Exception{
		Parser parser = new Parser();
		ParseException error = null;
		
		try {
			parser.parse("Lx. x x x)");
		} catch (ParseException e) {
			error = e;
		}
		
		assertNotNull(error);
	}

	@Test
	public void testParser_Error_3() throws Exception{
		Parser parser = new Parser();
		ParseException error = null;
		
		try {
			parser.parse("Lx. x x x)");
		} catch (ParseException e) {
			error = e;
		}
		
		assertNotNull(error);
	}
	// Full test
	@Test
	public void testParseAbstraction_single() throws Exception{
		Parser parser = new Parser();
		LambdaExpr lambda;
		
		lambda = parser.parse("(x)");
		String str = lambda.toString();
		assertTrue(str.equals("x"));
	}
	
	@Test
	public void testParseAbstraction() throws Exception{
		Parser parser = new Parser();
		LambdaExpr lambda;
		
		lambda = parser.parse("(Lx. x)");
		String str = lambda.toString();
		assertTrue(str.equals("Lx. x"));
	}
	
	@Test
	public void testParseAbstraction_y() throws Exception {
		Parser parser = new Parser();
		LambdaExpr lambda;
		
		lambda = parser.parse("(Lx. x y x)");
		String str = lambda.toString();
		assertTrue(str.equals("Lx. x y x"));
	}
	
	@Test
	public void testExecute_double_replace() {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Lx. x x)(y)");
		String str = lambda.toString();
		assertTrue(str.equals("y y"));
	}
	
	@Test
	public void testExecute_triple_replace() {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Lx. x x x)(y)");
		String str = lambda.toString();
		assertTrue(str.equals("y y y"));
		
		lambda = controller.execute("(Lx. x z x)(y)");
		str = lambda.toString();
		assertTrue(str.equals("y z y"));
	}
	
	@Test
	public void testExecute_beta_reduce() {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Lx. x y)(Lz. z)");
		String str = lambda.toString();
		assertTrue(str.equals("y"));
	}
	
	@Test
	public void testExecute_mocking_bird() {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Lx. x x)(Lx. x x)");
		String str = lambda.toString();
	}
	
	@Test
	public void testExecute_var_abs() {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(x)(Lx. x x)");
		String str = lambda.toString();
		assertTrue(str.equals("x Lx. x x"));
	}
}
