package com.asgard.core.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.asgard.core.Core;

import net.md_5.bungee.api.ChatColor;

public class InventoryClick implements Listener {

	Core plugin;
	
	public InventoryClick(Core plugin) {
		
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void invClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();
		
		if (open == null) return;
		
		if (open.getName().equals(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("rank-gui-name")))) {
			
			event.setCancelled(true);
			player.updateInventory();
			
			for (int i = 0; i < plugin.getConfig().getStringList("Ranks").size(); i++) {
				
				if (item.getItemMeta().getDisplayName().equals(plugin.getConfig().getStringList("Ranks").get(i))) {
					
					player.sendMessage("You clicked §6" + plugin.getConfig().getStringList("Ranks").get(i));
					player.closeInventory();
					
				}
				
			}
			
		}
		
	}
	
}
