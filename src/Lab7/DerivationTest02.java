package Lab7;

import static org.junit.Assert.*;

import org.junit.Test;

public class DerivationTest02 {

	@Test
	public void testDerivative() {
		testCase("y+y*5*x*y", "0", "z");
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
