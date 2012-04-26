package gameCharacter;

import controllers.MotionController;
import utils.Location;
import ai.SquareMovementAI;
import app.RPGame;
import dialogue.AbstractDialogue;
import dialogue.AbstractDialogue.DialogueObject;
import evented.EventedWrapper;

/**
 * Character that is non-playable and has movements
 * controlled by an AI algorithm. Can have dialogue
 * and alive states.
 * @author jameshong
 *
 */
public abstract class AutomatedCharacter extends GameCharacter{
	
	private static final long serialVersionUID = 1L;

	
	protected AbstractDialogue dialogue;

	private boolean alive;
	private boolean canDie;
	
	public AutomatedCharacter(RPGame game, Location loc, String configURL){
		super(game, loc, configURL);
	}

	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void update(long elapsed){
		super.update(elapsed);
	}
	
	public String respondToTalk(DialogueObject choice){
		if (choice != null)
			dialogue.goToNextLine(choice);
		return dialogue.getCurrentLine();
	}
	
}