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

	Core pl;

	// Compares system time when put into hashmap to time when right click
	public HashMap<UUID, Long> cooldown = new HashMap<>();

	public EnderPearl(Core pl) {
		this.pl = pl;
		pl.getServer().getPluginManager().registerEvents(this, pl);

	}

	String cooldownMessage = pl.getConfig().getString("ender-pearl-cooldownMessage");

	@EventHandler
	public void onPlayerRightClick(PlayerInteractEvent e) {
		
		
		
		Player player = e.getPlayer();
		
		UUID uuid = e.getPlayer().getUniqueId();

		String cooldownMessage = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("ender-pearl-cooldownMessage"));

		if (e.getItem().getType().equals(Material.ENDER_PEARL)) {




		
		if (cooldown.get(uuid) == null) {
			cooldown.put(uuid, System.currentTimeMillis());
			return;
		}
		long elapsedTime =  (((cooldown.get(uuid) / 1000) + pl.getConfig().getInt("ender-pearl"))) - (System.currentTimeMillis() / 1000);
		if (elapsedTime <= 0) {
			//cooldown finished
			//If person is not in hashmap, adds it. If player is in hashmap, updates it with the current time and uuid.
			cooldown.put(uuid, System.currentTimeMillis());
			return;
		}

		
		e.setCancelled(true);
		player.sendMessage(cooldownMessage.replace("{time}", elapsedTime + ""));
		
		
		
		
		
		}

	}
<<<<<<< Upstream, based on branch 'master' of https://github.com/ghost1848/Asgard-Core
	//ju
=======
>>>>>>> d22feb9 Fixed Enderpearl cooldown for real
	
}
