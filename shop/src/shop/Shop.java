package shop;

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
		
	//Admin Submenu
	private final int ENROLL_ITEM = 1;
	private final int DELETE_ITEM = 2;
	private final int UPDATE_ITEM = 3;
	private final int PRINT_SALE_STATE = 4;
	
	//User Submenu
	private final int JOIN = 1;
	private final int  DELETE_USER = 2;
	private final int  LOG_IN = 3;
	private final int LOG_OUT = 4;
	private final int  MYPAGE = 5;
	
	//menu
	private final int USER = 1;
	private final int ADMIN = 2;
	private final int END_SYSTEM = 0;
	
	private int log;
	private boolean isRun;
	
	private UserManager userManager;
	private ItemManager itemManager;
	private FileManager fileManager;

	private String shopName;
	
	public void setLog(int log) {
		this.log = log;
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
		}else if (select == ADMIN) {
			printAdminMenu();
			int option = inputNumber("menu");
			runAdminMenu(option);
		}else if (select == END_SYSTEM) {
			isRun = false;
		}
	}
	private void runAdminMenu(int option) {
		if (option == ENROLL_ITEM) {
			itemManager.enrollItem();
		}else if (option == DELETE_ITEM) {
			itemManager.deleteItem();
		}else if (option == UPDATE_ITEM) {
			itemManager.updateItem();
		}else if (option == PRINT_SALE_STATE) {
			itemManager.printSaleSate();
		}
	}
	private void runUserMenu(int option) {
		if (option == JOIN) {
			userManager.join();
		}else if (option == DELETE_USER) {
			userManager.deleteUser();
		}else if (option == LOG_IN) {
			userManager.login();
		}else if (option == LOG_OUT) {
			userManager.logout();
		}else if (option == MYPAGE) {
			printMyPageMenu();
		}
	}
	private void printMyPageMenu() {
		// TODO Auto-generated method stub
		
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
		System.out.println("5.마이페이지");
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
