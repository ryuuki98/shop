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
	private final int USER = 1;
	private final int ADMIN = 2;
	private String shopName;
	public Shop(String shopName) {
		this.shopName = shopName;
	}
	public void run() {
		while (true) {
			printMenu();
			int select = inputNumber("menu");
			runMenu(select);
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
		}
	}
	private void runAdminMenu(int option) {
		// TODO Auto-generated method stub
		
	}
	private void runUserMenu(int option) {
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
		System.out.println("=======" + shopName + "=======");
		System.out.println("1.유저");
		System.out.println("2.관리자");
	}

}
