package evented;

/**
 * A specific evented item which includes the EventedWrapper
 * as a parent.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
 * 
 * @author Kirill Klimuk
 */

public abstract class EventedItem<T extends EventedItem<T>> implements Evented{

	private EventedWrapper<T> wrapper;
	
	public EventedItem(EventedWrapper<T> wrapper) {
		this.wrapper = wrapper;
	}
	
	protected EventedItem () {
    }

    public EventedWrapper<T> getWrapper() {
		return wrapper;
	}
	
}
