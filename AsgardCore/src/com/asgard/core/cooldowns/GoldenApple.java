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

public class GoldenApple implements Listener {
	
	public HashMap<UUID, Long> cooldownTime = new HashMap<UUID, Long >();
	public Core plugin;

	public GoldenApple (Core plugin) {
		
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onConsume(PlayerInteractEvent e) {
		
		if (e.getMaterial() == Material.GOLDEN_APPLE) {
		
			String cooldownMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ender-pearl-cooldownMessage"));
			Player player = e.getPlayer();
			UUID uuid = e.getPlayer().getUniqueId();
			if (cooldownTime.get(uuid) == null) {
				
				cooldownTime.put(uuid, System.currentTimeMillis());
				return;
				
			}
			
			long elapsedTime = (((cooldownTime.get(uuid) / 1000) + plugin.getConfig().getInt("golden-apple")) - (System.currentTimeMillis() / 1000));
			
			if (elapsedTime <= 0) {
					
				cooldownTime.put(uuid, System.currentTimeMillis());
				return;
					
			}
				
			e.setCancelled(true);
			player.sendMessage(cooldownMessage.replace("{time}", elapsedTime + ""));
			
		}

	}
	
}
