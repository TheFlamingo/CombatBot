package com.theflamingo.Combat;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ErrorMessages {
	
		//if any errors are found in the attack command, the flow is redirected here. Sends embedded message with proper usage.
		public static void sendAttackErrorMessage (MessageReceivedEvent evt) {
			
			EmbedBuilder build = new EmbedBuilder();
			build.setTitle("Invalid Usage");
			build.addField("Proper usage:", "c attack {user} {item-name}", false);
			
			evt.getChannel().sendMessage(build.build()).queue();
		}
		
		//if an item fails to be created, the flow is redirected here.
		public static void sendCreateItemErrorMessage(MessageReceivedEvent evt) {

			EmbedBuilder build = new EmbedBuilder();
			build.setTitle("Error");
			build.setDescription("Couldn't add item to database");

			evt.getChannel().sendMessage(build.build()).queue();
		}
		
		//if an item could not be found in the database, the flow is redirected here.
		public static void sendItemNotFoundErrorMessage(MessageReceivedEvent evt, String itemName) {

			EmbedBuilder build = new EmbedBuilder();
			build.setTitle("Error");
			build.setDescription("Couldn't find any item by the name " + itemName);

			evt.getChannel().sendMessage(build.build()).queue();
		}
		
		//if a user could not be found in the database, the flow is redirected here.
		public static void sendUserNotFoundErrorMessage(MessageReceivedEvent evt, String userName) {
			
			EmbedBuilder build = new EmbedBuilder();
			build.setTitle("Error");
			build.setDescription("Couldn't find any user by the name " + userName);

			evt.getChannel().sendMessage(build.build()).queue();
		}
		
		public static void sendAccountNotCreatedErrorMessage(MessageReceivedEvent evt) {
			
			EmbedBuilder build = new EmbedBuilder();
			build.setTitle("Error");
			build.setDescription("You have not created an account. Use c new to create one.");

			evt.getChannel().sendMessage(build.build()).queue();
		}
		
		//if any errors are found in the add command, the flow is redirected here. Sends embedded message with proper usage.
		public static void sendAddErrorMessage (MessageReceivedEvent evt) {
			
			EmbedBuilder build = new EmbedBuilder();
			build.setTitle("Invalid Usage");
			build.addField("Proper usage:", "c add", false);
			
			evt.getChannel().sendMessage(build.build()).queue();
		}
}
