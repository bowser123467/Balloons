package me.tyler.balloons;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BalloonThread implements Runnable {

	@Override
	public void run() {

		Collection<Balloon> balloons = BalloonManager.getAllBalloons();
		
		for(Balloon ba : balloons){
			
			if(!ba.isActive()){
				continue;
			}
			
			Player player = ba.getPlayer();
			
			if(player == null){
				ba.destroy();
				continue;
			}
			
			for(Player pl : Bukkit.getOnlinePlayers()){
				ba.update(pl);
			}
			
		}
		
	}

}
