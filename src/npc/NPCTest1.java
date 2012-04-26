package npc;

import gameCharacter.GameCharacter;
import dialogue.AbstractDialogue.DialogueObject;
import dialogue.SimpleDialogue;
import state.WalkingState;
import utils.Location;
import ai.ScriptedMovementAI;
import ai.SquareMovementAI;
import app.RPGame;

public class NPCTest1 extends NPC{
	
	private boolean hasTalked;

	/**
	 * Computer-generated serial ID number 
	 */
	private static final long serialVersionUID = 4483591744499315422L;

	public NPCTest1(GameCharacter character) {
		super(character);
		hasTalked = false;
		int[][] testArray= new int[][] {{1, 2100}, {2, 2000}};
		this.setCurrentState(new WalkingState(new SquareMovementAI(this.character.getGame(), this.getCharacter(), 300)));
		dialogue = new SimpleDialogue("rsc/savedmaps/npc1.txt");
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTalk(DialogueObject choice){
		if (!hasTalked){
			hasTalked = true;
		}
		else{
			dialogue.goToNextLine(new SimpleDialogue("").new SimpleDialogueObject());
		}
		return dialogue.getCurrentLine();
	}

}
