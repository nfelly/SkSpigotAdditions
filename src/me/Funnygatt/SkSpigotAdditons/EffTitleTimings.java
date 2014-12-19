package me.Funnygatt.SkSpigotAdditons;

/**
 * Created by Zachary on 10/25/2014.
 */

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
/**
 * Created by Zachary on 10/24/2014.
 */
public class EffTitleTimings extends Effect {
	private Expression<Number> afadein;
	private Expression<Number> afadeout;
	private Expression<Number> astay;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.afadein = (Expression<Number>) expressions[0];
		this.afadeout = (Expression<Number>) expressions[1];
		this.astay = (Expression<Number>) expressions[2];

		return true;
	}

	@Override
	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "title and subtitle";
	}

	@Override

	protected void execute(Event event) {
		Number newfadein = this.afadein.getSingle(event);
		Number newfadeout = this.afadeout.getSingle(event);
		Number newstay = this.astay.getSingle(event);


		EffNewTitles.fadein = newfadein;
		EffNewTitles.fadeout = newfadeout;
		EffNewTitles.stay = newstay;


	}
}
