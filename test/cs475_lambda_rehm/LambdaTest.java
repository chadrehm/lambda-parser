package cs475_lambda_rehm;

import java.util.ArrayList;
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
	public void testBuildExprArray() throws Exception{
		Parser parser = new Parser();
		ArrayList<LambdaExpr> exprArr = parser.buildExprList(new ArrayList<LambdaExpr>(),
			"(x)(Ly. y)(k)", 0);
		
		int x = 1;
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
		assertTrue(error.getMessage().equals("No opening parenthesis found."));
	}
	
	@Test
	public void testParser_Error_2() throws Exception{
		Parser parser = new Parser();
		ParseException error = null;
		
		try {
			parser.parse("(Lx. x x x)x");
		} catch (ParseException e) {
			error = e;
		}
		
		assertNotNull(error);
		assertTrue(error.getMessage()
			.equals("All terms must be wrapped in parenthesis."));
	}

	@Test
	public void testParser_Error_3() throws Exception{
		Parser parser = new Parser();
		ParseException error = null;
		
		try {
			parser.parse("(Lx. x x x");
		} catch (ParseException e) {
			error = e;
		}
		
		assertNotNull(error);
		assertTrue(error.getMessage().equals("No closing parenthesis found."));
	}
	
	@Test
	public void testParser_Error_4() throws Exception{
		Parser parser = new Parser();
		ParseException error = null;
		
		try {
			parser.parse("(x x)");
		} catch (ParseException e) {
			error = e;
		}
		assertNotNull(error);
		assertTrue(error.getMessage()
			.equals("Variables must a single character in parenthesis."));
	}
	
	@Test
	public void testParser_Error_5() throws Exception{
		Parser parser = new Parser();
		ParseException error = null;
		
		try {
			parser.parse("(Lx x)");
		} catch (ParseException e) {
			error = e;
		}
		assertNotNull(error);
		assertTrue(error.getMessage()
			.equals("Abstractions require a period after bounding Var."));
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
	public void testExecute_double_replace() throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Lx. x x)(y)");
		String str = lambda.toString();
		assertTrue(str.equals("y y"));
	}
	
	@Test
	public void testExecute_triple_replace() throws Exception  {
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
	public void testExecute_beta_reduce()  throws Exception {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Lx. x y)(Lz. z)");
		String str = lambda.toString();
		assertTrue(str.equals("y"));
	}
	
	@Test
	public void testExecute_mocking_bird() throws Exception {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		Exception error = null;
		
		try {
			lambda = controller.execute("(Lx. x x)(Lx. x x)");
		} catch (Exception e) {
			error = e;
		}
	}
	
	@Test
	public void testExecute_var_abs() throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(x)(Lx. x x)");
		String str = lambda.toString();
		assertTrue(str.equals("x (Lx. x) x"));
	}
	
	@Test
	public void testExecute_three_term() throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(x)(Ly. y)(k)");
		String str = lambda.toString();
		assertTrue(str.equals("x (Ly. y) k"));
	}
	
	@Test
	public void testExecute_four_term_a() throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Lx. x y)(Lz. z)(Lr. r)(p)");
		String str = lambda.toString();
		assertTrue(str.equals("y (Lr. r) p"));
	}
	
	@Test
	public void testExecute_four_term_b() throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Lx. x y)(Lz. z)(r)(p)");
		String str = lambda.toString();
		assertTrue(str.equals("y r p"));
	}
	
	@Test
	public void testExecute_two_term () throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Ly. x y x)(Lz. z)");
		String str = lambda.toString();
		assertTrue(str.equals("x (Lz. z) x"));
	}
	
	@Test
	public void testExecute_two_term_b () throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Ly. y x)(Lz. z z)");
		String str = lambda.toString();
		assertTrue(str.equals("x x"));
	}
	
	@Test
	public void testExecute_a () throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Ly. y x y)(Lz. z z)");
		String str = lambda.toString();
		assertTrue(str.equals("x x (Lz. z z)"));
	}
	
	@Test
	public void testExecute_b () throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Ly. y x y)(Lz. z w z)");
		String str = lambda.toString();
		assertTrue(str.equals("x w x (Lz. z) w z"));
	}
	
	@Test
	public void testExecute_c () throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Ly. x y y)(Lz. z)");
		String str = lambda.toString();
		assertTrue(str.equals("x (Lz. z) (Lz. z)"));
	}
	
	@Test
	public void testExecute_d () throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Ly. y y x)(Lz. z)");
		String str = lambda.toString();
		assertTrue(str.equals("x"));
	}
	
	@Test
	public void testExecute_e () throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("(Ly. y y y x)(Lz. z w)");
		String str = lambda.toString();
		assertTrue(str.equals("w w (Lz. z w) x"));
	}
	
	@Test
	public void testExecute_parseException () throws Exception  {
		Controller controller = new Controller(frame, tarea, pane);
		LambdaExpr lambda;
		
		lambda = controller.execute("Ly. y x)(Lz. z z)");
	}
}
