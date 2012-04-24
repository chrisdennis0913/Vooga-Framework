package gameCharacter;

import utils.Location;
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
	private MotionController movAI;
	private AbstractDialogue dialogue;

	//--- STATE FIELDS ----------------------------------------
	private boolean alive;
	private boolean canDie;
	
	public AutomatedCharacter(RPGame game, Location loc, String configURL){
		super(game, null, configURL);
	}

	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void update(long elapsedTime){
		movAI.update(elapsedTime);
	}
	
	public String respondToTalk(DialogueObject choice){
		if (choice != null)
			dialogue.goToNextLine(choice);
		return dialogue.getCurrentLine();
	}
	
}
