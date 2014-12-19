package me.Funnygatt.SkSpigotAdditons;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.spigotmc.ProtocolInjector;

/**
 * Created by Zachary on 10/23/2014.
 */
public class EffTabList extends Effect implements Listener {

	private Expression<Player> Players;
	private Expression<String> format;
	private boolean footer;


	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.footer = parse.mark == 0;
		this.format = (Expression<String>) exprs[0];
		this.Players = (Expression<Player>) exprs[1];

		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "tablist header 1.8";
	}

	@Override

	protected void execute(Event event) {
		Player[] playerlist = Players.getAll(event);
		String format = this.format.toString(event, true);
		for (Player p : playerlist) {
			if (Main.isPlayerRightVersion(p)){
				//Bukkit.broadcastMessage("Footer = " + type.toString());
				if (this.footer == false) {
					PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
					Main.newfooter = ChatSerializer.a(format);
					if (Main.newheader == null){
						Main.newheader = ChatSerializer.a("Default");
					}
					connection.sendPacket(new ProtocolInjector.PacketTabHeader(Main.newfooter, Main.newheader));
					//Bukkit.broadcastMessage("Footer");
				}
				if (this.footer == true) {
					PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
					Main.newheader = ChatSerializer.a(format);
					if (Main.newfooter == null) {
						Main.newfooter = ChatSerializer.a("Default");
					}
					connection.sendPacket(new ProtocolInjector.PacketTabHeader(Main.newfooter, Main.newheader));
					//Bukkit.broadcastMessage("Header");
				}
			}
		}
	}
}

