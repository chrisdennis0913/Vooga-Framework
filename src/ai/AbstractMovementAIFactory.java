package ai;

import app.RPGame;

import com.google.gson.JsonElement;

import gameCharacter.GameCharacter;

public abstract class AbstractMovementAIFactory {
		
		public abstract boolean isThisType(String movementName);
		public abstract AbstractMovementAI constructMovement(RPGame game, GameCharacter gameChar, JsonElement jsonMovement);

}
