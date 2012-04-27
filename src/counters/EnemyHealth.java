package counters;

import java.awt.Graphics2D;

import enemy.AbstractEnemy;
import evented.EventedWrapper;

public class EnemyHealth extends Counter {

	private static final long serialVersionUID = 1L;
	
	public EnemyHealth(EventedWrapper<Counter> wrapper, int count) {
		super(wrapper, count);
		initResources();
	}

	public void initResources() {
	}

	public void update(long elapsed) {
		if (isEmpty()){
			getWrapper().getCharacter().setActive(false);
			((AbstractEnemy) getWrapper().getCharacter().getDecorator()).setAlive(false);
			((AbstractEnemy) getWrapper().getCharacter().getDecorator()).uponDeath();
		}
	}

	public void render(Graphics2D g) {}

}
