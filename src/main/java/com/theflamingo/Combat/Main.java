package com.theflamingo.Combat;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Main extends ListenerAdapter {
	public static void main( String[] args ) throws Exception {
		
		JDA jda = new JDABuilder(AccountType.BOT).setToken(Ref.TOKEN).build();
		
		jda.getPresence().setGame(Game.playing("c help"));
		
		jda.addEventListener(new CommandNew());
		jda.addEventListener(new DebugTools());
		jda.addEventListener(new CommandAttack());
	}
}
