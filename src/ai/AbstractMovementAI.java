package ai;

import gameCharacter.GameCharacter;

import java.util.ArrayList;
import java.util.List;

import app.RPGame;

import com.google.gson.JsonElement;

public abstract class AbstractMovementAI {
	
	RPGame game;
	GameCharacter character;
	boolean active = true;
	
	private static List<AbstractMovementAIFactory> AbstractMovementAIs;
	
	static
	{			
		AbstractMovementAIs = new ArrayList<AbstractMovementAIFactory>();
		AbstractMovementAIs.add(new ScriptedMovementAI.Factory());	
		AbstractMovementAIs.add(new SquareMovementAI.Factory());
	}	

	public AbstractMovementAI(RPGame game, GameCharacter character) {
		this.game = game;
		this.character = character;
	}

	public void setActive(boolean active){
		this.active = active;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public abstract void update(long elapsedTime);

	public static AbstractMovementAI getAbstractMovementAI(RPGame game, GameCharacter gameChar, JsonElement jsonMovement){

		for(AbstractMovementAIFactory m : AbstractMovementAIs){
			if(m.isThisType(jsonMovement.getAsJsonObject().get("type").getAsString())){
				return m.constructMovement(game, gameChar, jsonMovement);
			}
		}
		throw new RuntimeException("Given name of AbstractMovementAI not recognized");
	}
}
