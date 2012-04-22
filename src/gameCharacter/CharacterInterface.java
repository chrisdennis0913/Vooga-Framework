package gameCharacter;

import evented.Evented;

interface CharacterInterface extends Evented{	
	public boolean isCurrentDirection(int direction);
	public Direction getCurrentDirection();
	public void setCurrentDirection(int direction, boolean animate);
}
