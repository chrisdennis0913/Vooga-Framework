package player;

import enemy.Enemy;
import gameCharacter.GameCharacter;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import utils.Direction;
import actions.ActionDecorator;
import actions.Attack;
import calculators.DamageCalculator;

import com.golden.gamedev.util.ImageUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class StdAttack extends ActionDecorator {

	private static final long serialVersionUID = 1L;

	private String type;
	private DamageCalculator calculator = new DamageCalculator(null, null);

	public StdAttack(Attack attack) {
		super(attack);
		initResources();
	}

	public void initResources() {
		JsonObject json = getJsonObject();
		JsonObject dirs = json.getAsJsonObject("directions");
		
		Direction[] tempDirections = new Direction[4];
		
		type = json.get("type").getAsString();

		JsonArray dirsDirections = dirs.getAsJsonArray("directions");
		for (int i=0; i<dirsDirections.size();i++){
			JsonObject direction = dirsDirections.get(i).getAsJsonObject();
			BufferedImage image = getWrapper().getCharacter().getGame()
					.getImage(direction.get("image").getAsString());
			BufferedImage[] images = ImageUtil.splitImages(image, dirs.get("frames").getAsInt(),
					1);

			int intepretedDirection = 0;

			if (direction.get("direction").getAsString().equals("DIR_DOWN"))
				intepretedDirection = GameCharacter.DIR_DOWN;
			else if (direction.get("direction").getAsString().equals("DIR_UP"))
				intepretedDirection = GameCharacter.DIR_UP;
			else if (direction.get("direction").getAsString().equals("DIR_LEFT"))
				intepretedDirection = GameCharacter.DIR_LEFT;
			else if (direction.get("direction").getAsString().equals("DIR_RIGHT"))
				intepretedDirection = GameCharacter.DIR_RIGHT;
			else
				throw new RuntimeException("Invalid direction specified");

			tempDirections[intepretedDirection] = new Direction(getWrapper()
					.getCharacter(), images, intepretedDirection, dirs.get("delay").getAsInt());
		}

		Attack attk = (Attack) action;

		attk.directions = Arrays.asList(tempDirections);
	}
	
	public int getDamage(Enemy enemy) {
		return calculator.calculate();
	}
	
	public Attack getAttack() {
		return (Attack) action;
	}

	public boolean isEnabled() {
		return getWrapper().getCharacter().getInventory().isEquipped(type);
	}

}
