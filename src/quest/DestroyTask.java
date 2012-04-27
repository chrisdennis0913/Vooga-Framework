/* 
 * Type of task where player must destroy a certain NPC
 */

package quest;

import enemy.AbstractEnemy;

public class DestroyTask extends Task
{
	private AbstractEnemy recipient;

	public DestroyTask(String description, AbstractEnemy recipient) 
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
