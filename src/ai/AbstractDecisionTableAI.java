package ai;

import java.util.HashMap;
import java.util.PriorityQueue;

import app.RPGame;
import attacks.AbstractAttack;
import enemy.AbstractEnemy;

/**
 * Attack chooser that uses a decision table to determine
 * the best attack to use in a given situation. It calculates
 * a score for each attack and chooses the one with the
 * largest score.
 * @author jameshong
 *
 */
public abstract class AbstractDecisionTableAI extends AbstractAttackAI{

	DecisionTable dTable = new DecisionTable();
	
	public AbstractDecisionTableAI(RPGame game, AbstractEnemy character) {
		super(game, character);
		constructTable();
	}

	public abstract void constructTable();
	
	@Override
	public void update(long elapsedTime) {
		if(character.isAlive()){
			AbstractAttack choice = pickBestAttack();
			if(choice != null)
				choice.performAttack(elapsedTime);
		}
	}

	@Override
	public boolean shouldAttack() {
		//This AI is aggressive: we should always attack
		return true;
	}

	public AbstractAttack pickBestAttack(){
		HashMap<String, AbstractAttack> attackMap = character.getAttacks();
		PriorityQueue<AbstractAttack> attackQ = new PriorityQueue<AbstractAttack>(10,new DecisionTable.AttackComparator(dTable));
		for(String attackName : dTable.getAttackNames()){
			if(attackMap.containsKey(attackName))
				attackQ.add(attackMap.get(attackName));
		}
		AbstractAttack choice;
		while((choice = attackQ.poll()) != null)
			if(choice.isActive() && choice.isAvailable(0))
				return choice;
		return null;
	}
	

	
}
