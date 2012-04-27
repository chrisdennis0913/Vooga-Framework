package gameCharacter;

import java.awt.Graphics2D;

import com.google.gson.JsonObject;

import utils.Jsonable;

/**
 * Decorator class for GameCharacter;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
 * 
 * @author Kirill Klimuk
 */

public abstract class CharacterDecorator implements CharacterInterface, Jsonable 
{
	protected GameCharacter character;
	protected String name;

	public CharacterDecorator(GameCharacter character) {
		this.character = character;
	}
	
	public void initResources() {
		character.initResources();
	}

	public void render(Graphics2D g) {}

	public void update(long elapsed) {}

	
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
	
	public GameCharacter getCharacter(){
	    return character;
	}

	public void setCurrentDirection(int direction) {
		character.setCurrentDirection(direction);
	}

	@Override
	public abstract JsonObject toJson();

	public String getName() {
		return name;
	}
}
