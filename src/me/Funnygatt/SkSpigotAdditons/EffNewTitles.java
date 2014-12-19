package me.Funnygatt.SkSpigotAdditons;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.Funnygatt.SkSpigotAdditons.TitleManager.Reflection;
import me.Funnygatt.SkSpigotAdditons.TitleManager.TitleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.spigotmc.ProtocolInjector;


/**
 * Created by Zachary on 10/25/2014.
 */
public class EffNewTitles extends Effect implements Listener{

	private Expression<String> Title;
	private Expression<String> Subtitle;
	private Expression<Player> Players;
	private Expression<Number> afadein;
	private Expression<Number> afadeout;
	private Expression<Number> astay;
	public static Number fadein;
	public static Number fadeout;
	public static Number stay;



	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean,
						SkriptParser.ParseResult parse) {
		this.Title = (Expression<String>) exprs[0];
		this.Subtitle = (Expression<String>) exprs[1];
		this.Players = (Expression<Player>) exprs[2];

		this.afadein = (Expression<Number>) exprs[3];
		this.astay = (Expression<Number>) exprs[4];
		this.afadeout = (Expression<Number>) exprs[5];
		return true;
	}

	public String toString(@javax.annotation.Nullable Event arg0, boolean arg1) {
		return "titles";
	}

	@Override

	protected void execute(Event event) {
		String newtitle = this.Title.getSingle(event);
		String newsubtitle = this.Subtitle.getSingle(event);
		Player[] playerlist = Players.getAll(event);

		Number newfadein = this.afadein.getSingle(event);
		Number newfadeout = this.afadeout.getSingle(event);
		Number newstay = this.astay.getSingle(event);


		EffNewTitles.fadein = newfadein;
		EffNewTitles.fadeout = newfadeout;
		EffNewTitles.stay = newstay;


		for (Player p : playerlist) {
			if (!(TitleManager.getVersion(p))){
				return;
			}
			try {
				final Object handle = Reflection.getHandle(p);
				final Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
				Object packet = ProtocolInjector.PacketTitle.class.getConstructor(ProtocolInjector.PacketTitle.Action.class, int.class, int.class, int.class).newInstance(ProtocolInjector.PacketTitle.Action.TIMES, fadein.intValue(), stay.intValue(), fadeout.intValue());
				Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			TitleManager.sendSubTitle(p, newsubtitle);
			TitleManager.sendTitle(p, newtitle);
		}

	}
}
