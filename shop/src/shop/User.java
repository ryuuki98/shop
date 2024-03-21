package shop;

public class User {
	private String name;
	private String id;
	private String password;
	private Cart cart;
	
	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
		cart = new Cart();
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Cart getCart() {
		return cart;
	}
}
