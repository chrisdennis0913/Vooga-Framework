package quest;

import gameCharacter.GameCharacter;
import app.RPGame;

import com.google.gson.JsonObject;

public abstract class TaskFactory
{
	public abstract boolean isThisType(String taskName);
	public abstract Task constructTask(RPGame game, GameCharacter gC,JsonObject jTask);
}