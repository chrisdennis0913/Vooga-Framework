package ai;

import com.golden.gamedev.object.Timer;

import gameCharacter.GameCharacter;
import app.RPGame;

public class SquareMovementAI extends AbstractMovementAI{
	
	private Timer timer;
	private int directionIndex;
	private final int[][] directions = new int[][] {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

	public SquareMovementAI(RPGame game, GameCharacter character, int timeToTurn) {
		super(game, character);
		timer = new Timer(timeToTurn);
	}

	@Override
	public void update(long elapsedTime) {
		if (timer.action(elapsedTime)){
			if (directionIndex == 3)
				directionIndex = 0;
			else
				directionIndex ++;
			character.setSpeed(directions[directionIndex][0], directions[directionIndex][1]);
		}
	}

}
