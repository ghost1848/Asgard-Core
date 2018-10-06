package com.asgard.core.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.asgard.core.Core;

public class CmdDiscord implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
		
		String message = Core.i.getConfig().getString("discord-message");
		Player player = (Player) sender;
		
		if (sender instanceof Player) {

			player.sendMessage("" + ChatColor.translateAlternateColorCodes('&', message));
		
		} else {
			
			sender.sendMessage("§4You must be a player to use this command!");
			
		}
		
		return true;
	}

}
