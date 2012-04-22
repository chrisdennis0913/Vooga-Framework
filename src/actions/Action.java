package actions;

import java.awt.Graphics2D;

import evented.EventedItem;
import evented.EventedWrapper;

public abstract class Action extends EventedItem<Action> {

	private boolean enabled = false;
	private boolean active = false;

	public Action(EventedWrapper<Action> wrapper) {
		super(wrapper);
	}
	
	public abstract void update(long elapsed);

	public abstract void render(Graphics2D g);

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
