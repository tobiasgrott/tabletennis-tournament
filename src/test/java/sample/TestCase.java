package sample;

import org.junit.Test;

import junit.framework.Assert;

public class TestCase {

	@Test
	public void testCase(){
		String input = "Hello World";
		Assert.assertEquals("Hello World", input);
	}
}
