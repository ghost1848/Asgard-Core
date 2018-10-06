package com.asgard.core.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdStaffChat implements CommandInterface {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player p = (Player) sender;
		
		p.sendMessage("§bPlease use §5/sc ");
		
		return false;
		
	}
	
}
