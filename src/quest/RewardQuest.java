/*
 * Type of quest where the player is given a reward at the end
 */

package quest;

import inventory.Item;

import app.RPGame;

public class RewardQuest extends Quest
{
	private Item reward;

	public RewardQuest(String description, Item reward, Task... required ) {
		super(description, required);
		this.reward = reward;
	}

	public void completeQuest(RPGame game2) 
	{
		game2.addItems(reward);
	}
}
