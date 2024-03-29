package actions;

import java.awt.Graphics2D;
import java.util.List;

import utils.Direction;
import calculators.DamageCalculator;

import com.google.gson.JsonObject;

import enemy.AbstractEnemy;
import evented.EventedWrapper;

public class Attack extends Action {

	private static final long serialVersionUID = 1L;

	private DamageCalculator calculator;
	public List<Direction> directions;
	
	public static final int ATTACK_BASIC = 4;

	public Attack(EventedWrapper<ActionInterface> wrapper, JsonObject json) {
		super(wrapper, json);
		initResources();
	}

	public int getDamage(AbstractEnemy enemy) {
		return calculator.calculate();
	}

	public void initResources() {}
	
	public void setActiveDirection(int direction) {
		getWrapper().getCharacter().setCurrentDirection(direction);
		directions.get(direction).changeCharacter(true);
	}

	public void update(long elapsed) {}

	public void render(Graphics2D g) {}

}
