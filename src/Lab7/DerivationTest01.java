package Lab7;

import static org.junit.Assert.*;

import org.junit.Test;

public class DerivationTest01 {

	@Test
	public void testDerivative() {
		testCase("y+-1*y*5*x*y", "1+-1*2*5*x*y", "y");
	}
	
	private void testCase(String inputString, String expectedString, String der) {
		Derivation test = new Derivation(inputString);
		test.derivative(der);;
		String actualString = test.getPolynomial().toString();
	    System.out.println("expect: "+expectedString);
		System.out.println("actual: "+actualString);
		assertEquals(expectedString,actualString);
	}

}
