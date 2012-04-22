package evented;

import com.golden.gamedev.object.Sprite;

/**
 * A specific evented item which includes the EventedWrapper
 * as a parent.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
 * 
 * @author Kirill Klimuk
 */

public abstract class EventedItem<T extends EventedItem<T>> extends Sprite implements Evented{

	protected EventedWrapper<T> wrapper;
	
	public EventedItem(EventedWrapper<T> wrapper) {
		this.wrapper = wrapper;
	}
	
	protected EventedItem () {
    }

    public EventedWrapper<T> getWrapper() {
		return wrapper;
	}
	
}
