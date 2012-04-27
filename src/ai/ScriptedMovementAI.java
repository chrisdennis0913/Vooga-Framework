package ai;


import gameCharacter.GameCharacter;

import java.util.ArrayList;
import java.util.List;

import utils.JsonUtil;

import app.RPGame;

import com.golden.gamedev.object.Timer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class ScriptedMovementAI extends AbstractMovementAI{
	
	int[][] actions;
	int actionsIndex;
	Timer timer;

	/**
	 * takes in an array such that the character moves in {direction, time} in order of the array
	 * @param game
	 * @param character
	 * @param actions
	 */
	public ScriptedMovementAI(RPGame game, GameCharacter character, int[][] actions) {
		super(game, character);
		this.actions = actions;
		actionsIndex = 0;
		character.setVelocity(0.05);
		character.setActiveDirection(actions[0][0]);
		timer = new Timer(actions[0][1]);
	}

	@Override
	public void update(long elapsedTime) {
		if (actionsIndex == actions.length){
			character.setVelocity(0);
			character.stop();
		}
		else if (timer.action(elapsedTime)){
			actionsIndex++;
			if (actionsIndex != actions.length){
				character.setActiveDirection(actions[actionsIndex][0]);
				timer = new Timer(actions[actionsIndex][1]);
			}
		}
	}
	
	public static class Factory extends AbstractMovementAIFactory{

		@Override
		public boolean isThisType(String movementName) {
			// TODO Auto-generated method stub
			return movementName.equals("ScriptedMovementAI");
		}
		
		@Override
		public AbstractMovementAI constructMovement(RPGame game, GameCharacter gameChar, JsonElement jsonMovement) {
			JsonArray actionsOuter = jsonMovement.getAsJsonObject().getAsJsonArray("actions");
			List<int[]> actionsList = new ArrayList<int[]>();
			
			for(int i=0; i<actionsOuter.size(); i++){
				JsonArray actionsInner = actionsOuter.get(i).getAsJsonArray();
				actionsList.add(JsonUtil.JsonArrayToIntArray(actionsInner));
			}
			
			int[][] actions = new int[actionsList.size()][];
			for(int i=0; i<actionsList.size(); i++){
				actions[i] = actionsList.get(i);
			}			
			return new ScriptedMovementAI(game, gameChar, actions);
		}	
		
	}

	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		
	}

}