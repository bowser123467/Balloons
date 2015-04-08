package me.tyler.balloons;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BalloonInventoryHolder implements InventoryHolder {

	private Inventory inv;
	
	public void setInventory(Inventory i){
		inv = i;
	}
	
	@Override
	public Inventory getInventory() {
		return inv;
	}

}
