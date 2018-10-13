package com.asgard.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.asgard.core.cmd.CmdAsgardFactions;
import com.asgard.core.cmd.CmdAsgardFactionsBroadcast;
import com.asgard.core.cmd.CmdAsgardFactionsHelp;
import com.asgard.core.cmd.CmdAsgardFactionsReload;
import com.asgard.core.cmd.CmdBounty;
import com.asgard.core.cmd.CmdBuy;
import com.asgard.core.cmd.CmdDiscord;
import com.asgard.core.cmd.CommandHandler;
//import com.asgard.core.cooldowns.GoldenApple;
import com.asgard.core.event.InventoryClick;
import com.asgard.core.event.NoHopperCraft;
import com.asgard.core.cooldowns.EnderPearl;

import net.milkbowl.vault.economy.Economy;

public class Core extends JavaPlugin {
	
	public static Core i;
	public static Economy econ = null;
	public String msg = "";
	
	//===========================
	//         Commands
	//===========================
	
	public void registerCommands() {
		
		CommandHandler handler = new CommandHandler();
		handler.register("AsgardFactions", new CmdAsgardFactions());
		handler.register("reload", new CmdAsgardFactionsReload());
		handler.register("broadcast", new CmdAsgardFactionsBroadcast());
		handler.register("help", new CmdAsgardFactionsHelp());
		getCommand("AsgardFactions").setExecutor(handler);
		getCommand("Bounty").setExecutor(new CmdBounty());
		getCommand("Buy").setExecutor(new CmdBuy());
		getCommand("Discord").setExecutor(new CmdDiscord());
		
	}
	
	private boolean setupEconomy() {
		
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null)
			return false;
		
		RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null)
			return false;
		
		econ = rsp.getProvider();
		return econ != null;
		
	}
	
	public void onEnable() {
		
		i = this;
		registerCommands();
		
		getServer().getConsoleSender().sendMessage(getEnableMsg());
		getServer().getPluginManager().registerEvents(new CmdBounty(), this);
		//new GoldenApple(this);
		new InventoryClick(this);
		new NoHopperCraft(this);
		new EnderPearl(this);
		
		msg = "";
		loadConfig();
		
		if (!setupEconomy()) {
			
			getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			getServer().getPluginManager().disablePlugin(this);
			return;
			
		}
				
	}
	
	public void onDisable() {
		
		getServer().getConsoleSender().sendMessage(getDisableMsg());
		
	}

	public String getEnableMsg() {
		
		msg += "§b==================================\n";
		msg += "                    §3Asgard§fCore §6has been enabled!\n";
		msg += "                 §b==================================";
		
		return msg;
		
	}
	
	public String getDisableMsg() {
		
		msg += "§b===================================\n";
		msg += "                    §3Asgard§fCore §6has been disabled!\n";
		msg += "                 §b===================================";
		
		return msg;
		
	}
	
	public void loadConfig() {
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	
}
