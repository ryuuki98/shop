package shop;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemManager {
	private ArrayList<Item> itemlist;
	public ItemManager() {
		itemlist = new ArrayList<Item>();
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
		// TODO Auto-generated method stub
		
	}

	public void updateItem() {
		// TODO Auto-generated method stub
		
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
