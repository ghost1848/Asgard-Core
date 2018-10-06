package com.asgard.core.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.asgard.core.inventories.RankInventory;

public class CmdBuy implements CommandExecutor {
	
	RankInventory ri = new RankInventory();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		
		if (sender instanceof Player) {
			
			ri.rankInventory(player);
			return true;
			
		} else {
			
			sender.sendMessage("§4You must be a player to use this command!");
			
		}
		
		return false;
		
	}
	
}
