package me.Funnygatt.SkSpigotAdditons;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import me.Funnygatt.SkSpigotAdditons.Utils.HeaderFootermanager;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import me.Funnygatt.SkSpigotAdditons.HologramManager.*;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Zachary on 10/23/2014.
 */
public class Main extends JavaPlugin implements Listener{
	public static IChatBaseComponent newfooter = null;
	public static IChatBaseComponent newheader = null;

	public static Plugin plugin;

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);

		plugin = this;

		pm.registerEvents(new HeaderFootermanager(), this);

		Logger.info("Now loading SkSpigotAdditions");

		Skript.registerAddon(this);
		Skript.registerEffect(EffTabList.class, "(send|set) [advanced ](0¦footer|1¦header) to %string% (to|for) %players%");
		//Skript.registerEffect(EffSetTitle.class, "set title (0¦title|1¦sub[ ]title) to %string%");

		//Skript.registerEffect(EffSendTitle.class, "send title to %players%");
		//Skript.registerEffect(EffTitleTimings.class, "set title timings to %number%, %number%, %number%");
		Skript.registerEffect(EffNewTitles.class, "send [a ]title from %string% and %string% to %players% for %number%, %number%, %number%");
		Skript.registerEffect(EffActionBar.class, "send [a ]action bar from %string% to %players%");

		//1.8 Holograms

		//TODO: Remove //'s once complete

		Skript.registerEffect(EffCreateHologram.class, "create [a ]new holo[gram] named %string%");
		//Skript.registerEffect(EffSetHoloLine.class, "set holo[gram] line %integer% of holo[gram] %string% to %string%");

		Skript.registerEffect(EffAddHoloLine.class, "set lines of holo[gram] %string% to %strings%");
		Skript.registerEffect(EffDeleteHoloLine.class, "(remove|clear|delete) lines of holo[gram] %string%");
		Skript.registerEffect(EffMoveHolo.class, "move holo[gram] %string% to %location%");
		Skript.registerEffect(EffHoloFollow.class, "make holo[gram] %string% follow %entity%");
		Skript.registerEffect(EffHoloStart.class, "start holo[gram] %string%");
		Skript.registerEffect(EffHoloStop.class, "stop holo[gram] %string%");
		Skript.registerEffect(EffDeleteHolo.class, "delete holo[gram] %string%");

		Skript.registerEffect(EffCreateFollowGram.class, "create [a ]new following holo[gram] (to|that) follow[s] %entity% with [text] %string%");

		Skript.registerEffect(EffCreateTempHolo.class, "create [a ]new temporary holo[gram] named %string% for %number% [second[s]]");

		Skript.registerEffect(EffSetHoloType.class, "set holo[gram] type to (0¦wither skulls|1¦armor stands)");

		Skript.registerExpression(ExprUnbreakable.class, ItemStack.class, ExpressionType.PROPERTY, new String[] {"[a[n]] unbreakable %itemstacks%"});

	}
//	@EventHandler
//	public void onJoin(PlayerJoinEvent e){
//		Player p = e.getPlayer();
//		Hologram hologram = new Hologram(p.getLocation(), "Test!", "&eColour Test", "&cWow").start();
//		hologram.setFollowEntity(p);
//	}

	public static boolean isPlayerRightVersion(Player player)
	{
		return ((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() >= 47;
	}

}
