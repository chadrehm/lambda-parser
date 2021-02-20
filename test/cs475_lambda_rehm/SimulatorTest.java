package cs475_lambda_rehm;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimulatorTest {
	
	public SimulatorTest() {
	}

	@Test
	public void testBetaReduce() throws Exception{
		Simulator sim = new Simulator();
		Parser parser = new Parser();
		
		LambdaExpr lambda = parser.parse("(Lx. x x)(Ly. y)");
		
		lambda = sim.betaReduce(lambda);
		
		int x = 1;
	}
	
	@Test
	public void testBetaReduce_reduce_one_abs() throws Exception{
		Simulator sim = new Simulator();
		Parser parser = new Parser();
		
		LambdaExpr lambda = parser.parse("(Lx. x)(Ly. y)");
		
		lambda = sim.betaReduce(lambda);
		
		int x = 1;
	}
	
	@Test
	public void testBetaReduce_one_reduce_var() throws Exception{
		Simulator sim = new Simulator();
		Parser parser = new Parser();
		Variable var = new Variable('y');
		
		LambdaExpr lambda = parser.parse("(Lx. x)(y)");
		lambda = sim.betaReduce(lambda);
		
		assertTrue(var.equals(lambda));
	}
	
	@Test
	public void testBetaReduce__var() throws Exception{
		Simulator sim = new Simulator();
		Parser parser = new Parser();
		Variable var = new Variable('y');
		
		LambdaExpr lambda = parser.parse("(y)");
		lambda = sim.betaReduce(lambda);
		
		assertTrue(var.equals(lambda));
	}
	
	@Test
	public void testBetaReduce_abs() throws Exception{
		Simulator sim = new Simulator();
		Parser parser = new Parser();
		
		LambdaExpr lambda = parser.parse("(Lx. x)");
		
		lambda = sim.betaReduce(lambda);
		
		int x = 1;
	}
}
