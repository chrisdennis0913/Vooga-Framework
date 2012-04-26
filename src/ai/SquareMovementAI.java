package ai;

import com.golden.gamedev.object.Timer;

import gameCharacter.GameCharacter;
import app.RPGame;

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

}
