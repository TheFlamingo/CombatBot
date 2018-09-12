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
				if (inputVerification(strArgs, evt.getAuthor(), evt)) {
					//calls Databases methods to retrieve the index of specified user and specified item. These are then passed
					//to attackPlayer for the actual damage to be calculated and the user list to be changed.
					int userIndex = Databases.getUserIndex(strArgs[2]);
					int itemIndex = Databases.getItemIndex(strArgs[3]);
					
					attackPlayer(userIndex, itemIndex);
					sendAttackCompleteMessage(evt, strArgs);
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
	private boolean inputVerification(String[] strArgs, User author, MessageReceivedEvent evt) {
		
		if (strArgs.length == 4 && Databases.checkUserExistance(author) && Databases.checkItemExistance(strArgs[3].toLowerCase())) {
			return true;
		} else {
			
			// Checks if the issuer of the command has created an account with c new
			if (!Databases.checkUserExistance(author)) {
				ErrorMessages.sendAccountNotCreatedErrorMessage(evt);
				return false;
			// Checks if the user referenced in the command is in the database
			} else if (!Databases.checkUserExistance(evt.getJDA().getUsersByName(strArgs[2], true).get(0))) {
				ErrorMessages.sendUserNotFoundErrorMessage(evt, strArgs[2]);
				return false;
			// Checks if the item referenced in the command is in the database
			} else if (!Databases.checkItemExistance(strArgs[3].toLowerCase())) {
				ErrorMessages.sendItemNotFoundErrorMessage(evt, strArgs[3]);
				return false;
			}
			return false;
		}
	}
	
	private void sendAttackCompleteMessage(MessageReceivedEvent evt, String[] strArgs) {
		int itemIndex = Databases.getItemIndex(strArgs[3]);
		
		EmbedBuilder build = new EmbedBuilder();
		build.setTitle("Attack successful");
		build.setDescription("Attacked " + strArgs[2] + " with " + strArgs[3] + " for " + Databases.items.get(itemIndex).get(1) + " damage.");
		
		evt.getChannel().sendMessage(build.build()).queue();
	}
}
