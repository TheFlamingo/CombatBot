package com.theflamingo.Combat;

import java.util.ArrayList;
import java.util.List;

public class Databases {
	
	//format: {'user-name, 'health'}
	public static List<List<String>> users = new ArrayList<List<String>>(); 
	//stores what items each user has. user at index 0 will have their items stored at index 0 in this database.
	public static  List<List<String>> userItems = new ArrayList<List<String>>(); 
	//format: {'item-name', 'damage', 'extra-info'}
	public static List<List<String>> items = new ArrayList<List<String>>();
	
	//creates a new String List that will contain a new user's info.
	public static void createUserStringList() {
		users.add(new ArrayList<String>());
	}
	
	//gets the newest index in users db
	public static int getLatestIndexUsers() {
		
		int lastIndex = users.size() - 1; 
		return lastIndex;
	}
	
	//gets the newest index in userItems db
	public static int getLatestIndexUserItems() {
		
		int lastIndex = userItems.size() - 1; 
		return lastIndex;
	}
	
	//gets the newest index in items db
	public static int getLatestIndexItems() {
		
		int lastIndex = items.size() - 1; 
		return lastIndex;
	}
}
