package player;

import gameCharacter.GameCharacter;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import utils.Direction;
import utils.JsonUtil;
import utils.JsonUtil.JSONAttack;
import utils.JsonUtil.JSONDirections;

import com.golden.gamedev.util.ImageUtil;

import actions.ActionDecorator;
import actions.Attack;

public class StdAttack extends ActionDecorator {

	private static final long serialVersionUID = 1L;

	private String type;

	public StdAttack(Attack attack) {
		super(attack);
		initResources();
	}

	public void initResources() {
		JSONAttack json = (JSONAttack) getJsonable();
		JSONDirections dirs = json.directions;
		Direction[] tempDirections = new Direction[4];

		type = json.type;

		for (JsonUtil.JSONDirection direction : dirs.directions) {
			BufferedImage image = getWrapper().getCharacter().getGame()
					.getImage(direction.image);
			BufferedImage[] images = ImageUtil.splitImages(image, dirs.frames,
					1);

			int intepretedDirection = 0;

			if (direction.direction.equals("DIR_DOWN"))
				intepretedDirection = GameCharacter.DIR_DOWN;
			else if (direction.direction.equals("DIR_UP"))
				intepretedDirection = GameCharacter.DIR_UP;
			else if (direction.direction.equals("DIR_LEFT"))
				intepretedDirection = GameCharacter.DIR_LEFT;
			else if (direction.direction.equals("DIR_RIGHT"))
				intepretedDirection = GameCharacter.DIR_RIGHT;
			else
				throw new RuntimeException("Invalid direction specified");

			tempDirections[intepretedDirection] = new Direction(getWrapper()
					.getCharacter(), images, intepretedDirection, dirs.delay);
		}

		Attack attk = (Attack) action;

		attk.directions = Arrays.asList(tempDirections);
	}

	public boolean isEnabled() {
		return getWrapper().getCharacter().getInventory().isEquipped(type);
	}

}
