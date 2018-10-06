package com.asgard.core.inventories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.asgard.core.Core;

import net.md_5.bungee.api.ChatColor;

public class RankInventory implements Listener {

	HashMap<UUID, Inventory> perInv = new HashMap<UUID, Inventory>();
	
	public void allPlayers() {
		
		for (Player p : Core.i.getServer().getOnlinePlayers()) {
			
			perInv.put(p.getUniqueId(), rankInventory(p));
			
		}
		
	}
		
	public Inventory rankInventory(Player player) {

		Inventory inv = perInv.get(player.getUniqueId());
		
		inv = Core.i.getServer().createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', Core.i.getConfig().getString("rank-gui-name")));
		
		ItemStack rank = new ItemStack(Material.CHEST);
		ItemMeta rankMeta = rank.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		
		if (Core.i.getConfig().getStringList("Ranks") == null) {
			
			return null;
			
		} else {
			
			for (int i = 0; i < Core.i.getConfig().getStringList("Ranks").size(); i++) {
				
				lore.add(" ");
				lore.add("§2Cost: $" + Core.i.getConfig().getIntegerList("Price").get(i));
				
				rankMeta.setLore(lore);
				rankMeta.setDisplayName(Core.i.getConfig().getStringList("Ranks").get(i));
				rank.setItemMeta(rankMeta);
				
				inv.setItem(i, rank);
				
				lore.clear();

			}
			
		}
		
		player.openInventory(inv);
		
		return perInv.get(player.getUniqueId());
		
	}
	
}
