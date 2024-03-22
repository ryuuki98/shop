package shop;

public class Item {
	private String name;
	private int price;
	private int ea;
	public Item(String name,int price) {
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s : %d원",name,price);
	}
	
}
