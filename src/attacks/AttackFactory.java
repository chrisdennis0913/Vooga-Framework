package attacks;

import app.RPGame;
import enemy.AbstractEnemy;

public abstract class AttackFactory {

	public abstract boolean isThisType(String attackName);
	public abstract AbstractAttack constructAttack(RPGame game, AbstractEnemy enemy);
	
}
