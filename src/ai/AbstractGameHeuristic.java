package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

public abstract class AbstractGameHeuristic {

	RPGame game;
	GameCharacter attacker;
	String conditionName;
	
	public AbstractGameHeuristic(RPGame game, GameCharacter character, String conditionName){
		this.game = game;
		this.attacker = character;
		this.conditionName = conditionName;
	}
	
	public abstract boolean getHeuristicBool();
	
}
