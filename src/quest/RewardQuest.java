package quest;

import gameCharacter.GameCharacter;
import inventory.ConcreteItem;
import inventory.Item;
import app.RPGame;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class RewardQuest extends Quest
{
	private Item reward;
	
	public RewardQuest(Item reward, String description, Task... required) {
		super(description, required);
		this.reward = reward;
	}

	public void completeQuest(GameCharacter gC) 
	{
		gC.getInventory().add(reward);
	}

	public JsonObject getJsonAttributes() 
	{
		JsonObject json = new JsonObject();
		json.add("item", new JsonPrimitive(reward.getName()));
		
		return json;
	}
	
	public static class RewardQuestFactory extends QuestFactory
	{
		public boolean isThisType(String questName) {
			return "Reward Quest".equals(questName);
		}
	
		public RewardQuest constructQuest(RPGame game, Task[] tasks, JsonObject jQuest) {
			return new RewardQuest(new ConcreteItem(game, jQuest.getAsJsonObject("item")), "Reward Quest", tasks);
		}
	}
}
