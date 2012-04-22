package gameCharacter;

import utils.Location;

import com.golden.gamedev.Game;

//import dialogue.AbstractDialogue;

public abstract class AutomatedCharacter extends GameCharacter{
	
	private static final long serialVersionUID = 1L;

	//--- CONTROL FIELDS --------------------------------------
//	private AbstractMovementAI movAI;
//	private AbstractDialogue dialogue;
	
	//--- STATE FIELDS ----------------------------------------
//	private boolean alive;
//	private boolean canDie;
	
	public abstract void setAlive(boolean alive);
	public abstract boolean isAlive();
	
	public AutomatedCharacter(Game game, Location loc, String configURL){
		super(game, null, configURL);
	}
	
	public void update(long elapsedTime){
//		movAI.update(elapsedTime);
//		dialogue.update(elapsedTime);
	}
	
}
