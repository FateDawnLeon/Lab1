package Lab7;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExpressionTest01 {

	@Test
	public void testExpression() {
		testCase("1*x*2+3*y+-4*z+w", "2*x+3*y+-4*z+w");
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
