package gameCharacter;

import java.awt.image.BufferedImage;

public class Direction {
	private BufferedImage[] images;
	private GameCharacter character;
	private final int direction;
	
	public Direction (GameCharacter character, int direction, BufferedImage[] images) {
		this.images = images;
		this.character = character;
		this.direction = direction;
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
	
	public void changeCharacter (boolean animate, int delay) {
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
}

