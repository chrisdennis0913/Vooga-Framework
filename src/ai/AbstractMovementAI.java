package ai;

import gameCharacter.GameCharacter;
import app.RPGame;
import controllers.MotionController;

public abstract class AbstractMovementAI extends MotionController{
	
	GameCharacter character;

	public AbstractMovementAI(RPGame game, GameCharacter character) {
		super(game, character);
		this.character = character;
	}

	@Override
	public abstract void update(long elapsedTime);

}
