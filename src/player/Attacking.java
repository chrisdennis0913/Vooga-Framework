package player;

import gameCharacter.GameCharacter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import utils.Direction;
import utils.JsonUtil;
import utils.KeyHandle;
import actions.ActionDecorator;
import actions.Attack;

import com.golden.gamedev.object.Timer;
import com.golden.gamedev.util.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Attacking extends ActionDecorator {

	private static final long serialVersionUID = 1L;

	private KeyHandle keys;
	private Timer timer = new Timer(250);

	public Attacking(Attack attack) {
		super(attack);
		initResources();
	}

	public void initResources() {
		Gson gson = new Gson();
		
		keys = new KeyHandle(getWrapper().getCharacter().getGame());

		//JsonUtil.JSONPlayerAttacking attacking = (JsonUtil.JSONPlayerAttacking) getJsonObject();
		JsonObject attacking = getJsonObject();
		JsonArray jAttackingKeys = attacking.getAsJsonArray("keys");	
		int[] attackingKeys = JsonUtil.JsonArrayToIntArray(jAttackingKeys);
		
		
		if (attackingKeys == null)
			new RuntimeException("Attack keys undefined");

		keys.add(Attack.ATTACK_BASIC, attackingKeys);
		
		JsonObject dirs = attacking.getAsJsonObject("directions");
		JsonArray dirsDirections = dirs.getAsJsonArray("directions");
		
		Direction[] tempDirections = new Direction[4];

		//for (JsonUtil.JSONDirection direction : dirs.directions) {
		for(int i=0; i<dirsDirections.size(); i++){
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
				timer.setActive(true);
				character.stop();
				setActiveDirection(character.getCurrentDirection());
				getWrapper().get("walking").setEnabled(false, true);
			}
		}
		else if (isActive()) {
			if (timer.action(elapsed)) {
				getWrapper().get("walking").setEnabled(true, false);
				setActive(false);
				timer.refresh();
				timer.setActive(false);
			}
				
		}
	}

	public void render(Graphics2D g) {
	}

}
