package attacks;

import gameCharacter.GameCharacter;
import app.RPGame;

public abstract class AbstractBehaviorModifier{

	RPGame game;
	protected GameCharacter target;
	
	public AbstractBehaviorModifier(RPGame game, GameCharacter target){
		this.game = game;
		this.target = target;
	}
	
	public void deregister(){
		target.getBehaviorModifiers().removeFirstOccurrence(this);
	}

	public abstract void setUp(long elapsedTime);
	public abstract boolean unsetUp(long elapsedTime);

}
