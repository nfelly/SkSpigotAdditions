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
public class EffSetHoloLine extends Effect {
	private Expression<Number> Line;
	private Expression<String> HologramName;
	private Expression<String> Text;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.Line = (Expression<Number>) exprs[0];
		this.HologramName = (Expression<String>) exprs[1];
		this.Text = (Expression<String>) exprs[2];
		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "set holo line // old";
	}

	@Override
	protected void execute(Event event) {

		String text = this.Text.getSingle(event);
		String holoName = this.HologramName.getSingle(event);
		if (holoName == null){
			return;
		}
		Hologram h = Holograms.get(holoName);
		h.setLines(text);
	}
}
