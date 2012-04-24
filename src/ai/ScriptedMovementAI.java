package ai;

import com.golden.gamedev.object.Timer;

import gameCharacter.GameCharacter;
import app.RPGame;

public class ScriptedMovementAI extends AbstractMovementAI{
	
	int[][] actions;
	int actionsIndex;
	Timer timer;

	/**
	 * takes in an array such that the character moves in {direction, time} in order of the array
	 * @param game
	 * @param character
	 * @param actions
	 */
	public ScriptedMovementAI(RPGame game, GameCharacter character, int[][] actions) {
		super(game, character);
		this.actions = actions;
		actionsIndex = 0;
	}

	@Override
	public void update(long elapsedTime) {
		if (actionsIndex == actions.length)
			character.setVelocity(0);
		if (timer.action(elapsedTime)){
			
		}
	}

}