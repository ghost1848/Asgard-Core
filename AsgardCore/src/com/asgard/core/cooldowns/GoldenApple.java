package com.asgard.core.cooldowns;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.asgard.core.Core;

public class GoldenApple implements Listener {
	
	private HashMap<Player, Integer> cooldownTime = new HashMap<Player, Integer>();
	private HashMap<Player, BukkitRunnable> cooldownTask = new HashMap<Player, BukkitRunnable>();
	
	private int duration;
	
	public GoldenApple (Core plugin) {
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e) {
		
		Player p = e.getPlayer();
		
		cooldownTime.put(p, Core.i.getConfig().getInt("golden-apple"));
		
		if (e.getItem().getType() == Material.GOLDEN_APPLE) {
			
			//===============================================
			//         Grabs item from main/off hand
			//   Removes one of those items from inventory
			//===============================================
			ItemStack itemInHand;
			boolean itemInMainHand;
			if (p.getInventory().getItemInMainHand().getType() == e.getItem().getType()) {
				
				itemInHand = p.getInventory().getItemInMainHand();
				itemInMainHand = true;
				
			} else {
				
				itemInHand = p.getInventory().getItemInOffHand();
				itemInMainHand = false;
				
			}
			
			if (itemInHand.getAmount() > 1) 
				itemInHand.setAmount(itemInHand.getAmount() - 1);
			
			else {
				
				if (itemInMainHand) 
					p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
				
				else 
					p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
				
			}
			
			cooldownTask.put(p, new BukkitRunnable() {
				
				public void run() {
					
					duration = cooldownTime.get(p);
					cooldownTime.put(p, duration - 1);
					
					p.sendMessage("You have " + duration + " seconds left.");
					
					if (duration > 0)
						e.setCancelled(true);
					
					if (duration == 0) {
						
						p.sendMessage("You use the the golden apple now!");
						cooldownTime.remove(p);
						cooldownTask.remove(p);
						cancel();
						
					}
					
				}
				
			});
			
			cooldownTask.get(p).runTaskTimer(Core.i, 20, 20);
			
		}

	}
	
}
