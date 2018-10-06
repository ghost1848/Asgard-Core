package com.asgard.core.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.asgard.core.Core;

public class CmdAsgardFactionsReload  implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player p = (Player) sender;
		
		if (args.length > 1) return false;

		Core.i.reloadConfig();
		p.sendMessage("§b§lAsgard Factions §8» §f§oConfig reloaded.");
		
		return false;
		
	}

}
