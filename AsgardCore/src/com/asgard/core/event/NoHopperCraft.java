package com.asgard.core.event;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import com.asgard.core.Core;

public class NoHopperCraft implements Listener {

	Core plugin;
	
	public NoHopperCraft(Core plugin) {
		
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);		
		
	}
	
	@EventHandler
	public void itemCraft(PrepareItemCraftEvent event) {
		
		Material itemType = event.getRecipe().getResult().getType();
		
		if (itemType == Material.HOPPER) {
			
			event.getInventory().setResult(new ItemStack(Material.AIR));
			
			for (HumanEntity he : event.getViewers()) {
				
				if (he instanceof Player) {
					
					((Player)he).sendMessage("§4You can not craft this!");
					
				}
				
			}
			
		}
		
	}
	
}
