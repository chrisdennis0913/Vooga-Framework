package npc;

import gameCharacter.GameCharacter;
import quest.QuestGiverNPC;
import state.MovingAttackingState;
import state.TalkingState;
import ai.SquareMovementAI;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dialogue.SimpleDialogue;
import dialogue.SimpleDialogue.SimpleDialogueObject;

public class NPCTest1 extends QuestGiverNPC
{
	/**
	 * Computer-generated serial ID number
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 4483591744499315422L;
	private boolean timeToChange = false;

	public NPCTest1(GameCharacter character, JsonElement jsonMovement) {
		super(character);

		name = "NPCTest1";
		
		this.setCurrentState(new TalkingState());
		dialogue = new SimpleDialogue("rsc/savedmaps/npc1.txt");
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String getTalk(SimpleDialogueObject choice)
	{
		if (questComplete)
			dialogue.goToNextLine(new SimpleDialogue.SimpleDialogueObject());
		if(dialogue.isDone())
		{
			timeToChange = true;
		}
		return dialogue.getCurrentLine();
	}

	public static class NPCTest1Factory extends NPCFactory {

		public NPCTest1Factory() {
		};

		@Override
		public boolean isThisType(String npcName) {
			return npcName.equals("NPCTest1");
		}


		public NPC constructNPC(GameCharacter gameChar, JsonElement jsonMovement) {
			return new NPCTest1(gameChar, jsonMovement);
		}

	}

	@Override
	public JsonObject getJsonAttributes() {
		// TODO Auto-generated method stub
		return new JsonObject();
	}
	
	public void changeState()
	{
		if (timeToChange)
		{
			SquareMovementAI movement = new SquareMovementAI(this.character.getGame(), this.getCharacter(), 300);
			setCurrentState(new MovingAttackingState(movement,null,null));
			timeToChange = false;
		}
	}

}
