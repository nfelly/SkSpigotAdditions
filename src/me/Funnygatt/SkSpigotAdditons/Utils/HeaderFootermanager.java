package me.Funnygatt.SkSpigotAdditons.Utils;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.ProtocolInjector;

import java.util.HashMap;

/**
 * Created by Zachary on 10/25/2014.
 */
public class HeaderFootermanager implements Listener
{
	public static HashMap<Player, String> playerHeaders = new HashMap<Player, String>();
	public static HashMap<Player, String> playerFooters = new HashMap<Player, String>();

	public void playerJoin(PlayerJoinEvent p){
		if (!(playerFooters.containsKey(p.getPlayer()))){
			playerFooters.put(p.getPlayer(), "");
		}
		if (!(playerHeaders.containsKey(p.getPlayer()))){
			playerHeaders.put(p.getPlayer(), "");
		}
	}
	public void playerLeave(PlayerQuitEvent p){
		if (playerFooters.containsKey(p.getPlayer())){
			playerFooters.remove(p.getPlayer());
		}
		if (playerHeaders.containsKey(p.getPlayer())){
			playerFooters.remove(p.getPlayer());
		}
	}

	public static void UpdateAdvanceTablist(Player p){
		PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
		IChatBaseComponent header = ChatSerializer.a(playerHeaders.get(p));
		IChatBaseComponent footer = ChatSerializer.a(playerFooters.get(p));
		connection.sendPacket(new ProtocolInjector.PacketTabHeader(footer, header));
	}


}
