package player;

import gameCharacter.GameCharacter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.golden.gamedev.util.ImageUtil;

import utils.Direction;
import utils.JsonUtil;
import utils.KeyHandle;
import actions.ActionDecorator;
import actions.Attack;

public class Attacking extends ActionDecorator {

	private static final long serialVersionUID = 1L;

	private KeyHandle keys;
	private long timer;

	public Attacking(Attack attack) {
		super(attack);
		initResources();
	}

	public void initResources() {
		keys = new KeyHandle(getWrapper().getCharacter().getGame());

		JsonUtil.JSONPlayerAttacking attacking = (JsonUtil.JSONPlayerAttacking) getJsonable();
		if (attacking.keys == null)
			new RuntimeException("Attack keys undefined");

		keys.add(Attack.ATTACK_BASIC, attacking.keys);
		
		JsonUtil.JSONDirections dirs = attacking.directions;
		Direction[] tempDirections = new Direction[4];

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

		Attack attack = (Attack) action;
		
		attack.directions = Arrays.asList(tempDirections);
	}

	public void update(long elapsed) {
		super.update(elapsed);

		int status = keys.checkKeys();
		GameCharacter character = getWrapper().getCharacter();

		if (status != -1) {
			if (!isActive()) {
				setActive(true);
				character.stop();
				setActiveDirection(character.getCurrentDirection());
				getWrapper().get("walking").setEnabled(false, true);
			}
		}
		else if (isActive()) {
			timer += elapsed;
			if (timer >= 250) {
				getWrapper().get("walking").setEnabled(true, false);
				setActive(false);
				reset();
			}
				
		}
	}
	
	public void reset() {
		timer = 0;
	}

	public void render(Graphics2D g) {
	}

}
