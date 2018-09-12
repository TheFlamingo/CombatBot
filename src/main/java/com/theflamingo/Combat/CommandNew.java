package com.theflamingo.Combat;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandNew extends ListenerAdapter {
	
	public void onMessageReceived(MessageReceivedEvent evt) {
		
		Message msg = evt.getMessage();
		String[] strArgs = msg.getContentRaw().split(" ");
			
		try {
			if ((strArgs[0] + strArgs[1]).equals(Ref.PREFIX + "new")) {
				if (strArgs.length == 2 && !Databases.checkUserExistance(evt.getAuthor())) {
					
					createUser(evt);
				} else {
					ErrorMessages.sendAddErrorMessage(evt);
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
		Databases.users.get(lastIndex).add(0, evt.getAuthor().getName());
		Databases.users.get(lastIndex).add(1, "100");
		
		//debug message
		System.out.println(Databases.users.get(lastIndex).get(0));
		
		sendAccountCompleteMessage(evt);
		
	}
	
	public static void sendAccountCompleteMessage(MessageReceivedEvent evt) {
		
		EmbedBuilder build = new EmbedBuilder();
		build.setTitle("Done");
		build.setDescription("Account created");
		
		evt.getChannel().sendMessage(build.build()).queue();
		
	}
}



