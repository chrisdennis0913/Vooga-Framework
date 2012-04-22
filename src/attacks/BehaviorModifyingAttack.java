package attacks;

import gameCharacter.GameCharacter;
import app.RPGame;

public class BehaviorModifyingAttack extends AbstractAttack{

	private AbstractBehaviorModifier bm;

	public BehaviorModifyingAttack(RPGame game, GameCharacter attacker, String name, AbstractBehaviorModifier bm) {
		super(game, attacker, name);
		this.bm = bm;
	}

	@Override
	public void performAttack(long elapsedTime) {
		target.registerBehaviorModifier(bm, true);
	}
	
	public AbstractBehaviorModifier getBehaviorModifier(){
		return bm;
	}

	@Override
	public boolean isAvailable(long elapsedTime) {
		// TODO Auto-generated method stub
		return active;
	}


}
