package utils;

import gameCharacter.GameCharacter;

import java.awt.image.BufferedImage;

/**
 * Direction is used to assign the direction specific images, delay for the animation,
 * and add in the character.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
 * 
 * @author Kirill Klimuk
 */

public class Direction {
	private GameCharacter character;
	private final BufferedImage[] images;
	private final int direction;
	private final int delay;

	public Direction(GameCharacter character, BufferedImage[] images,
			int direction, int delay) {
		this.images = images;
		this.character = character;
		this.direction = direction;
		this.delay = delay;
	}

	public int getIntDirection() {
		return direction;
	}

	public int frameCount() {
		return images.length;
	}

	public void changeCharacter(boolean animate) {
		character.setImages(images);
		if (frameCount() == 1 || !animate) {
			character.setLoopAnim(false);
			character.setAnimate(false);
			if (frameCount() > 2)
				character.setFrame(3);
			else character.setFrame(0);
		} else {
			character.getAnimationTimer().setDelay(delay);
			character.setLoopAnim(true);
			character.setAnimate(true);
			character.setAnimationFrame(0, frameCount() - 1);
		}
	}

	public String toString() {
		if (direction == GameCharacter.DIR_DOWN)
			return "DIR_DOWN";
		else if (direction == GameCharacter.DIR_UP)
			return "DIR_UP";
		else if (direction == GameCharacter.DIR_LEFT)
			return "DIR_LEFT";
		else if (direction == GameCharacter.DIR_RIGHT)
			return "DIR_RIGHT";
		else
			return "INVALID DIRECTION";
	}
}
