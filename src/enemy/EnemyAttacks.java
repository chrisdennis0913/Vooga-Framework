package enemy;

import java.util.ArrayList;

import app.RPGame;
import attacks.AbstractAttack;
import attacks.AttackFactory;
import attacks.PoisoningRangeAttack;
import attacks.ShootingAttack;

public class EnemyAttacks {

	private static ArrayList<AttackFactory> attacks = new ArrayList<AttackFactory>();
	
	static{
		attacks.add(new ShootingAttack.ShootingAttackFactory());
		attacks.add(new PoisoningRangeAttack.PoisoningRangeAttackFactory());
	}
	
	public static AbstractAttack createAttack(String attackName, RPGame game, AbstractEnemy enemy){
		for(AttackFactory af: attacks){
			if(af.isThisType(attackName))
				return af.constructAttack(game, enemy);
		}
		throw new RuntimeException("Attack " + attackName + " not defined");
	}
}
