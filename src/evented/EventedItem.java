package evented;

public abstract class EventedItem<T extends EventedItem<?>> implements Evented{

	private EventedWrapper<T> wrapper;
	
	public EventedItem(EventedWrapper<T> wrapper) {
		this.wrapper = wrapper;
	}
	
	public EventedWrapper<T> getWrapper() {
		return wrapper;
	}
	
}
