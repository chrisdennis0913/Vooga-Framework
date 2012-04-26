package gameCharacter;

import java.awt.Graphics2D;

import state.AttackingState;
import state.State;
import state.TalkingState;
import state.WalkingState;

/**
 * Decorator class for GameCharacter;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
 * 
 * @author Kirill Klimuk
 */

public class CharacterDecorator implements CharacterInterface 
{
	private AttackingState atkState;
	private TalkingState talkState;
	private WalkingState walkState;
	
	private State currentState;
	
	protected GameCharacter character;

	public CharacterDecorator(GameCharacter character) {
		this.character = character;
	}
	
	public CharacterDecorator(GameCharacter character, AttackingState aState, TalkingState tState, WalkingState wState)
	{
		atkState = aState;
		talkState = tState;
		walkState = wState;
	}
	
	public void initResources() {
		character.initResources();
	}

	public void render(Graphics2D g) {
		character.render(g);
	}

	public void update(long elapsed) {
		currentState.update(elapsed);
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
	
	public GameCharacter getCharacter(){
	    return character;
	}

	public void setCurrentDirection(int direction) {
		character.setCurrentDirection(direction);
	}
}
