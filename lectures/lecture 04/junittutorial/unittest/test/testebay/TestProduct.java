package testebay;

import junit.framework.Assert;
import junit.framework.TestCase;
import ebay.Product;

/**
 * This is a sample JUnit test case for ebay.Product.java
 * 
 * @author Bin Han
 */
public class TestProduct extends TestCase {
	
	private Product p;

	public void testGetName() {
		String s = "Thinking in Java";
		p = new Product (s);
		Assert.assertEquals(s, p.getName());
	}

}
