package com.theflamingo.Combat;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DebugTools extends ListenerAdapter {

	public void onMessageReceived(MessageReceivedEvent evt) {

		Message msg = evt.getMessage();
		String[] strArgs = msg.getContentRaw().split(" ");

		try {
			// c debug users - displays a list of all users in users database
			if ((strArgs[0] + strArgs[1] + strArgs[2]).equals(Ref.PREFIX + "debug" + "users")) {

				if (strArgs.length == 3) {
					displayUserList();
				} else {
					System.out.println("error in debug tools users**");
				}
			}
			// c debug additem - adds an item to the item database
			else if ((strArgs[0] + strArgs[1] + strArgs[2]).equals(Ref.PREFIX + "debug" + "additem")) {

				if (strArgs.length == 5) {
					addItem(strArgs, evt);
				} else {
					System.out.println("error in debug tools additem**");
				}
				// c debug removeitem - removes an item from the item database
			} else if ((strArgs[0] + strArgs[1] + strArgs[2]).equals(Ref.PREFIX + "debug" + "removeitem")) {
				
				if (strArgs.length == 4) {
					removeItem(strArgs, evt);
				} else {
					System.out.println("error in debug tools removeitem**");
				}
			}
			// c debug itmes - displays a list of all items in items database
			else if ((strArgs[0] + strArgs[1] + strArgs[2]).equals(Ref.PREFIX + "debug" + "items")) {

				if (strArgs.length == 3) {
					displayItemList();
				} else {
					System.out.println("error in debug tools additem**");
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	// assigns new variables from strArgs that will be passed to
	// Databases.createItem()
	public void addItem(String[] strArgs, MessageReceivedEvent evt) {

		// variables that will be passed to createItem()
		String itemName, itemDamage;
		itemName = strArgs[3];
		itemDamage = strArgs[4];

		Databases.createItem(itemName, itemDamage, evt);
	}

	// removes the indicated item from the database
	public void removeItem(String[] strArgs, MessageReceivedEvent evt) {

		// variable that will be passed to removeItem()
		String itemName;
		itemName = strArgs[3];

		Databases.removeItem(itemName, evt);
	}

	// only displays user names currently. change this comment once other
	// attributes are added.
	public void displayUserList() {
		
		if (Databases.users.size() == 0) {
			System.out.println("No users currently in user database");
		}
		
		for (int i = 0; i < Databases.users.size(); i++) {
			System.out.println(Databases.users.get(i).get(0));
			System.out.println(Databases.users.get(i).get(1));
			System.out.println();
		}
	}

	// cycles through item database and prints item name, item damage, and a
	// space to the console.
	public void displayItemList() {
		
		if (Databases.items.size() == 0) {
			System.out.println("No items currently in item database");
		}
		
		for (int i = 0; i < Databases.items.size(); i++) {
			System.out.println(Databases.items.get(i).get(0));
			System.out.println(Databases.items.get(i).get(1));
			System.out.println();
		}
	}
}
