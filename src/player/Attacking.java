package player;

import java.awt.Graphics2D;

import utils.JsonUtil;
import utils.Jsonable;

import evented.EventedWrapper;
import gameCharacter.GameCharacter;
import actions.Action;

public class Attacking extends Action{

	private static final long serialVersionUID = 1L;

	public Attacking(EventedWrapper<Action> wrapper, Jsonable json) {
		super(wrapper, json);
	}

	public void initResources() {
		JsonUtil.JSONPlayerWalking walking = (JsonUtil.JSONPlayerWalking) getJsonable();
		if (walking.down == null || walking.right == null
				|| walking.left == null || walking.right == null)
			new RuntimeException("Directional keys undefined");

		keys.put(GameCharacter.DIR_DOWN, walking.down);
		keys.put(GameCharacter.DIR_UP, walking.up);
		keys.put(GameCharacter.DIR_RIGHT, walking.right);
		keys.put(GameCharacter.DIR_LEFT, walking.left);
	}

	public void update(long elapsed) {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

}
