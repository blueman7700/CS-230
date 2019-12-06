

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
	private static String currName = "";
	private static final String FILE_PATH ="src/Files/Users.txt";
	public static String loginPage() throws Exception {
		Scanner in = new Scanner(System.in);
		String finalName = "";
		System.out.println("Pick an existing user, or a make a new one.");
		ArrayList<String> names = displayUsers();
		currName = in.nextLine();
		if(currName.equals("new")) {
			currName = in.nextLine();
			createUser(currName);
			finalName = currName;
			System.out.println(finalName+ " is the user!");
		}else {
			if(!checkName(names)) {
				finalName =loginPage();
			}else {
				finalName = currName;
				System.out.println(finalName+ " is the user!");
			}
		}
		in.close();
		
		return(finalName);

	}
	
	private static ArrayList<String> displayUsers() throws FileNotFoundException {
		ArrayList<String> names = new ArrayList<String>();
		File file = new File(FILE_PATH);
		System.out.println("test");
		Scanner in = new Scanner(file);
		while(in.hasNextLine()) {
			currName = in.next();
			names.add(currName);
			System.out.println("User: "+currName+" , Highest Level: "+in.nextInt());
			
		}
		in.close();
		return names;
	}
	
	private static boolean checkName(ArrayList<String> names) {
		boolean isUser = false;
		for(int i = 0;i<names.size();i++) {
			if(names.get(i).equals(currName)) {
				isUser = true;
			}
		}
		return isUser;
	}
	
	private static void createUser(String name) throws IOException {
		if(name.trim().length() > 0) {
			String content = name+ " 0"; 
			BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH,true));
			writer.newLine();
			writer.write(content);
			writer.close();
		}
		
	}
	
	public static void loginTwo(String name) throws Exception {
		ArrayList<String> names = displayUsers();
		currName = name;
		if(checkName(names)) {
			System.out.println(name+ " is the user!");
		}else { 
			createUser(name);
			System.out.println(name+ " is the user!");
		}
	}
	
	public static void delete(String name) {
		
	}
	
	public static ArrayList<String> getUsers() throws FileNotFoundException {
		ArrayList<String> names = new ArrayList<String>();
		File file = new File(FILE_PATH);
		System.out.println("test");
		Scanner in = new Scanner(file);
		while(in.hasNextLine()) {
			currName = in.next();
			names.add("User: "+currName+" , Highest Level: "+in.nextInt());
			
		}
		in.close();
		return names;
	}
	
	
	
}