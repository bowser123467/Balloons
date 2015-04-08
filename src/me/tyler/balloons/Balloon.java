package me.tyler.balloons;

import java.util.UUID;

import com.comphenix.packetwrapper.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Balloon {

	private static final double GRAVITY = 0.04D;
	
	private String playerUUID;
	private boolean isActive;
	private int entityId;
	
	private short color;
	
	private Location lastUpdate;
	
	public Balloon(String playerUUID, int entityId, short color) {
		this.playerUUID = playerUUID;
		isActive = true;
		this.entityId = entityId;
		this.color = color;
	}
	
	public String getPlayerUUID(){
		return playerUUID;
	}
	
	public short getColor(){
		return color;
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(UUID.fromString(playerUUID));
	}

	public void destroy() {
		isActive = false;
		
		WrapperPlayServerEntityDestroy destroyEntity;
		
		destroyEntity = new WrapperPlayServerEntityDestroy();
		
		destroyEntity.setEntities(new int[] { entityId });
		
		for(Player player : Bukkit.getOnlinePlayers()){
			destroyEntity.sendPacket(player);
		}
		
		if(getPlayer().getPassenger() != null){
			getPlayer().getPassenger().remove();
		}
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public int getEntityId(){
		return entityId;
	}

	/**
	 * Teleport the balloon
	 * @param location the desired location
	 */
	public void teleportForAll(Location location){
		WrapperPlayServerEntityTeleport teleportEntity;
		
		teleportEntity = new WrapperPlayServerEntityTeleport();
		
		teleportEntity.setEntityID(entityId);
		teleportEntity.setX(location.getX());
		teleportEntity.setY(location.getY());
		teleportEntity.setZ(location.getZ());
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			teleportEntity.sendPacket(pl);
		}
		
		setLastLocation(location);
	}
	
	public void update(Player pl) {
		WrapperPlayServerEntityVelocity entityVel; 
		
		entityVel = new WrapperPlayServerEntityVelocity();
		
		entityVel.setEntityId(entityId);
		entityVel.setVelocityX(0);
		entityVel.setVelocityY(GRAVITY);
		entityVel.setVelocityZ(0);
		
		entityVel.sendPacket(pl);
	}
	
	public Location getLastLocation(){
		return lastUpdate;
	}
	
	public void setLastLocation(Location loc){
		lastUpdate = loc;
	}

}
