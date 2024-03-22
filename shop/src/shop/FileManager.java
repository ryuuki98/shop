package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	private UserManager userManager;
	private ItemManager itemManager;

	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	private File file;
	private String fileName;

	public FileManager() {
		userManager = Shop.getUserManager();
		itemManager = Shop.getItemManager();
		fileName = "shop.txt";
		file = new File(fileName);
	}

	
	/*
	 * [저장형식]
	 * 물품/가격,물품/가격,물품/가격...
	 * 이름/아이디/비밀번호/물품,가격,갯수/물품,가격,갯수/
	 * 이름/아이디/비밀번호
	 */
	public void save() {
		try {
			fileWriter = new FileWriter(file);
			String data = "";
			int itemSize = itemManager.getItemlist().size();
			if (itemSize==0) {
				data += "null" ;
			}
			for (int i = 0; i < itemSize; i++) {
				Item item = itemManager.getItemlist().get(i);
				data += item.getName() + "," + item.getPrice();
				if (i < itemSize - 1) {
					data += "/";
				}
			}
			data +=  "/" + itemManager.getTotalPrice();
			data += "\n";

			int userSize = userManager.getUserList().size();
			for (int i = 0; i < userSize; i++) {
				User user = userManager.getUserList().get(i);
				data += user.getName() + "/" + user.getId() + "/" + user.getPassword();
				ArrayList<Item> shoppingList = user.getCart().getShoppingList();
				if (shoppingList.size() > 0) {
					data += "/";
				}
				for (int j = 0; j < shoppingList.size(); j++) {
					Item item = shoppingList.get(j);
					data += item.getName() + "," + item.getPrice() + "," + item.getEa();
					if (j < shoppingList.size() - 1) {
						data += "/";
					}
				}
				if (i < userSize - 1) {
					data += "\n";
				}
			}

			fileWriter.write(data);
			fileWriter.close();
			System.out.println("저장완료");

		} catch (IOException e) {
			System.out.println("저장실패");
			e.printStackTrace();
		}

	}

	public void load() throws IOException {
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			if (file.exists()) {
				String[] itemListArr = bufferedReader.readLine().split("/");
				if (itemListArr[0].equals("null")) {
					itemManager.setTotalPrice(Integer.parseInt(itemListArr[1]));
				}else {
					for (int i = 0; i < itemListArr.length-1; i++) {
						String[] itemInfo = itemListArr[i].split(",");
						String name = itemInfo[0];
						int price = Integer.parseInt(itemInfo[1]);
						Item item = new Item(name, price);
						itemManager.getItemlist().add(item);
					}
					itemManager.setTotalPrice(Integer.parseInt(itemListArr[itemListArr.length-1]));
				}
				
				while (bufferedReader != null) {
					String[] userInfo = bufferedReader.readLine().split("/");
					String name = userInfo[0];
					
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
