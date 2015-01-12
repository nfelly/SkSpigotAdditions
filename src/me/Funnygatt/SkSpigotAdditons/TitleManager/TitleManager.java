package me.Funnygatt.SkSpigotAdditons.TitleManager;

import net.minecraft.server.v1_8_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * <p><b>TitleLIB/TitleManager:</b> Class to send <b>Titles, Subtitles and Timings</b> to Players. <i>(Minecraft 1.8)</i><br/>
 * 
 * </p>
 * @author inventivetalent <b>-<b/> <a href="http://www.inventivegames.de"><i>www.inventivegames.de</i></a>
 * @version 2
 * 
 */
public class TitleManager implements Listener {

	public static Class<?>	nmsChatSerializer	= Reflection.getNMSClass("ChatSerializer");
	public static int		VERSION				= 47;

	/**
	 * Send a Title
	 * 
	 * @param p
	 *            Player to send the Title to
	 * @param title
	 *            Json Title String
	 */
	public static void sendTitle(Player p, String title) {
		try {
			PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
			title = ChatColor.translateAlternateColorCodes('&', title);
			IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + title + "\"}");
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleSub);
			connection.sendPacket(packetPlayOutSubTitle);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendActionTitle(Player p, String action){
		try {
			PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
			action = ChatColor.translateAlternateColorCodes('&', action);
			IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + action + "\"}");
			Byte slot = 2;
			PacketPlayOutChat packetPlayOutSubTitle = new PacketPlayOutChat(titleSub, slot);
			connection.sendPacket(packetPlayOutSubTitle);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	public static void sendSubTitle(Player p, String subtitle) {
		try {
			PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
			subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
			IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleSub);
			connection.sendPacket(packetPlayOutSubTitle);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
