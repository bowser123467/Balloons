package me.tyler.balloons;

import org.bukkit.plugin.Plugin;

import com.comphenix.packetwrapper.WrapperPlayServerNamedSoundEffect;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

public class NoisePacketListener extends PacketAdapter {

	public NoisePacketListener(Plugin plugin) {
		super(plugin, ListenerPriority.HIGHEST, PacketType.Play.Server.NAMED_SOUND_EFFECT);
	}

	@Override
	public void onPacketSending(PacketEvent event) {
		if(event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT){
			
			WrapperPlayServerNamedSoundEffect wrapper = new WrapperPlayServerNamedSoundEffect(event.getPacket());
			
		}
	}
	
	

}
