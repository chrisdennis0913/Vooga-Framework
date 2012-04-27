package attacks;

import enemy.AbstractEnemy;
import gameCharacter.GameCharacter;
import app.RPGame;

public class PoisoningRangeAttack extends BehaviorModifyingRangeAttack{

	private static int MIN_DISTANCE = 70;
	public PoisoningRangeAttack(RPGame game, GameCharacter attacker,
			String name) {
		super(game, attacker, "poisoning", MIN_DISTANCE, new PoisonedBehaviorModifier(game), "player");
	}

	@Override
	public int calculateDamage() {
		return 1;
	}
	
	public static class PoisoningRangeAttackFactory extends AttackFactory{

		@Override
		public boolean isThisType(String attackName) {
			return "poisoning".equals(attackName);
		}

		@Override
		public AbstractAttack constructAttack(RPGame game,
				AbstractEnemy enemy) {
			return new PoisoningRangeAttack(game,enemy.getCharacter(),"poisoning");
		}
		
	}

}
