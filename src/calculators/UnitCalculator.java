package calculators;

import counters.Counter;
import enemy.AbstractEnemy;
import evented.EventedWrapper;

public class UnitCalculator extends DamageCalculator {

	public UnitCalculator(EventedWrapper<Counter> playerAttributes,
			EventedWrapper<Counter> enemyAttributes) {
		super(playerAttributes, enemyAttributes);
	}

	@Override
	public int calculate() {
		return 1;
	}

	@Override
	public int calculate(AbstractEnemy enemy) {
		return 1;
	}

}
