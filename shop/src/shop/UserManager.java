package shop;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
	private ArrayList<User> userList;
	public UserManager() {
		userList = new ArrayList<User>();
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
		// TODO Auto-generated method stub
		
	}

	public void login() {
		// TODO Auto-generated method stub
		
	}

	public void logout() {
		// TODO Auto-generated method stub
		
	}



}
