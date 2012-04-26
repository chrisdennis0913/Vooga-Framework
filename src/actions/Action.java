package actions;

import com.google.gson.JsonObject;

import evented.EventedItem;
import evented.EventedWrapper;

/**
 * An evented item intended for game object actions
 * (like walking or attacking). It can be set active
 * and enabled.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
 * 
 * @author Kirill Klimuk
 */

public class Action extends EventedItem<ActionInterface> implements ActionInterface {

	private static final long serialVersionUID = 1L;
	
	private JsonObject json;
	private boolean enabled = false;
	private boolean active = false;

	public Action(EventedWrapper<ActionInterface> wrapper, JsonObject json) {
		super(wrapper);
		this.json = json;
	}
	
	public JsonObject getJsonObject() {
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

	public void initResources() {}

	public void setActiveDirection(int direction) {}
}
