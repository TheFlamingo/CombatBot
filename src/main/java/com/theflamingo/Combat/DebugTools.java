package com.theflamingo.Combat;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DebugTools extends ListenerAdapter {
	
	public void onMessageReceived(MessageReceivedEvent evt) {
		
		Message msg = evt.getMessage();
		String[] strArgs = msg.getContentRaw().split(" ");
			
		try {
			if ((strArgs[0] + strArgs[1] + strArgs[2]).equals(Ref.PREFIX + "debug" + "users")) {
				
				if (strArgs.length == 3) {
					displayUserList();
				} else {
					System.out.println("error in debug tools users**");
				}
			}
			else if ((strArgs[0] + strArgs[1] + strArgs[2]).equals(Ref.PREFIX + "debug" + "additem")) {
				
				if (strArgs.length == 5) {
					addItem(strArgs, evt);
				} else {
					System.out.println("error in debug tools additem**");
				}
			}
			else if ((strArgs[0] + strArgs[1] + strArgs[2]).equals(Ref.PREFIX + "debug" + "items")) {
				
				if (strArgs.length == 3) {
					displayItemList();
				} else {
					System.out.println("error in debug tools additem**");
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
	
	//assigns new variables from strArgs that will be passed to Databases.createItem()
	public void addItem(String[] strArgs, MessageReceivedEvent evt) {
		
		//variables that will be passed to createItem()
		String itemName, itemDamage;
		itemName = strArgs[3];
		itemDamage = strArgs[4];
		
		Databases.createItem(itemName, itemDamage, evt);
	}
	
	//only displays user names currently. change this comment once other attributes are added.
	public void displayUserList() {
		
		for (int i = 0; i < Databases.users.size(); i++) {
			System.out.println(Databases.users.get(i).get(0));
		}
	}
	
	//cycles through item database and prints item name, item damage, and a space to the console.
	public void displayItemList() {
		
		for (int i = 0; i < Databases.items.size(); i++) {
			System.out.println(Databases.items.get(i).get(0));
			System.out.println(Databases.items.get(i).get(1));
			System.out.println();
		}
	}
	
}
