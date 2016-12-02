package Lab7;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExpressionTest02 {

	@Test
	public void testExpression() {
		testCase("", "0");
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
