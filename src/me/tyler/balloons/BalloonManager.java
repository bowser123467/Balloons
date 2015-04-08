package me.tyler.balloons;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.comphenix.packetwrapper.*;

public class BalloonManager {

	private static int nextId = Integer.MIN_VALUE;
	
	private static final Map<String, Balloon> balloons = new HashMap<String, Balloon>();
	
	public static final short WHITE = 0;
	public static final short ORANGE = 1;
	public static final short MAGENTA = 2;
	public static final short LIGHT_BLUE = 3;
	public static final short YELLOW = 4;
	public static final short LIME = 5;
	public static final short PINK = 6;
	public static final short GRAY = 7;
	public static final short LIGHT_GRAY = 8;
	public static final short CYAN = 9;
	public static final short PURPLE = 10;
	public static final short BLUE = 11;
	public static final short BROWN = 12;
	public static final short GREEN = 13;
	public static final short RED = 14;
	public static final short BLACK = 15;
	
	public static void giveBalloon(Player player, short color){
		
		if(hasBalloon(player)){
			getBalloon(player).destroy();
		}
		
		int entityId = nextId++;
		
		Balloon balloon = new Balloon(player.getUniqueId().toString(), entityId, color);
		
		Location spawnLocation = getBalloonLocation(player);
		
		spawnBalloon(balloon, spawnLocation);
		
		Chicken chicken = player.getWorld().spawn(spawnLocation, Chicken.class);
		
		chicken.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Integer.MAX_VALUE, 1));
		
		player.setPassenger(chicken);
		
		chicken.setLeashHolder(player);
		
		balloon.setLastLocation(spawnLocation);
		
		balloons.put(player.getUniqueId().toString(), balloon);
		
	}
	
	public static void spawnBalloon(Balloon balloon, Location spawnLocation){
		
		for(Player player : Bukkit.getOnlinePlayers()){
			spawnBalloonFor(balloon, player);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static void spawnBalloonFor(Balloon theBalloon, Player pl){
		
		Location spawnLocation = getBalloonLocation(theBalloon.getPlayer());
		
		WrapperPlayServerSpawnEntity spawnEntity;
		
		spawnEntity = new WrapperPlayServerSpawnEntity();
		
		spawnEntity.setEntityID(theBalloon.getEntityId());
		spawnEntity.setType(WrapperPlayServerSpawnEntity.ObjectTypes.FALLING_BLOCK);//Falling block ID
		spawnEntity.setObjectData(Material.WOOL.getId() | theBalloon.getColor() << 0xC);
		spawnEntity.setX(spawnLocation.getX());
		spawnEntity.setY(spawnLocation.getY());
		spawnEntity.setZ(spawnLocation.getZ());
		
		//spawnEntity.getHandle().getIntegers().write(10, Material.WOOL.getId() | theBalloon.getColor() << 0xC);
		
		spawnEntity.sendPacket(pl);
	}
	
	public static boolean hasBalloon(Player player){
		if(balloons.containsKey(player.getUniqueId().toString())){
			return true;
		}
		
		return false;
	}
	
	public static Balloon getBalloon(Player player){
		return balloons.get(player.getUniqueId().toString());
	}
	
	public static Collection<Balloon> getAllBalloons(){
		return balloons.values();
	}
	
	public static Location getBalloonLocation(Player player){
		return player.getEyeLocation().clone().add(0, 2, 0);
	}
	
}
