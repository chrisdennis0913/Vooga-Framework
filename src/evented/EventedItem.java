package evented;

import app.RPGame;

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
	protected RPGame game;
	
	public EventedItem(EventedWrapper<T> eventedWrapper) {
		this.wrapper = eventedWrapper;
	}
	
	public EventedItem(RPGame game) {
		this.game = game;
	}
	
	public boolean hasWrapper() {
		if (getWrapper() == null)
			return false;
		return true;
	}
	
	protected EventedItem () {
    }

    public EventedWrapper<T> getWrapper() {
		return wrapper;
	}
    
    public RPGame getGame() {
    	return game;
    }
	
}