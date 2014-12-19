package me.Funnygatt.SkSpigotAdditons.HologramManager;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.Funnygatt.SkSpigotAdditons.libraryaddict.Holograms.*;
import org.bukkit.event.Event;

/**
 * Created by Zachary on 11/3/2014.
 */
public class EffCreateTempHolo extends Effect{
	private Expression<String> HologramName;
	private Expression<Number> Time;


	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.HologramName = (Expression<String>) exprs[0];
		this.Time = (Expression<Number>) exprs[1];

		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "create temp holo";
	}

	@Override
	protected void execute(Event event) {
		String holoName = this.HologramName.getSingle(event);
		Number n = this.Time.getSingle(event);
		if (holoName == null){
			return;
		}
		Hologram h = Holograms.get(holoName);
		h.timed(h, n.intValue());

	}
}