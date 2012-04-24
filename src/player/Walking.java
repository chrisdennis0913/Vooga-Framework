package player;

import gameCharacter.GameCharacter;

import java.awt.Graphics2D;

import utils.JsonUtil;
import utils.KeyHandle;
import actions.ActionDecorator;
import actions.Walk;

public class Walking extends ActionDecorator {

	private static final long serialVersionUID = 1L;

	private KeyHandle keys;
	private double speed = 0.05;

	public Walking(Walk walk) {
		super(walk);
		initResources();
	}

	public void initResources() {
		keys = new KeyHandle(getWrapper().getCharacter().getGame());

		JsonUtil.JSONPlayerWalking walking = (JsonUtil.JSONPlayerWalking) getJsonable();
		if (walking.down == null || walking.right == null
				|| walking.left == null || walking.right == null)
			new RuntimeException("Directional keys undefined");

		keys.add(GameCharacter.DIR_DOWN, walking.down);
		keys.add(GameCharacter.DIR_UP, walking.up);
		keys.add(GameCharacter.DIR_RIGHT, walking.right);
		keys.add(GameCharacter.DIR_LEFT, walking.left);
	}

	public void update(long elapsed) {
		if (isEnabled()) {
			super.update(elapsed);

			int status = keys.checkKeys();
			GameCharacter character = getWrapper().getCharacter();

			if (status != -1) {
				character.setSpeed(0.05);
				if (!isActive() || status != character.getCurrentDirection()) {
					setActive(true);
					character.setActiveDirection(status);
				}
			} else {
				setActive(false);
				character.stop();
			}
		}
	}

	public void render(Graphics2D g) {
	}

}
