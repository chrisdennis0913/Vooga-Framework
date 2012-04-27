package state;

import gameCharacter.CharacterDecorator;


public interface State 
{		
	public void update(long elapsedTime, CharacterDecorator cD);
	
	public String getStatus();

	public boolean wantsActive();
}
