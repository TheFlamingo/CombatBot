package com.theflamingo.Combat;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandAttack extends ListenerAdapter {
	
	public void onMessageReceived(MessageReceivedEvent evt) {
		
		Message msg = evt.getMessage();
		String[] strArgs = msg.getContentRaw().split(" ");
			
		try {
			if ((strArgs[0] + strArgs[1]).equals(Ref.PREFIX + "attack")) {
				if (inputVerification(strArgs, evt.getAuthor())) {
					//calls Databases methods to retrieve the index of specified user and specified item. These are then passed
					//to attackPlayer for the actual damage to be calculated and the user list to be changed.
					int userIndex = Databases.getUserIndex(strArgs[2]);
					int itemIndex = Databases.getItemIndex(strArgs[3]);
					
					attackPlayer(userIndex, itemIndex);
				} else {
					sendErrorMessage(evt);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
	
	//replaces index[1] of the user list with a newly calculated health (current health - item damage)
	private void attackPlayer(int userIndex, int itemIndex) {
		
		int currentUserHealth = Integer.parseInt(Databases.users.get(userIndex).get(1));
		int itemDamage = Integer.parseInt(Databases.items.get(itemIndex).get(1));
		
		String newUserHealth = Integer.toString(currentUserHealth - itemDamage);
		
		Databases.users.get(userIndex).set(1, newUserHealth);
	}

	//checks that strArgs length is correct, that the specified user exists and that the specified item exists. If any of these
	//checks returns false, the check fails and sendErrorMessage() is called.
	private boolean inputVerification(String[] strArgs, User author) {
		
		if (
			strArgs.length == 4 &&
			Databases.checkUserExistance(author) &&
			Databases.checkItemExistance(strArgs[3])) {
			return true;
		} else {
			return false;
		}
	}
	
	//if any errors are found, the flow is redirected here. Sends embedded message with proper usage.
	private void sendErrorMessage (MessageReceivedEvent evt) {
		
		EmbedBuilder build = new EmbedBuilder();
		build.setTitle("Invalid Usage");
		build.addField("Proper usage:", "c attack {user} {item-name}", false);
		
		evt.getChannel().sendMessage(build.build()).queue();
		
	}
}
