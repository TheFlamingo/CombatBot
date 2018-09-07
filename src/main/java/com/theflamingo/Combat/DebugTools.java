package com.theflamingo.Combat;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DebugTools extends ListenerAdapter {
	
	public void onMessageReceived(MessageReceivedEvent evt) {
		
		Message msg = evt.getMessage();
		String[] strArgs = msg.getContentRaw().split(" ");
			
		try {
			if ((strArgs[0] + strArgs[1] + strArgs[2]).equals(Ref.PREFIX + "display" + "users")) {
				
				if (strArgs.length == 3) {
					displayUserList(evt);
				} else {
					System.out.println("error in debug tools");
				}
				
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
	
	//only displays user names currently. change this comment once other attributes are added.
	public void displayUserList(MessageReceivedEvent evt) {
		
		for (int i = 0; i < Databases.users.size(); i++) {
			System.out.println(Databases.users.get(i).get(0));
		}
	}
}
