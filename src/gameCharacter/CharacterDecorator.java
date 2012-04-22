package gameCharacter;

import java.awt.Graphics2D;

public class CharacterDecorator implements CharacterInterface{
	protected GameCharacter character;

	public CharacterDecorator(GameCharacter character) {
		this.character = character;
	}
	
	public void initResources() {
		character.initResources();
	}

	public void render(Graphics2D g) {
		character.initResources();
	}

	public void update(long elapsed) {
		character.update(elapsed);
	}
	
	public boolean isCurrentDirection(int direction) {
		return character.isCurrentDirection(direction);
	}

	public Direction getCurrentDirection() {
		return character.getCurrentDirection();
	}

	public void setCurrentDirection(int direction, boolean animate) {
		character.setCurrentDirection(direction, animate);
	}

	public void stop() {
		character.stop();
	}
	
	
}
