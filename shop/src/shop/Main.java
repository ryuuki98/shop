package shop;

import java.io.IOException;

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

public class Main {
	public static void main(String[] args) throws IOException {
		Shop shop = new Shop("Mega");
		shop.run();
	}
}
