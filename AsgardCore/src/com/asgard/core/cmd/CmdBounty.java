package com.asgard.core.cmd;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.asgard.core.Core;
import com.earth2me.essentials.User;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

public class CmdBounty implements CommandExecutor, Listener {
	
	Player target;
	User player;
	
	Map<UUID, Double> bounties = new HashMap<UUID, Double>();
	Map<UUID, Boolean> hasBounties = new HashMap<UUID, Boolean>();
	Map<String, String> whoSentIt = new HashMap<String, String>();

	public CmdBounty() {
		
		for (Player p : Core.i.getServer().getOnlinePlayers()) {
				
			bounties.put(p.getUniqueId(), 0.00);
			hasBounties.put(p.getUniqueId(), false);
				
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (sender instanceof Player) {

			if ((args.length >= 0 && args.length < 3) || args.length > 3) {
				
				sender.sendMessage("§bPlease use the command §5/bounty [Set/Remove] [Player] [Amount]§b.");
				
			}
			
			if (args.length == 3) {
				
				if (args[0].equalsIgnoreCase("set")) {
					
					target = Bukkit.getPlayer(args[1]);
					
					if (args[1].equalsIgnoreCase(target.getName()) && target.isOnline()) {
							
						try {
							
							if (Economy.getMoney(sender.getName()) >= Double.parseDouble(args[2])) {
							
								whoSentIt.put(target.getName(), ((Player) sender).getName());
								hasBounties.put(target.getUniqueId(), true);
								bounties.put(target.getUniqueId(), Double.parseDouble(args[2]));
									
								sender.sendMessage("§bYou have placed a bounty on §5" + target.getName() + " §b for §5$" + bounties.get(target.getUniqueId()) + "§b.");
								Bukkit.getServer().broadcastMessage("§b§lAsgard Factions §8" + "\u00BB" + " §bA bounty has been placed on §5" + target.getName() + " §bfor §5$" + bounties.get(target.getUniqueId()) + "§b.");

							} else {
								
								sender.sendMessage("§bYou do not have enough money to set that bounty!");
								
							}
							
						} catch (NumberFormatException e) {
							
							e.printStackTrace();
							
						} catch (UserDoesNotExistException e) {
							
							e.printStackTrace();
							
						}
					
					} else {
						
						sender.sendMessage("§5" + target.getName() + " §bis either offline, or doesn't exist!");
						
					}
					
				}
				
				if (args[0].equalsIgnoreCase("remove")) {
					
					target = Bukkit.getPlayer(args[1]);
					
					if (args[1].equalsIgnoreCase(target.getName()) && target.isOnline()) {
						
						if (!args[2].equalsIgnoreCase(Double.toString(bounties.get(target.getUniqueId())))) {
							
							sender.sendMessage("§bIf you do not know the player's bounty please use §5/bounty show [Player] §bto get their bounty.");
							
						} else {
							
							hasBounties.put(target.getUniqueId(), false);
							bounties.put(target.getUniqueId(), 0.00);
							
							sender.sendMessage("§bYou have removed the bounty from §5" + target.getName() + "§b.");
							Bukkit.getServer().broadcastMessage("§b§lAsgard Factions §8 " + "\u00BB" + " §5" + target.getName() + "§b's bounty has been removed");
							
						}
						
					} else {
						
						sender.sendMessage("§5" + target.getName() + " §bis either offline, or doesn't exist!");
						
					}
					
				}
				
				if (args[0].equalsIgnoreCase("show")) {
					
					target = Bukkit.getPlayer(args[1]);
					
					sender.sendMessage("§5" + target.getName() + "§b's bounty is §5$" + bounties.get(target.getUniqueId()) + " §b and was set by §5" + whoSentIt.get(target.getName()) + "§b.");
					
				}
				
			}
			
		}
		
		return true;
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void isDeadWithBounty(PlayerDeathEvent event) {
		
		Player player = event.getEntity();
		
		if ((player.getKiller() instanceof Player) && hasBounties.get(player.getUniqueId())) {

			
			try {
				Economy.add(player.getKiller().getName(), bounties.get(player.getUniqueId()));
				Economy.subtract(whoSentIt.get(player.getName()), bounties.get(player.getUniqueId()));
				hasBounties.put(player.getUniqueId(), false);
				bounties.put(player.getUniqueId(), 0.00);
			} catch (NoLoanPermittedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UserDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			
		} else {
			
			return;
			
		}
		
	}
	
}
