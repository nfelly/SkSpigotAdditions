package me.Funnygatt.SkSpigotAdditons;

import org.bukkit.Bukkit;

public class Logger {
	public static void info(String s) {
		Bukkit.getLogger().info("[SkSpigotAdditions] " + s);
	}

	public static void severe(String content) {
		Bukkit.getLogger().severe("[SkSpigotAdditions] " + content);
	}

	public static void console(String content) {
		Bukkit.getServer().getConsoleSender().sendMessage(content);
	}
	
}
