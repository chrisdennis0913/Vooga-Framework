/* 
 * Type of task where player must destroy a certain NPC
 */

package quest;

import enemy.TestEnemy;

public class DestroyTask extends Task
{
	private TestEnemy recipient;

	public DestroyTask(String description, TestEnemy recipient) 
	{
		super(description);
		this.recipient = recipient;
	}

	public boolean checkComplete()
	{
		isComplete = (recipient.getHealth() <= 0);
		return isComplete;
	}

}
