package Lab7;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExpressionTest12 {

	@Test
	public void testExpression() {
		testCase("A*(B+c)","A*(B+c)");
	}
	
	private void testCase(String inputString, String expectedString) {
		Simplification test = new Simplification(inputString);
		test.simplify("");
		String actualString = test.getPolynomial().toString();
	    System.out.println("expect: "+expectedString);
		System.out.println("actual: "+actualString);
		assertEquals(expectedString,actualString);
	}

}
