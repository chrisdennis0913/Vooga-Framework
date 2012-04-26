package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

/**
 * Returns a value that is used to calculate the fitness
 * of an attack for a given situation. The heuristics are
 * linearly combined to produce a score and the weights for 
 * the combination are defined in the decision table.
 * @author jameshong
 *
 */
public abstract class AbstractGameHeuristic {

	RPGame game;
	GameCharacter attacker;
	String conditionName;
	
	public AbstractGameHeuristic(RPGame game, GameCharacter character, String conditionName){
		this.game = game;
		this.attacker = character;
		this.conditionName = conditionName;
	}
	
	public abstract double getHeuristic();
	
}
