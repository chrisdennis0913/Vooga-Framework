package attacks;

import gameCharacter.GameCharacter;
import app.RPGame;

public abstract class BehaviorModifyingAttack extends AbstractAttack{

	private AbstractBehaviorModifier bm;
	
	public BehaviorModifyingAttack(RPGame game, GameCharacter attacker, String name) {
		super(game, attacker, name);
	}

	@Override
	public void performAttack(long elapsedTime) {
		target.getBehaviorModifiers().addFirst(bm);
	}
	
	public AbstractBehaviorModifier getBehaviorModifier(){
		return bm;
	}

}
