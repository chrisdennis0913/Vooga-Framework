package gameCharacter;

import evented.Evented;

/**
 * Used as part of the decorator pattern involving CharacterInterface,
 * CharacterDecorator, and GameCharacter.
 * 
 * @author Kirill Klimuk
 */

interface CharacterInterface extends Evented{	
	public boolean isCurrentDirection(int direction);
	public int getCurrentDirection();
	public void setActiveDirection(int direction);
	public void setCurrentDirection(int direction);
	public void stop();
}
