package player;

import java.awt.Graphics2D;
import java.util.HashMap;

import utils.JsonUtil;

import actions.Action;
import evented.EventedWrapper;
import gameCharacter.GameCharacter;

public class Walking extends Action {

	private JsonUtil.JSONPlayerWalking walking;
	private double speed = 0.07;
	private HashMap<Integer, int[]> keys = new HashMap<Integer, int[]>();

	public Walking(EventedWrapper<Action> wrapper, JsonUtil.JSONPlayerWalking walking) {
		super(wrapper);
		this.walking = walking;
		initResources();
	}

	public void initResources() {
		if (walking.down == null || walking.right == null
				|| walking.left == null || walking.right == null)
			new RuntimeException("Directional keys undefined");

		keys.put(GameCharacter.DIR_DOWN, walking.down);
		keys.put(GameCharacter.DIR_UP, walking.up);
		keys.put(GameCharacter.DIR_RIGHT, walking.right);
		keys.put(GameCharacter.DIR_LEFT, walking.left);
	}

	private int checkKeys() {
		boolean isDirection = false;

		for (Integer direction : keys.keySet()) {
			for (int key : keys.get(direction)) {
				if (!getWrapper().getCharacter().getGame().keyDown(key)) {
					isDirection = false;
					continue;
				}
				isDirection = true;
			}

			if (isDirection)
				return direction;
		}

		return -1;
	}

	public void update(long elapsed) {
		int status = checkKeys();
		GameCharacter character = getWrapper().getCharacter();

		if (status != -1) {
			if (!isActive() || status != character.getCurrentDirection()) {
				setActive(true);
				character.setActiveDirection(status);
				character.setSpeed(speed);
			}
		} else {
			setActive(false);
			character.stop();
		}
	}

	public void render(Graphics2D g) {
	}

}
