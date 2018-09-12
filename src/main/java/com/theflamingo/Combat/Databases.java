package com.theflamingo.Combat;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Databases {

	/*
	 * Visualization of items List structure
	 * 
	 * Item List - index 0 List - itemName - itemDamage - index 1 List -
	 * itemName - itemDamage
	 */

	// format: {'user-name, 'health'}
	public static List<List<String>> users = new ArrayList<List<String>>();
	
	// stores what items each user has. user at index 0 will have their items
	// stored at index 0 in this database.
	public static List<List<String>> userItems = new ArrayList<List<String>>();
	
	// format: {'item-name', 'damage', 'extra-info'}
	public static List<List<String>> items = new ArrayList<List<String>>();

	// creates a new String List that will contain a new user's info.
	public static void createUserStringList() {
		users.add(new ArrayList<String>());
	}

	public static void createItemStringList() {
		items.add(new ArrayList<String>());
	}

	// gets the newest index in users db
	public static int getLatestIndexUsers() {

		int lastIndex = users.size() - 1;
		return lastIndex;
	}

	// gets the newest index in userItems db
	public static int getLatestIndexUserItems() {

		int lastIndex = userItems.size() - 1;
		return lastIndex;
	}

	// gets the newest index in items db
	public static int getLatestIndexItems() {

		int lastIndex = items.size() - 1;
		return lastIndex;
	}
	
	//cycles through user lists and checks the first index of each one for the specified user. When it is found, the index is returned
	public static int getUserIndex(String userName) {
		
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).get(0).equals(userName)) {
				return i;
			}
		}
		
		//this will never happen due to checkUserExistance. Just here to deal with not all code paths return a value error
		return 0;
	}
	
	//see above. Same thing, but with item db
	public static int getItemIndex(String itemName) {
		
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).get(0).equals(itemName)) {
				return i;
			}
		}
		
		//this will never happen due to checkItemExistance. Just here to deal with not all code paths return a value error
		return 0;
	}
	
	// used by CommandNew to check if the user trying to create an account is
	// already in the system, by checking if their user name matches anything in
	// the Users list
	// used by CommandAttack to check if a user is in the database in general,
	// to verify that the specified user to attack is in the system
	public static boolean checkUserExistance(User user) {

		// Prevents the for loop from running if the length of the users array is less than 1 to avoid an ArrayIndexOutOfBoundsException
		if (Databases.items.size() > 0) {
			for (int i = 0; i < Databases.users.size(); i++) {
				if (Databases.users.get(i).get(0).equalsIgnoreCase(user.getName()))
					return true;
			}
		}

		return false;
	}
	
	//checks that the specified item exists in the item database.
	public static boolean checkItemExistance(String item) {

		// Prevents the for loop from running if the length of the items array is less than 1 to avoid an ArrayIndexOutOfBoundsException
		if (Databases.items.size() > 0) {
			for (int i = 0; i < Databases.items.size(); i++) {
				if (Databases.items.get(i).get(0).equalsIgnoreCase(item))
					return true;
			}
		}
		
		return false;
	}

	public static void createItem(String itemName, String itemDamage, MessageReceivedEvent evt) {

		// creates a new String List that will store the new item
		items.add(new ArrayList<String>());

		// adds the item name at index 0 in newly created String List
		items.get(getLatestIndexItems()).add(0, itemName.toLowerCase());

		// checks that itemDamage contains only numbers, and adds itemDamage to
		// index 1 in the newly created String List
		if (itemDamage.matches("^[0-9]+$"))
			items.get(getLatestIndexItems()).add(1, itemDamage);
		else
			ErrorMessages.sendCreateItemErrorMessage(evt);
	}

	public static void removeItem(String itemName, MessageReceivedEvent evt) {

		for (int i = 0; i < items.size(); i++) {
			// If the string at the first index in items.get(i) equals the
			// itemName argument, remove the item List from items.
			if (items.get(i).get(0).equalsIgnoreCase(itemName)) {
				items.remove(i);
				return;
			}
		}

		// Reach this only if the for loop goes all the way through without
		// finding a match.
		ErrorMessages.sendItemNotFoundErrorMessage(evt, itemName);
	}

	
}
