package controllers;

import evented.EventedItem;
import gameCharacter.GameCharacter;
import app.RPGame;

/**
 * Controls the movement of the game character.
 * Can be scripted, AI-based or human player controlled.
 * 
 * @author jameshong
 *
 */
public abstract class MotionController extends EventedItem<Controller> implements Controller{

	protected RPGame game;
	GameCharacter character;
	
	public MotionController(RPGame game, GameCharacter character){
		this.game = game;
		this.character = character;
	}
		
}
