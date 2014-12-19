package me.Funnygatt.SkSpigotAdditons.HologramManager;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.Funnygatt.SkSpigotAdditons.libraryaddict.Holograms.*;
import org.bukkit.Location;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 11/3/2014.
 */
public class EffMoveHolo extends Effect{
	private Expression<String> HologramName;
	private Expression<org.bukkit.Location> Location;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.HologramName = (Expression<String>) exprs[0];
		this.Location = (Expression<Location>) exprs[1];
		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "titles";
	}

	@Override
	protected void execute(Event event) {

		Location loc = this.Location.getSingle(event);
		String holoName = this.HologramName.getSingle(event);
		if (holoName == null){
			return;
		}
		Hologram h = Holograms.get(holoName);
		h.moveHologram(loc);
	}
}
