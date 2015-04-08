package me.tyler.balloons;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

//Copyright (c) Tyler Hasman

public class BalloonPlugin extends JavaPlugin {

	public void onEnable(){
		
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new BalloonInventoryListener(), this);
		
		Bukkit.getScheduler().runTaskTimer(this, new BalloonThread(), 0, 0);
		
	}
	
	public void onDisable(){
		Bukkit.getScheduler().cancelTasks(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		
		if(command.getName().equals("balloon"))
		{
			
			
			if(!(sender instanceof Player)){
				sender.sendMessage("Not allowed! Sorry console person.");
				return true;
			}
			
			Player player = (Player) sender;
			
			Inventory inventory = Bukkit.createInventory(new BalloonInventoryHolder(), 18);
			
			ItemStack[] items = new ItemStack[16];
			
			for(int i = 0; i < 16;i++){
				items[i] = new ItemStack(Material.WOOL, 1, (short) i);
			}
			
			inventory.addItem(items);
			
			ItemStack barrier = new ItemStack(Material.BARRIER, 1);
			
			ItemMeta meta = barrier.getItemMeta();
			
			meta.setDisplayName(ChatColor.DARK_RED+"Destroy balloon!");
			
			barrier.setItemMeta(meta);
			
			inventory.addItem(barrier);
			
			player.openInventory(inventory);
			
			return true;
		}
		
		
		return super.onCommand(sender, command, label, args);
	}

	
	
	
}
