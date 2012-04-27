package quest;

import enemy.AbstractEnemy;
import enemy.TestEnemy;
import gameCharacter.GameCharacter;

/*
 * Type of quest where an enemy will appear after quest completion
 */

import app.RPGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class EnemyAttackQuest extends Quest
{
	private AbstractEnemy myEnemy;

	public EnemyAttackQuest(AbstractEnemy en, String description, Task... required) {
		super(description, required);
		myEnemy = en;
	}

	public void completeQuest(GameCharacter gC)
	{
		gC.getGame().getField().getGroup("enemies").add(myEnemy.getCharacter());
	}

	public JsonObject getJsonAttributes() {
		JsonObject json = new JsonObject();
		json.add("enemy", new JsonPrimitive(myEnemy.getName()));
		
		return json;
	}
	
	public static class EnemyAttackQuestFactory extends QuestFactory
	{
		public boolean isThisType(String questName) {
			return "Enemy Attack Quest".equals(questName);
		}

		public EnemyAttackQuest constructQuest(RPGame game, Task[] tasks, JsonObject jQuest) {
			SpriteGroup group = game.getField().getGroup("enemies");
			
			AbstractEnemy en = null;
			
			for (Sprite s: group.getSprites())
			{
				if (s instanceof GameCharacter)
				{
					String name = ((GameCharacter) s).getDecorator().getName();
					
					if (name.equals(jQuest.getAsJsonObject("enemy")))
					{
						en = (AbstractEnemy) ((GameCharacter) s).getDecorator();
					}
				}
			}
			
			if (en == null)
			{
				throw new RuntimeException("Enemy is not available");
			}
			
			return new EnemyAttackQuest(en, jQuest.getAsString(), tasks);
		}
	}
}
