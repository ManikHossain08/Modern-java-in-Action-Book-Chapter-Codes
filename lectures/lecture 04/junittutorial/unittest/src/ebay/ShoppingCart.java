package ebay;

import java.util.ArrayList;

public class ShoppingCart {
	
	private ArrayList<Product> item;
	
	public ShoppingCart()
	{
		item = new ArrayList<Product>();
	}
	
	public void addItem (Product p)
	{
		this.item.add(p);
	}
	
	public void removeAll ()
	{
		this.item.clear();
	}
	
	public boolean isEmpty()
	{
		return this.item.isEmpty();
	}
	 
	public int getItemNum()
	{
		return this.item.size();
	}
}
