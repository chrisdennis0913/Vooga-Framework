package calculators;

import counters.Counter;
import enemy.AbstractEnemy;
import evented.EventedWrapper;

/**
 * This example calculates a random damage value of
 * between 10% and 30% of the remaining player health,
 * while keeping the damage minimum at 1.
 * @author jameshong
 *
 */
public class RandomCalculator extends DamageCalculator{

	public RandomCalculator(EventedWrapper<Counter> playerAttributes,
			EventedWrapper<Counter> enemyAttributes) {
		super(playerAttributes, enemyAttributes);
	}

	@Override
	public int calculate() {
		return (int) Math.max(1, Math.random()*0.3*player.get("health").getCount());
	}

	@Override
	public int calculate(AbstractEnemy enemy) {
		return calculate();
	}

}
