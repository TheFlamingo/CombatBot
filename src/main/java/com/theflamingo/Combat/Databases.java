package com.theflamingo.Combat;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Databases {
	
	/*
	 * Visualization of items List structure
	 * 
	 * Item List
	 * 	- index 0 List
	 * 		- itemName
	 * 		- itemDamage
	 * 	- index 1 List
	 * 		- itemName
	 * 		- itemDamage
	 */
	
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
	public static void createItemStringList() {
		items.add(new ArrayList<String>());
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
	
	public static void createItem(String itemName, String itemDamage, MessageReceivedEvent evt) {
		
		//creates a new String List that will store the new item
		items.add(new ArrayList<String>());
		
		//adds the item name at index 0 in newly created String List
		items.get(getLatestIndexItems()).add(0, itemName);
		
		//checks that itemDamage contains only numbers, and adds itemDamage to index 1 in the newly created String List
		if (itemDamage.matches("^[0-9]+$")) items.get(getLatestIndexItems()).add(1, itemDamage);
		else sendCreateItemErrorMessage(evt);
	}
	
	public static void removeItem(String itemName, MessageReceivedEvent evt) {
		
		for (int i = 0; i < items.size(); i++) {
			// If the string at the first index in items.get(i) equals the itemName argument, remove the item List from items.
			if (items.get(i).get(0).equals(itemName)) {
				items.remove(i);
				return;
			}
		}
		
		// Reach this only if the for loop goes all the way through without finding a match.
		sendRemoveItemErrorMessage(evt);
	}
	
	private static void sendCreateItemErrorMessage(MessageReceivedEvent evt) {
		
		EmbedBuilder build = new EmbedBuilder();
		build.setTitle("Error");
		build.setDescription("Couldn't add item to database");
		
		evt.getChannel().sendMessage(build.build()).queue();
	}
	
	private static void sendRemoveItemErrorMessage(MessageReceivedEvent evt) {
		
		EmbedBuilder build = new EmbedBuilder();
		build.setTitle("Error");
		build.setDescription("Couldn't find any item by the name provided");
		
		evt.getChannel().sendMessage(build.build()).queue();
	}
}
