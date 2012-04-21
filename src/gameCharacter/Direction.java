package gameCharacter;

import java.awt.image.BufferedImage;

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

	public int getDirection() {
		return direction;
	}

	public BufferedImage[] getImages() {
		return images;
	}

	public int frameCount() {
		return images.length;
	}

	public void changeCharacter(boolean animate) {
		if (animate) {
			character.setImages(getImages());
			if (frameCount() == 1) {
				character.setLoopAnim(false);
				character.setAnimate(false);
			} else {
				character.getAnimationTimer().setDelay(delay);
				character.setLoopAnim(true);
				character.setAnimate(true);
				character.setAnimationFrame(0, frameCount() - 1);
			}
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
