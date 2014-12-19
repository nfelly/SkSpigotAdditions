package me.Funnygatt.SkSpigotAdditons.TitleManager;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.spigotmc.ProtocolInjector;
import org.spigotmc.ProtocolInjector.PacketTitle;

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
		if (!(getVersion(p))) return;
		try {
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			IChatBaseComponent serialized = ChatSerializer.a("{\"text\": \"" + title +
					"\"}");
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class, Reflection.getNMSClass("IChatBaseComponent")).newInstance(PacketTitle.Action.TITLE, serialized);
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
			connection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TITLE, serialized));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendActionTitle(Player p, String action){
		if (!(getVersion(p))) return;
		try {
			String s = ChatColor.translateAlternateColorCodes('&', action);
			IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + action +
					"\"}");
			PacketPlayOutChat bar = new PacketPlayOutChat(icbc, 2);
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send a Subtitle
	 * 
	 * @param p
	 *            Player to send the SubTitle to
	 * @param subtitle
	 *            Json SubTitle String
	 */
	public static void sendSubTitle(Player p, String subtitle) {
		if (!(getVersion(p))) return;
		try {
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			IChatBaseComponent serialized = ChatSerializer.a("{\"text\": \"" + subtitle +
					"\"}");
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class, Reflection.getNMSClass("IChatBaseComponent")).newInstance(PacketTitle.Action.SUBTITLE, serialized);
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
			connection.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.SUBTITLE, serialized));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the Title Timings
	 * 
	 * @param p
	 *            Player to Update the Timings
	 * @param fadeIn
	 *            Time it should take to fade In
	 * @param stay
	 *            Time the Title should stay on screen
	 * @param fadeOut
	 *            Time it should take to fade Out
	 */
	public static void sendTimings(Player p, Number fadeIn, Number stay, Number fadeOut) {
		if (!(getVersion(p))) return;
		try {
			//final Object handle = Reflection.getHandle(p);
			//final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			//Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class, int.class, int.class, int.class).newInstance(PacketTitle.Action.TIMES, fadeIn, stay, fadeOut);
			//Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);

			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class, Reflection.getNMSClass("IChatBaseComponent")).newInstance(PacketTitle.Action.TIMES, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);


		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reset the Players Timing, Title, SubTitle
	 * 
	 * @param p
	 *            Player to Reset
	 */
	public static void reset(Player p) {
		if (!(getVersion(p))) return;
		try {
			final Object handle = Reflection.getHandle(p);
			final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class).newInstance(PacketTitle.Action.RESET);
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clear the Players Title
	 * 
	 * @param p
	 *            Player to be cleared
	 */
	public static void clear(Player p) {
		if (!(getVersion(p))) return;
		try {
			final Object handle = Reflection.getHandle(p);
			final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
			Object packet = PacketTitle.class.getConstructor(PacketTitle.Action.class).newInstance(PacketTitle.Action.CLEAR);
			Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getVersion(Player player) {
		return ((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() >= 47;
	}
}
