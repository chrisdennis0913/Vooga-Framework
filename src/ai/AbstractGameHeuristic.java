package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

public abstract class AbstractGameHeuristic {

	RPGame game;
	GameCharacter character;
	String conditionName;
	
	public AbstractGameHeuristic(RPGame game, GameCharacter character, String conditionName){
		this.game = game;
		this.character = character;
		this.conditionName = conditionName;
	}
	
	public abstract boolean getHeuristicBool();
	
}
