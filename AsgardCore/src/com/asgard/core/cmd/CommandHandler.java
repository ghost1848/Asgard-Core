package com.asgard.core.cmd;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

	private static HashMap<String, CommandInterface> commands = new HashMap<String, CommandInterface>();
	
	public void register(String name, CommandInterface cmd) {
		
		commands.put(name, cmd);
		
	}
	
	public boolean exists(String name) {
		
		return commands.containsKey(name);
		
	}
	
	public CommandInterface getExecutor(String name) {
		
		return commands.get(name);
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (sender instanceof Player) {
			
			if (args.length == 0) {
				
				getExecutor("AsgardFactions").onCommand(sender, cmd, commandLabel, args);
				return true;
				
			}
			
			if (args.length > 0) {
				
				if (exists(args[0])) {
					
					getExecutor(args[0]).onCommand(sender, cmd, commandLabel, args);
					return true;
					
				} else {
					
					sender.sendMessage("§bThe argument §5" + args[0] + " §bdoes not exist!");
					return true;
					
				}
				
			}
			
		} else {
			
			sender.sendMessage("§cYou must be a player to use this command.");
			return true;
			
		}
		
		return false;
		
	}
	
}
