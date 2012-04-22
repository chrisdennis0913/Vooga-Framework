package player;

import java.awt.Graphics2D;
import java.util.HashMap;

import utils.JsonUtil;
import utils.KeyHandler;
import actions.Action;
import evented.EventedWrapper;
import gameCharacter.GameCharacter;

public class Walking extends Action {

	private static final long serialVersionUID = 1L;

	private KeyHandler keys = new KeyHandler(getWrapper().getCharacter()
			.getGame());
	private double speed = 0.07;

	public Walking(EventedWrapper<Action> wrapper,
			JsonUtil.JSONPlayerWalking walking) {
		super(wrapper, walking);
	}

	public void initResources() {
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
		int status = keys.checkKeys();
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
