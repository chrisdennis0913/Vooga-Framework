package calculators;

import counters.Counter;
import evented.EventedWrapper;

public class DamageCalculator {

	@SuppressWarnings("unused")
	private EventedWrapper<Counter> player;
	@SuppressWarnings("unused")	
	private EventedWrapper<Counter> enemy;
	
	public DamageCalculator(EventedWrapper<Counter> playerAttributes, 
			EventedWrapper<Counter> enemyAttributes) {
		this.player = playerAttributes;
		this.enemy = enemyAttributes;
	}
	
	public int calculate() {
		return 1;
	}
	
}
