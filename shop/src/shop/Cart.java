package shop;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> shoppingList;
	public Cart() {
		shoppingList = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getShoppingList() {
		return shoppingList;
	}

	public void clearAll() {
		this.shoppingList.clear();
	}
}
