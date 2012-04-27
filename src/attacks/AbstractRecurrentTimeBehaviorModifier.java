package attacks;

import gameCharacter.GameCharacter;

import com.golden.gamedev.object.Timer;

import app.RPGame;

/**
 * Behavior modifier that performs modification
 * on the game character at frequent periods.
 * For example, it could deduct health points on every second.
 * 
 * @author jameshong
 *
 */
public abstract class AbstractRecurrentTimeBehaviorModifier extends AbstractBehaviorModifier{

	Timer timer;
	int executions;
	
	public AbstractRecurrentTimeBehaviorModifier(RPGame game, GameCharacter target, int intervalTime, int executions) {
		super(game, target);
		timer = new Timer(intervalTime);
		this.executions = executions;
	}
	
	public abstract void doRecurrent(long elapsedTime);
	
	@Override
	public void setUp(long elapsedTime){
		if(!timer.action(elapsedTime))
			return;
		doRecurrent(elapsedTime);
		executions--;
	}
	
	@Override
	public boolean unsetUp(long elapsedTime){
		if(executions <= 0)
			return true;
		return false;
	}
	
}
