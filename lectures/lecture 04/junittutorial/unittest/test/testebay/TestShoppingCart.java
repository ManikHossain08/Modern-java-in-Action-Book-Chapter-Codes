package testebay;

import junit.framework.Assert;
import junit.framework.TestCase;
import ebay.ShoppingCart;
import ebay.Product;
	
/**
 * This is a sample JUnit test case for ebay.ShoppingCart.java
 * 
 * @author Bin Han
 */

public class TestShoppingCart extends TestCase {
	
	private Product product_1;
	private ShoppingCart cart_1;
	
	/* 
	 * set up the test fixture.
	 * called before every test case method
	 */
	protected void setUp() throws Exception {
		product_1 = new Product("Thinking in Java");
		cart_1 = new ShoppingCart();
	}

	public void testAddItem() {
		cart_1.addItem(product_1);
		Assert.assertEquals(1, cart_1.getItemNum());
	}

	public void testRemoveAll() {
		cart_1.addItem(product_1);
		cart_1.removeAll();
		Assert.assertEquals(0, cart_1.getItemNum());
	}
	
	public void testIsEmpty() {
		Assert.assertEquals(0, cart_1.getItemNum());
	}

	public void testGetItemNum() {
		Assert.assertEquals(0, cart_1.getItemNum());
	}

}
