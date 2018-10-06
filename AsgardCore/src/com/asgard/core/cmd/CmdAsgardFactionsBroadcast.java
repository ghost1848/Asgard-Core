package com.asgard.core.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.asgard.core.Core;

public class CmdAsgardFactionsBroadcast implements CommandInterface {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		StringBuilder msg = new StringBuilder("");
		Player p = (Player) sender;
			
		if (args.length > 1) {
				
			for (String part : args) {
					
				if (!msg.toString().equals("")) msg.append(" ");
				msg.append(part);
					
			}
				
			msg.delete(0, 10);
			Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Core.i.getConfig().getString("broadcast-message")  + msg.toString()));
			return false;
				
		}
	
		p.sendMessage("§b§o/af broadcast [Message]");
		
		return true;
		
	}
	
}
