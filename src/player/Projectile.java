package player;

import gameCharacter.GameCharacter;

import java.awt.image.BufferedImage;

import utils.Velocity;

import com.golden.gamedev.object.Sprite;

public class Projectile {

	private GameCharacter character;
	private Velocity velocity = new Velocity(0.5);

	public Projectile(GameCharacter character) {
		this.character = character;
		initResources();
	}

	private void initResources() {
		int direction = character.getCurrentDirection();
		BufferedImage image;
		Sprite sprite;
		
		double loc = character.getY() + 10;

		if (direction == GameCharacter.DIR_DOWN) {
			image = character.getGame().getImage("rsc/items/arrow_down.png");
			sprite = new Sprite(image, character.getX(), loc);
		} else if (direction == GameCharacter.DIR_UP) {
			image = character.getGame().getImage("rsc/items/arrow_up.gif");
			sprite = new Sprite(image, character.getX(), loc);
		} else if (direction == GameCharacter.DIR_RIGHT) {
			image = character.getGame().getImage("rsc/items/arrow_right.png");
			sprite = new Sprite(image, character.getX(), loc);
		} else {
			image = character.getGame().getImage("rsc/items/arrow_left.gif");
			sprite = new Sprite(image, character.getX(), loc);
		}

		setProperties(sprite, direction);
		character.getGame().getField().getGroup("projectiles").add(sprite);
	}
	
	private void setProperties(Sprite sprite, int direction) {
		double[] velocity = this.velocity.get(direction);
		sprite.setSpeed(velocity[0], velocity[1]);
	}
}
