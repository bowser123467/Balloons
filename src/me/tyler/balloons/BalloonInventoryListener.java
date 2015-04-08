package me.tyler.balloons;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BalloonInventoryListener implements Listener{

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		
		Inventory inventory = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		
		if(inventory.getHolder() instanceof BalloonInventoryHolder){
			
			ItemStack clicked = inventory.getItem(event.getSlot());
			
			if(clicked == null)
				return;
			
			if(clicked.getType() == Material.WOOL){
				short color = clicked.getDurability();
				
				BalloonManager.giveBalloon(player, color);
				
				event.getWhoClicked().sendMessage(ChatColor.GREEN+"Enjoy your balloon!");
				
				player.closeInventory();
				
				
			}else if(clicked.getType() == Material.BARRIER){
				if(BalloonManager.hasBalloon(player)){
					BalloonManager.getBalloon(player).destroy();
					player.closeInventory();
					player.getWorld().playEffect(BalloonManager.getBalloonLocation(player), Effect.EXPLOSION, 1);
				}else{
					player.sendMessage(ChatColor.RED+"You don't have a balloon!");
					event.setCancelled(true);
				}
			}
			
		}
		
	}
	
}
