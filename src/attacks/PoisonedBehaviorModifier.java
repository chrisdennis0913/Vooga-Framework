package attacks;


import counters.Counter;
import app.RPGame;
import attacks.AbstractRecurrentTimeBehaviorModifier;

public class PoisonedBehaviorModifier extends AbstractRecurrentTimeBehaviorModifier{

	private static final int EXECUTIONS = 5;
	private static final int INTERVAL_TIME = 1500;
	
	public PoisonedBehaviorModifier(RPGame game){
		this(game, INTERVAL_TIME, EXECUTIONS);
	}
	
	public PoisonedBehaviorModifier(RPGame game, int intervalTime, int executions) {
		super(game, game.getPlayer().getCharacter(), intervalTime, executions);
	}

	@Override
	public void doRecurrent(long elapsedTime) {
		Counter c = target.getCounters().get("health");
		c.decrease(1);
	}

}
