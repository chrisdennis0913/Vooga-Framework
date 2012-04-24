package ai;

import com.golden.gamedev.object.Timer;

import gameCharacter.GameCharacter;
import app.RPGame;

public class SquareMovementAI extends AbstractMovementAI{
	
	private Timer timer;
	private int direction;
	private final int[][] directions = new int[][] {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

	public SquareMovementAI(RPGame game, GameCharacter character, int timeToTurn) {
		super(game, character);
		timer = new Timer(timeToTurn);
	}

	@Override
	public void update(long elapsed) {
		if (timer.action(elapsed)){
			if (direction == 3)
				direction = 0;
			else
				direction ++;
			character.setActiveDirection(direction);
			character.setVelocity(1);
		}
	}

}
