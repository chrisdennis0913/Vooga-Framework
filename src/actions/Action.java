package actions;

import utils.Jsonable;
import evented.EventedItem;
import evented.EventedWrapper;

/**
 * An evented item intended for game object actions
 * (like walking or attacking). It can be set active
 * and enabled.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
 * 
 * @author Kirill Klimuk
 */

public abstract class Action extends EventedItem<ActionInterface> implements ActionInterface {

	private static final long serialVersionUID = 1L;
	
	private Jsonable json;
	private boolean enabled = false;
	private boolean active = false;

	public Action(EventedWrapper<ActionInterface> wrapper, Jsonable json) {
		super(wrapper);
		this.json = json;
	}
	
	public Jsonable getJsonable() {
		return json;
	}

	public void setEnabled(boolean enabled, boolean modifyActive) {
		if (!enabled && modifyActive)
			active = false;
		this.enabled = enabled;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isActive() {
		return active;
	}
}
