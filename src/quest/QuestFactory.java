package quest;

import enemy.AbstractEnemy;
import gameCharacter.GameCharacter;
import app.RPGame;

import com.google.gson.JsonObject;

public abstract class QuestFactory
{
	public abstract boolean isThisType(String questName);
	public abstract Quest constructQuest(RPGame game, Task[] tasks,JsonObject jQuest);
}
