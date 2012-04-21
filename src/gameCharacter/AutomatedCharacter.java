package gameCharacter;

import dialogue.AbstractDialogue;

public abstract class AutomatedCharacter extends GameCharacter{

	//--- CONTROL FIELDS --------------------------------------
	private AbstractMovementAI movAI;
	private AbstractDialogue dialogue;
	
	//--- STATE FIELDS ----------------------------------------
	private boolean alive;
	private boolean canDie;
	
	public abstract void setAlive(boolean alive);
	public abstract boolean isAlive();
	
	public AutomatedCharacter(){
		super();
	}
	
	public void update(long elapsedTime){
		movAI.update(elapsedTime);
		dialogue.update(elapsedTime);
	}
	
}
