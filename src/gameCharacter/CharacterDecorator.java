package gameCharacter;

import java.awt.Graphics2D;

import com.golden.gamedev.object.AnimatedSprite;

/**
 * Decorator class for GameCharacter;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
 * 
 * @author Kirill Klimuk
 */

public class CharacterDecorator extends AnimatedSprite implements CharacterInterface{
	
	private static final long serialVersionUID = 1L;
	
	protected GameCharacter character;

	public CharacterDecorator(GameCharacter character) {
		this.character = character;
	}
	
	public void initResources() {
		character.initResources();
	}

	public void render(Graphics2D g) {
		character.render(g);
	}

	public void update(long elapsed) {
		character.update(elapsed);
	}
	
	public boolean isCurrentDirection(int direction) {
		return character.isCurrentDirection(direction);
	}

	public int getCurrentDirection() {
		return character.getCurrentDirection();
	}

	public void setActiveDirection(int direction) {
		character.setActiveDirection(direction);
	}

	public void stop() {
		character.stop();
	}
	
	
}
