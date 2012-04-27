package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

import com.golden.gamedev.object.Timer;
import com.google.gson.JsonElement;

public class SquareMovementAI extends AbstractMovementAI{
	
	private Timer timer;
	private int directionIndex;
	private final int[] directions = new int[] {GameCharacter.DIR_DOWN, GameCharacter.DIR_LEFT, GameCharacter.DIR_UP, GameCharacter.DIR_RIGHT};

	public SquareMovementAI(RPGame game, GameCharacter character, int timeToTurn) {
		super(game, character);
		timer = new Timer(timeToTurn);
	}

	@Override
	public void update(long elapsed) {
		if (timer.action(elapsed)){
			if (directionIndex == 3)
				directionIndex = 0;
			else
				directionIndex ++;
			character.setActiveDirection(directions[directionIndex]);
			character.setVelocity(0.05);
		}
	}

	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		
	}
	
	public static class Factory extends AbstractMovementAIFactory{

		@Override
		public boolean isThisType(String movementName) {
			return movementName.equals("SquareMovementAI");
		}
		
		@Override
		public AbstractMovementAI constructMovement(RPGame game, GameCharacter gameChar, JsonElement jsonMovement) {						
			return new SquareMovementAI(game, gameChar, jsonMovement.getAsJsonObject().get("timeToTurn").getAsInt());
		}			
	}

}
