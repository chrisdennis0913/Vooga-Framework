package state;

import gameCharacter.CharacterDecorator;

public interface State 
{	
	public void act(CharacterDecorator cd);
}
