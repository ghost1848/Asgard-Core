package com.asgard.core.entity;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;

import com.asgard.core.Core;

public abstract class CustomCreeper implements Listener {

	Core plugin;
	
	public CustomCreeper(Core plugin) {
		
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	public void luckyCreeper(CreeperPowerEvent event) {
		
		
		
	}
	
}
