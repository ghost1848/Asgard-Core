package com.asgard.core.cooldowns;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class EnderPearl {
	
	Plugin pl;
	
	//Compares system time when put into hashmap to time when right click
	public HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();

	public EnderPearl(Plugin pl) {
		this.pl = pl;
	}
	
	String cooldownMessage = pl.getConfig().getString("ender-pearl-cooldownMessage");
	
	public void onPlayerRightClick(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		UUID uuid = e.getPlayer().getUniqueId();
		long elapsedTime =  (((cooldown.get(uuid) / 1000) + pl.getConfig().getInt("ender-pearl")) - (System.currentTimeMillis() / 1000));
		
		
		if (elapsedTime <= 0) {
			//cooldown finished
			//If person is not in hashmap, adds it. If player is in hashmap, updates it with the current time and uuid.
			cooldown.put(uuid, System.currentTimeMillis());
			return;
		}
		
		//still on cooldown
		e.setCancelled(true);
		player.sendMessage(cooldownMessage.replace("{time}", elapsedTime + ""));
		
		
		
		
		
	}
	
	
	
}
