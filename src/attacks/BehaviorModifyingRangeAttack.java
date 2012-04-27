package attacks;

import gameCharacter.GameCharacter;
import app.RPGame;

/**
 * Attack that attaches a behavior modifier to
 * the target. It could be an attack that slows down
 * the target, or weakens it progressively, for example.
 * @author jameshong
 *
 */
public abstract class BehaviorModifyingRangeAttack extends AbstractRangeAttack{

	private AbstractBehaviorModifier bm;
	
	public BehaviorModifyingRangeAttack(RPGame game, GameCharacter attacker, String name, int minDistance, 
			AbstractBehaviorModifier bm, String... targetGroups) {
		super(game, attacker, name, minDistance, targetGroups);
		this.bm = bm;
	}

	@Override
	public void performAttackIndividual(long elapsedTime) {
		target.getBehaviorModifiers().addFirst(bm);
	}
	
	public AbstractBehaviorModifier getBehaviorModifier(){
		return bm;
	}

}
