package attacks;

import java.util.Iterator;
import java.util.LinkedList;


public class BehaviorModifierContainer {

	private LinkedList<AbstractBehaviorModifier> bmList = 
			new LinkedList<AbstractBehaviorModifier>();
	
	/**
	 * Traverses through the list of behavior modifiers in
	 * normal order. Performs modifying effect.
	 */
	public void setUpAll(long elapsedTime){
		for (AbstractBehaviorModifier bm : bmList)
			bm.setUp(elapsedTime);
	}
	
	/**
	 * Traverses through the list of behavior modifiers in
	 * reverse order. Undoes the modifying effect.
	 * Removes expired behavior modifiers from list.
	 */
	public void unsetUpAll(long elapsedTime){
		Iterator<AbstractBehaviorModifier> bmReverse = bmList
				.descendingIterator();
		while (bmReverse.hasNext())
			if (bmReverse.next().unsetUp(elapsedTime))
				bmReverse.remove();
	}
	
	public void addFirst(AbstractBehaviorModifier bm){
		bmList.addFirst(bm);
	}
	
	public void addLast(AbstractBehaviorModifier bm){
		bmList.addLast(bm);
	}
	
	public void removeFirstOccurrence(AbstractBehaviorModifier bm){
		bmList.removeFirstOccurrence(bm);
	}
	
	public void removeLastOccurrence(AbstractBehaviorModifier bm){
		bmList.removeLastOccurrence(bm);
	}
	
	public void clear(){
		bmList.clear();
	}
}
