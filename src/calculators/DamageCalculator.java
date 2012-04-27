package calculators;

import counters.Counter;
import enemy.AbstractEnemy;
import evented.EventedWrapper;

public abstract class DamageCalculator {

	protected EventedWrapper<Counter> player;
	protected EventedWrapper<Counter> enemy;
	
	public DamageCalculator(EventedWrapper<Counter> playerAttributes, 
			EventedWrapper<Counter> enemyAttributes) {
		this.player = playerAttributes;
		this.enemy = enemyAttributes;
	}
	
	public abstract int calculate();
	
	public abstract int calculate(AbstractEnemy enemy);
	
}
