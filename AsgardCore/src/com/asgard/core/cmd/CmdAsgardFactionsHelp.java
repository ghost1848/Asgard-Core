package com.asgard.core.cmd;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdAsgardFactionsHelp implements CommandInterface {

	ArrayList<String> msg = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player p = (Player) sender;
		
		msg.add("§8================= §b§lASGARD FACTIONS §8=================\n" +
				"\n" +
				"§b/af broadcast [Message] §f- §8Broadcast a personal message\n" +
				"§b/af help §f- §8Displays this message\n" +
				"§b/af reload §f- §8Reloads the config\n" +
				"§b/bounty [Set/Show/Remove] (Player) (Amount) §f- §8Set/Remove bounties from others\n" +
				"§b/buy §f- §8Shows the ranks up for purchase\n" +
				"§b/discord §f- §8Sends the discord link for this server\n" +
				"§b/itemdrop §f- §8Drops random rare item somewhere on the map\n" +
				"§b/staffchat §f- §8Allows you to chat with fellow staff members\n" +
				"§b/trade [Player] §f - §8Trade with another player");
				
		p.sendMessage(msg.get(0));
		
		return true;
		
	}
	
}