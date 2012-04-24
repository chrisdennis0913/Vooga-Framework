package ai;

import app.RPGame;
import attacks.AbstractAttack;

import gameCharacter.Enemy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public abstract class AbstractDecisionTableAI extends AbstractAttackAI{

	HashMap<String,HashMap<AbstractGameHeuristic,Integer>> decisionTable;
	
	public AbstractDecisionTableAI(RPGame game, Enemy character) {
		super(game, character);
	}

	@Override
	public void update(long elapsedTime) {
		if(character.isAlive()){
			AbstractAttack choice = pickBestAttack();
			if(choice != null)
				character.attack(pickBestAttack(),elapsedTime);
		}
	}

	@Override
	public boolean shouldAttack() {
		//This AI is aggressive: we should always attack
		return true;
	}

	public AbstractAttack pickBestAttack(){
		HashMap<String, AbstractAttack> attackMap = character.getAttacks();
		PriorityQueue<AbstractAttack> attackQ = new PriorityQueue<AbstractAttack>(10,new AttackComparator(this));
		for(String attackName : decisionTable.keySet()){
			if(attackMap.containsKey(attackName))
				attackQ.add(attackMap.get(attackName));
		}
		AbstractAttack choice;
		while((choice = attackQ.poll()) != null)
			if(choice.isActive() && choice.isAvailable(0))
				return choice;
		return null;
	}
	
	public static class AttackComparator implements Comparator<AbstractAttack>{

		AbstractDecisionTableAI myAI;
		
		public AttackComparator(AbstractDecisionTableAI ai){
			myAI = ai;
		}
		
		@Override
		public int compare(AbstractAttack a1, AbstractAttack a2) {
			return - evaluateAttackValue(a1) + evaluateAttackValue(a2);	//higher score means first
		}
		
		public int evaluateAttackValue(AbstractAttack at){
			HashMap<AbstractGameHeuristic,Integer> attackConditions = myAI.decisionTable.get(at.getName());
			int totalValue = 0;
			for(AbstractGameHeuristic cond: attackConditions.keySet()){
				if (cond.getHeuristicBool()){
					System.err.println(at.getName() + cond + attackConditions.get(cond));
					totalValue += attackConditions.get(cond);
				}
			}
			return totalValue;
		}
		
	}

	
}
