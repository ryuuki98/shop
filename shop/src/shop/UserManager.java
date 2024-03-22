package shop;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
	private ArrayList<User> userList;
	private User Admin;

	public UserManager() {
		userList = new ArrayList<User>();
		Admin = new User("admin", "admin", "1111");
		userList.add(Admin);
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void join() {
		String id = inputString("id");
		User user = findUserById(id);
		if (user != null) {
			System.out.println("아이디가 중복됩니다.");
			return;
		}
		String password = inputString("password");
		String name = inputString("name");
		user = new User(name, id, password);
		userList.add(user);
		System.out.println(name + "님 가입이 완료 되었습니다.");
	}

	private User findUserById(String id) {
		User user = null;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getId().equals(id)) {
				user = userList.get(i);
			}
		}
		return user;
	}

	private String inputString(String message) {
		System.out.print(message + " : ");
		return new Scanner(System.in).next();
	}

	public void deleteUser() {
		User user = searchUser();
		if (user.getName().equals("admin")) {
			System.out.println("관리자 계정은 탈퇴하실 수 없습니다.");
			return;
		}
		if (user == null) {
			System.out.println("아이디가 존재하지 않습니다.");
			return;
		}

		String password = inputString("password");
		if (isValidLogin(user, password)) {
			userList.remove(user);
			System.out.println(user.getName() + "님 탈퇴가 완료 되었습니다.");
			Shop.setLog(-1);
		}
	}

	private boolean isValidLogin(User user, String password) {
		if (user.getPassword().equals(password)) {
			return true;
		}
		System.out.println("비밀번호가 일치하지 않습니다.");
		return false;
	}

	private User searchUser() {
		User user = null;
		String id = inputString("id");
		user = findUserById(id);

		return user;
	}

	public void login() {
		User user = searchUser();
		if (user == null) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return;
		}

		String password = inputString("password");
		if (isValidLogin(user, password)) {
			int log = findIndexByUser(user);
			Shop.setLog(log);
			System.out.println("로그인이 완료 되었습니다");

		}
	}

	private int findIndexByUser(User user) {
		int index = -1;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) == user) {
				index = i;
			}
		}
		return index;
	}

	public void logout() {
		Shop.setLog(-1);
		System.out.println("로그아웃이 완료 되었습니다.");
	}

	public void printUserState() {
		System.out.println("=================");
		System.out.println("userCount : " + userList.size());
		System.out.println("=================");
	}

}
