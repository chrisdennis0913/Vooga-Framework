package evented;

import com.golden.gamedev.object.Sprite;

/**
 * A specific evented item which includes the EventedWrapper
 * as a parent.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
 * 
 * @author Kirill Klimuk
 */

public abstract class EventedItem<T extends Evented> extends Sprite implements Evented{
	private static final long serialVersionUID = 1L;


	protected EventedWrapper<T> wrapper;
	
	public EventedItem(EventedWrapper<T> eventedWrapper) {
		this.wrapper = eventedWrapper;
	}
	
	protected EventedItem () {
    }

    public EventedWrapper<T> getWrapper() {
		return wrapper;
	}
	
}
