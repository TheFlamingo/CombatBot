package com.theflamingo.Combat;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandNew extends ListenerAdapter {
	
	public void onMessageReceived(MessageReceivedEvent evt) {
		
		Message msg = evt.getMessage();
		String[] strArgs = msg.getContentRaw().split(" ");
			
		try {
			if ((strArgs[0] + strArgs[1]).equals(Ref.PREFIX + "new")) {
				if (strArgs.length == 2 && checkNewUser(evt.getAuthor())) {
					
					createUser(evt);
				} else {
					sendErrorMessage(evt);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
	
	private void createUser(MessageReceivedEvent evt) {
		
		//see Databases.createUserStringList comment
		Databases.createUserStringList();
		
		//gets the newest index (the one we just created) in users list. This is the List that will be added to.
		int lastIndex = Databases.getLatestIndexUsers();
		//creates new string in newly created String List. This new string holds the user's user name.
		Databases.users.get(lastIndex).add(evt.getAuthor().getName());
		
		//debug message
		System.out.println(Databases.users.get(lastIndex).get(0));
		
		sendCompleteMessage(evt);
		
	}
	
	//checks if the user trying to create an account is already in the system, by checking if their user name matches anything in the Users list
	private boolean checkNewUser(User user) {
		for (int i = 0; i < Databases.users.size(); i++) {
			if (Databases.users.get(i).get(0).equals(user.getName())) return false;
		}
		
		return true;
	}
	
	private void sendCompleteMessage(MessageReceivedEvent evt) {
		
		EmbedBuilder build = new EmbedBuilder();
		build.setTitle("Done");
		build.setDescription("Account created");
		
		evt.getChannel().sendMessage(build.build()).queue();
		
	}
	
	//if any errors are found, the flow is redirected here. Sends embedded message with proper usage.
	private void sendErrorMessage (MessageReceivedEvent evt) {
		
		EmbedBuilder build = new EmbedBuilder();
		build.setTitle("Invalid Usage");
		build.addField("Proper usage:", "c add", false);
		
		evt.getChannel().sendMessage(build.build()).queue();
		
	}
}



