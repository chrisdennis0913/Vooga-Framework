package actions;

import utils.Jsonable;
import evented.EventedItem;
import evented.EventedWrapper;

public abstract class ActionDecorator extends EventedItem<ActionInterface> implements ActionInterface{

	private static final long serialVersionUID = 1L;

	protected Action action;
	
	public ActionDecorator(Action action) {
		this.action = action;
	}
	
	public EventedWrapper<ActionInterface> getWrapper() {
		return action.getWrapper();
	}
	
	public void initResources() {
		action.initResources();
	}

	public Jsonable getJsonable() {
		return action.getJsonable();
	}

	public void setEnabled(boolean enabled, boolean modifyActive) {
		action.setEnabled(enabled, modifyActive);
	}

	public boolean isEnabled() {
		return action.isEnabled();
	}
	
	public void setActiveDirection(int direction) {
		action.setActiveDirection(direction);
	}

	public void setActive(boolean active) {
		action.setActive(active);
	}

	public boolean isActive() {
		return action.isActive();
	}
	
}
