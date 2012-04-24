package gameCharacter;

import utils.Location;
import ai.SquareMovementAI;
import app.RPGame;
import dialogue.AbstractDialogue;
import dialogue.AbstractDialogue.DialogueObject;

/**
 * Character that is non-playable and has movements
 * controlled by an AI algorithm. Can have dialogue
 * and alive states.
 * @author jameshong
 *
 */
public abstract class AutomatedCharacter extends GameCharacter{
	
	private static final long serialVersionUID = 1L;

	//--- CONTROL FIELDS --------------------------------------
	protected MotionController movAI;
	protected AbstractDialogue dialogue;

	//--- STATE FIELDS ----------------------------------------
	private boolean alive;
	private boolean canDie;
	
	public AutomatedCharacter(RPGame game, Location loc, String configURL){
		super(game, loc, configURL);
		movAI = new SquareMovementAI(game, this, 1000);
	}

	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void update(long elapsed){
		movAI.update(elapsed);
		super.update(elapsed);
	}
	
	public String respondToTalk(DialogueObject choice){
		if (choice != null)
			dialogue.goToNextLine(choice);
		return dialogue.getCurrentLine();
	}
	
}