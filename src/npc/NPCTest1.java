package npc;

import dialogue.SimpleDialogue;
import utils.Location;
import ai.ScriptedMovementAI;
import app.RPGame;

public class NPCTest1 extends NPC{

	/**
	 * Computer-generated serial ID number 
	 */
	private static final long serialVersionUID = 4483591744499315422L;

	public NPCTest1(RPGame game, Location loc, String configURL) {
		super(game, loc, configURL);
		int[][] testArray= new int[][] {{1, 2100}, {2, 2000}};
		movAI = new ScriptedMovementAI(game, this, testArray);
		dialogue = new SimpleDialogue("rsc/savedmaps/npc1.txt");
	}

}
