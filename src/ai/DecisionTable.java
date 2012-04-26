package ai;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

import attacks.AbstractAttack;

public class DecisionTable {

	private HashMap<String,HashMap<AbstractGameHeuristic,Double>> table = new HashMap<String,HashMap<AbstractGameHeuristic,Double>>();

	public void addAttack(String attackName){
		table.put(attackName, new HashMap<AbstractGameHeuristic,Double>());
	}
	
	public void addHeuristicToAttack(String attackName,AbstractGameHeuristic heuristic, double weight){
		table.get(attackName).put(heuristic, weight);
	}
	
	public Set<String> getAttackNames(){
		return table.keySet();
	}
	
	
	public static class AttackComparator implements Comparator<AbstractAttack>{

		DecisionTable decisionTable;
		
		public AttackComparator(DecisionTable dt){
			decisionTable = dt;
		}
		
		@Override
		public int compare(AbstractAttack a1, AbstractAttack a2) {
			return - evaluateAttackValue(a1) + evaluateAttackValue(a2);	//higher score means first
		}
		
		public int evaluateAttackValue(AbstractAttack at){
			HashMap<AbstractGameHeuristic,Double> attackConditions = decisionTable.table.get(at.getName());
			int totalValue = 0;
			for(AbstractGameHeuristic cond: attackConditions.keySet()){
				if (cond.getHeuristic() > 0.0){
					System.err.println(at.getName() + cond + attackConditions.get(cond));
					totalValue += attackConditions.get(cond);
				}
			}
			return totalValue;
		}
		
	}
}
