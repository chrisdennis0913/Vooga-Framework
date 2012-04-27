package ai;

import gameCharacter.GameCharacter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;

import app.RPGame;
import controllers.MotionController;

public abstract class AbstractMovementAI extends MotionController{
	
	GameCharacter character;
	private static List<AbstractMovementAIFactory> AbstractMovementAIs;
	
	static
	{			
		AbstractMovementAIs = new ArrayList<AbstractMovementAIFactory>();
		AbstractMovementAIs.add(new ScriptedMovementAI.Factory());	
		AbstractMovementAIs.add(new SquareMovementAI.Factory());
	}	

	public AbstractMovementAI(RPGame game, GameCharacter character) {
		super(game, character);
		this.character = character;
	}

	@Override
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
