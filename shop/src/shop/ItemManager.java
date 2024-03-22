package shop;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemManager {
	private ArrayList<Item> itemlist;
	private UserManager userManager;
	public ItemManager() {
		itemlist = new ArrayList<Item>();
		userManager = Shop.getUserManager();
	}
	
	public ArrayList<Item> getItemlist() {
		return itemlist;
	}

	public void enrollItem() {
		Item item = createItem();
		itemlist.add(item);
		System.out.println("상품 등록이 완료 되었습니다.");
	}

	private Item createItem() {
		Item item = null;
		String name = inputString("name");
		int price = inputNumber("price");
		item = new Item(name,price);
		return item;
	}


	public void deleteItem() {
		if (itemlist.size() == 0) {
			System.out.println("상품이 없습니다.");
			return;
		}
		Item item = findItem();
		if (item == null) {
			return;
		}
		itemlist.remove(item);
		deleteItemfromUserCart(item);
		System.out.println("삭제가 완료 되었습니다.");
		
	}

	private void deleteItemfromUserCart(Item item) {
		ArrayList<User> userlist = userManager.getUserList();
		for (int i = 0; i < userlist.size(); i++) {
			User user = userlist.get(i);
			ArrayList<Item>shoppingList = user.getCart().getShoppingList();
			for (int j = 0; j < shoppingList.size(); j++) {
				if (shoppingList.get(j).equals(item)) {
					shoppingList.remove(item);
				}
			}
		}
	}

	public Item findItem() {
		printItemList();
		return findItemByIndex();
	}

	private Item findItemByIndex() {
		Item item = null;
		int index = inputNumber("상품의 번호를 입력하세요.");
		if (index<0 || index>=itemlist.size()) {
			System.out.println("번호를 확인해주세요.");
		}else {
			item = itemlist.get(index);
		}
		return item;
	}

	private void printItemList() {
		for (int i = 0; i < itemlist.size(); i++) {
			System.out.println(i+") " + itemlist.get(i));
		}
	}

	public void updateItem() {
		if (itemlist.size() == 0) {
			System.out.println("상품이 존재하지 않습니다.");
			return;
		}
		Item item = findItem();
		if (item == null) {
			return;
		}
		item.setPrice(inputNumber("new price"));
		updatePriceFromUserCart(item);
		System.out.println("수정이 완료 되었습니다.");
		
	}

	private void updatePriceFromUserCart(Item item) {
		for (int i = 0; i < userManager.getUserList().size(); i++) {
			User user = userManager.getUserList().get(i);
			ArrayList<Item> shoppingList = user.getCart().getShoppingList();
			for (int j = 0; j < shoppingList.size(); j++) {
				if (shoppingList.get(i).getName().equals(item)) {
					shoppingList.get(i).setPrice(item.getPrice());
				}
			}
		}
	}

	public void printSaleSate() {
		// TODO Auto-generated method stub
		
	}
	

	private String inputString(String message) {
		System.out.println(message + " : ");
		return new Scanner(System.in).next();
	}
	
	private int inputNumber(String message) {
		int number = -1;
		System.out.print(message + " : ");
		try {
			String numberString = new Scanner(System.in).next();
			number = Integer.parseInt(numberString);
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요 ");
			return inputNumber(message);
		}
		return number;
	}

}
