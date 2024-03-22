package shop;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 유저 -
 * ㄴ 회원가입/탈퇴
 * ㄴ 로그인/로그아웃
 * ㄴ 쇼핑
 * ㄴ 마이페이지(내 장바구니,항목삭제,수량수정,결제)
 * ㄴ 자동저장/자동로드
 * 
 * 관리자 -
 * ㄴ 아이템 등록/삭제/수정
 * ㄴ 조회(총 매출)
 */

public class Shop {

	private final int ADMIN_LOG = 0;

	// mypage Submenu
	private final int MY_BAG = 1;
	private final int DELETE_ITEM_FROM_MY_CART = 2;
	private final int MODIFY_EA = 3;
	private final int PURCHASE = 4;

	// Admin Submenu
	private final int ENROLL_ITEM = 1;
	private final int DELETE_ITEM = 2;
	private final int UPDATE_ITEM = 3;
	private final int PRINT_SALE_STATE = 4;

	// User Submenu
	private final int JOIN = 1;
	private final int DELETE_USER = 2;
	private final int LOG_IN = 3;
	private final int LOG_OUT = 4;
	private final int SHOPPING = 5;
	private final int MYPAGE = 6;

	// menu
	private final int USER = 1;
	private final int ADMIN = 2;
	private final int END_SYSTEM = 0;
	
	private int totalPrice;

	private static int log;
	private boolean isRun;

	private static UserManager userManager;
	private static ItemManager itemManager;
	private FileManager fileManager;

	private String shopName;

	public static int getLog() {
		return log;
	}

	public static void setLog(int log) {
		Shop.log = log;
	}

	public static UserManager getUserManager() {
		return userManager;
	}

	public static ItemManager getItemManager() {
		return itemManager;
	}

	public Shop(String shopName) {
		this.shopName = shopName;

		userManager = new UserManager();
		itemManager = new ItemManager();
		fileManager = new FileManager();

		isRun = true;
		log = -1;
	}

	public void run() {
		// load
		while (isRun) {
			printMenu();
			int select = inputNumber("menu");
			runMenu(select);
			// save
		}
	}

	private void runMenu(int select) {
		if (select == USER) {
			printUserMenu();
			int option = inputNumber("menu");
			runUserMenu(option);
		} else if (select == ADMIN && isAdmin()) {
			printAdminMenu();
			int option = inputNumber("menu");
			runAdminMenu(option);
		} else if (select == END_SYSTEM) {
			isRun = false;
		}
	}

	private boolean isAdmin() {
		if (log != ADMIN_LOG) {
			System.out.println("관리자만 이용할 수 있는 메뉴입니다.");
			return false;
		}
		return true;
	}

	private void runAdminMenu(int option) {
		if (option == ENROLL_ITEM) {
			itemManager.enrollItem();
		} else if (option == DELETE_ITEM) {
			itemManager.deleteItem();
		} else if (option == UPDATE_ITEM) {
			itemManager.updateItem();
		} else if (option == PRINT_SALE_STATE) {
			itemManager.printSaleSate();
		}
	}

	private void runUserMenu(int option) {
		if (option == JOIN && logout()) {
			userManager.join();
		} else if (option == DELETE_USER && login()) {
			userManager.deleteUser();
		} else if (option == LOG_IN && logout()) {
			userManager.login();
		} else if (option == LOG_OUT && login()) {
			userManager.logout();
		} else if (option == SHOPPING && login()) {
			shopping();
		} else if (option == MYPAGE && login()) {
			printMyPageMenu();
			option = inputNumber("menu");
			runMyPageMenu(option);
		}
	}

	private void runMyPageMenu(int option) {
		if (option == MY_BAG) {
			printMyBag();
		} else if (option == DELETE_ITEM_FROM_MY_CART) {
			deleteItemFromMyCart();
		} else if (option == MODIFY_EA) {
			modifyEa();
		} else if (option == PURCHASE) {
			purchase();
		}
	}

	private void purchase() {
		User user = userManager.getUserList().get(log);
		ArrayList<Item> shoppingList = user.getCart().getShoppingList();
		
		if (shoppingList.size() == 0) {
			System.out.println("장바구니가 비어있습니다.");
			return;
		}
		
		printMyBag();
		int total = TotalPrice(shoppingList);
		int check = inputNumber("결제하시겠습니까 ? (y:1 n:0");
		if (check == 1) {
			totalPrice += total;
			user.getCart().clearAll();
			System.out.println("결제가 완료 되었습니다.");
		}
	}

	private int TotalPrice(ArrayList<Item> shoppingList) {
		int total = 0;
		for (int i = 0; i < shoppingList.size(); i++) {
			Item item = shoppingList.get(i);
			total += item.getPrice() * item.getEa();
		}
		System.out.println("총 금액 : " + total);
		return total;
	}

	private void modifyEa() {
		User user = userManager.getUserList().get(log);
		ArrayList<Item> shoppingList = user.getCart().getShoppingList();
		
		if (shoppingList.size() == 0) {
			System.out.println("장바구니가 비어있습니다.");
			return;
		}
		
		printMyBag();
		int index = inputNumber("수량을 수정하실 품목의 번호를 입력하세요");
		if (index <0 || index>=shoppingList.size()) {
			System.out.println("번호를 확인하세요.");
			return;
		}
		int newEa = inputNumber("수량을 입력하세요");
		shoppingList.get(index).setEa(newEa);
		System.out.println("수정이 완료 되었습니다.");
		
	}

	private void deleteItemFromMyCart() {
		User user = userManager.getUserList().get(log);
		ArrayList<Item> shoppingList = user.getCart().getShoppingList();

		if (shoppingList.size() == 0) {
			System.out.println("장바구니가 비어있습니다.");
			return;
		}
		
		printMyBag();
		int index = inputNumber("삭제하실 품목의 번호를 입력하세요");
		if (index <0 || index>=shoppingList.size()) {
			System.out.println("번호를 확인하세요.");
			return;
		}
		shoppingList.remove(index);
		System.out.println("삭제가 완료 되었습니다.");
	}

	private void printMyBag() {
		User user = userManager.getUserList().get(log);
		ArrayList<Item> shoppingList = user.getCart().getShoppingList();

		if (shoppingList.size() == 0) {
			System.out.println("장바구니가 비어있습니다.");
			return;
		}

		System.out.println("==============================");
		System.out.println(user.getName() + "님의 장바구니");
		printMyBag(shoppingList);
		System.out.println("==============================");
	}

	private void printMyBag(ArrayList<Item> shoppingList) {
		for (int i = 0; i < shoppingList.size(); i++) {
			Item item = shoppingList.get(i);
			System.out.println(i + ") " + item + "    " + item.getEa() + "개");
		}
	}

	private boolean logout() {
		if (log != -1) {
			System.out.println("로그아웃 후 이용하세요");
			return false;
		}
		return true;
	}

	private boolean login() {
		if (log == -1) {
			System.out.println("로그인 후 이용하세요");
			return false;
		}
		return true;
	}

	private void shopping() {
		if (itemManager.getItemlist().size() == 0) {
			System.out.println("상품이 존재하지 않습니다.");
			return;
		}

		User user = userManager.getUserList().get(log);
		ArrayList<Item> shoppingList = user.getCart().getShoppingList();

		Item item = itemManager.findItem();
		if (item == null) {
			return;
		}

		int ea = inputNumber("수량을 입력하세요");

		if (isFirstBuy(shoppingList, item)) {
			Item buy = item.clone();
			buy.setEa(ea);
			shoppingList.add(buy);
			System.out.println("장바구니에 상품을 추가하였습니다.");
		} else {
			setEaFromCart(shoppingList, item, ea);
			System.out.println("장바구니에 상품을 추가하였습니다.");
		}
	}

	private void setEaFromCart(ArrayList<Item> shoppingList, Item item, int ea) {
		for (int i = 0; i < shoppingList.size(); i++) {
			if (shoppingList.get(i).getName().equals(item.getName())) {
				Item target = shoppingList.get(i);
				target.setEa(target.getEa() + ea);
				break;
			}
		}
	}

	private boolean isFirstBuy(ArrayList<Item> shoppingList, Item item) {
		for (int i = 0; i < shoppingList.size(); i++) {
			if (shoppingList.get(i).getName().equals(item.getName())) {
				return false;
			}
		}
		return true;
	}

	private void printMyPageMenu() {
		System.out.println("1.내 장바구니");
		System.out.println("2.항목 삭제");
		System.out.println("3.수량 수정");
		System.out.println("4.결제");
	}

	private void printAdminMenu() {
		System.out.println("1.아이템 등록");
		System.out.println("2.아이템 삭제");
		System.out.println("3.아이템 수정");
		System.out.println("4.총 매출 조회");
	}

	private void printUserMenu() {
		System.out.println("1.회원가입");
		System.out.println("2.회원탈퇴");
		System.out.println("3.로그인");
		System.out.println("4.로그아웃");
		System.out.println("5.쇼핑");
		System.out.println("6.마이페이지");
	}

	private int inputNumber(String message) {
		int number = -1;
		System.out.print(message + " : ");
		try {
			String numberString = new Scanner(System.in).next();
			number = Integer.parseInt(numberString);
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요 ");
		}
		return number;
	}

	private void printMenu() {
		System.out.println("login :" + (log == -1 ? "none" : log + ""));
		printUserState();
		System.out.println("=======" + shopName + "=======");
		System.out.println("1.유저");
		System.out.println("2.관리자");
		System.out.println("0.시스템 종료");
	}

	private void printUserState() {
		userManager.printUserState();
	}

}
