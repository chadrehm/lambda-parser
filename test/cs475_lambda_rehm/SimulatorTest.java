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
		
		LambdaExpr lambda = parser.parse("(Lx. x)(y)");
		
		sim.betaReduce(lambda);
		
		int x = 1;
	}
	
}
