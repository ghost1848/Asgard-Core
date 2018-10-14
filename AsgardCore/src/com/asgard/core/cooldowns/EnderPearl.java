package com.asgard.core.cooldowns;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.asgard.core.Core;

import net.md_5.bungee.api.ChatColor;

public class EnderPearl implements Listener {
	
	Core plugin;
	
	//Compares system time when put into hashmap to time when right click
	public HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();

	public EnderPearl(Core plugin) {
		
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	@EventHandler
	public void onPlayerRightClick(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		String cooldownMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ender-pearl-cooldownMessage"));
		UUID uuid = e.getPlayer().getUniqueId();
		
		cooldown.put(player.getUniqueId(), System.currentTimeMillis());
		long elapsedTime =  (((cooldown.get(uuid) / 1000) + (plugin.getConfig().getInt("ender-pearl"))) - (System.currentTimeMillis() / 1000));
		
		if (e.getMaterial() == Material.ENDER_PEARL) {
		
			while (elapsedTime > 0) {
				
				//e.setCancelled(false);
				
				try {
					
					elapsedTime -= 1;
					Thread.sleep(1000);
					e.setCancelled(true);
					
				} catch (InterruptedException ie) {}
				
				player.sendMessage(cooldownMessage.replace("{time}", elapsedTime + ""));
				
				if (elapsedTime <= 0) {
					
					//cooldown finished
					//If person is not in hashmap, adds it. If player is in hashmap, updates it with the current time and uuid.
					cooldown.put(uuid, System.currentTimeMillis());
					e.setCancelled(false);
					return;
					
				}
				
				
	
			}
			
		} else { return; }
		
	}
	
}
