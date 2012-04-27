package attacks;

import enemy.AbstractEnemy;
import gameCharacter.GameCharacter;
import app.RPGame;

public abstract class AttackFactory {

	public abstract boolean isThisType(String attackName);
	public abstract AbstractAttack constructAttack(RPGame game, AbstractEnemy enemy);
	
}
