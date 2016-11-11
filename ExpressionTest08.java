package lab6;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ExpressionTest08 {

	@Test
	public void testExpression() {
		testCase("a-b","[[a-b]]");
	}
	
	private void testCase(String inputString, String expectedString) {
		ArrayList<ArrayList<Node>> actual = Lab1.expression(inputString);
		String actualString = actual.toString();
	    System.out.println("expect: "+expectedString);
		System.out.println("actual: "+actualString);
		assertEquals(expectedString,actualString);
	}

}