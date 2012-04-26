package npc;

import dialogue.SimpleDialogue;
import utils.Location;
import ai.ScriptedMovementAI;
import app.RPGame;

public class NPCTest1 extends NPC{

	public NPCTest1(RPGame game, Location loc, String configURL) {
		super(game, loc, configURL);
		int[][] testArray= new int[][] {{1, 2100}, {2, 2000}};
		movAI = new ScriptedMovementAI(game, this, testArray);
		dialogue = new SimpleDialogue("rsc/npc1.txt");
	}

}
