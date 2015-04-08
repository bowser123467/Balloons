package me.tyler.balloons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		
		for(Balloon ba : BalloonManager.getAllBalloons()){
			BalloonManager.spawnBalloonFor(ba, event.getPlayer());
		}
		
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		
		Player player = event.getPlayer();
		
		if(BalloonManager.hasBalloon(player)){
			BalloonManager.getBalloon(player).destroy();
		}
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		
		if(BalloonManager.hasBalloon(player)){
			Balloon ba = BalloonManager.getBalloon(player);
			
			ba.teleportForAll(BalloonManager.getBalloonLocation(player));
		}
	}
	
}
