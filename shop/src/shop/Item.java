package shop;

public class Item {
	private String name;
	private int price;
	private int ea;
	public Item(String name,int price) {
		this.name = name;
		this.price = price;
	}
	
	public Item(String itemName, int price, int ea) {
		this.name = itemName;
		this.price = price;
		this.ea = ea;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	
	
	public Item clone() {
		
		return new Item(name, price);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s : %d원",name,price);
	}
	
}
