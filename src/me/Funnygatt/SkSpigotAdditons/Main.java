package me.Funnygatt.SkSpigotAdditons;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import me.Funnygatt.SkSpigotAdditons.Utils.HeaderFootermanager;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
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

		Skript.registerExpression(ExprUnbreakable.class, ItemStack.class, ExpressionType.PROPERTY, new String[] {"[a[n]] unbreakable %itemstacks%"});

	}
}
