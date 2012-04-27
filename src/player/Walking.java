
package player;

import gameCharacter.GameCharacter;

import java.awt.Graphics2D;

import utils.JsonUtil;
import utils.KeyHandle;
import actions.Action;
import actions.ActionDecorator;

import com.google.gson.JsonObject;

public class Walking extends ActionDecorator {

	private static final long serialVersionUID = 1L;

	private KeyHandle keys;

	public Walking(Action action) {
		super(action);
		setEnabled(true, false);
		initResources();
	}

	public void initResources() {
		keys = new KeyHandle(getWrapper().getCharacter().getGame());

		JsonObject walking = getJsonObject();
		int[] down = JsonUtil.JsonArrayToIntArray(walking.getAsJsonArray("down"));
		int[] up = JsonUtil.JsonArrayToIntArray(walking.getAsJsonArray("up"));
		int[] right = JsonUtil.JsonArrayToIntArray(walking.getAsJsonArray("right"));
		int[] left = JsonUtil.JsonArrayToIntArray(walking.getAsJsonArray("left"));	
		
		
		if (down == null || up == null
				|| left == null || right == null)
			new RuntimeException("Directional keys undefined");

		keys.add(GameCharacter.DIR_DOWN, down);
		keys.add(GameCharacter.DIR_UP, up);
		keys.add(GameCharacter.DIR_RIGHT, right);
		keys.add(GameCharacter.DIR_LEFT, left);
	}

	public void update(long elapsed) {
		if (isEnabled()) {
			super.update(elapsed);

			int status = keys.checkKeys();
			GameCharacter character = getWrapper().getCharacter();

			if (status != -1) {
				if (!isActive() || status != character.getCurrentDirection()) {
					setActive(true);
					character.setActiveDirection(status);
					character.setVelocity(character.getSpeed());
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