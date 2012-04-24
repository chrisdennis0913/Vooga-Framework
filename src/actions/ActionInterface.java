package actions;

import evented.Evented;
import utils.Jsonable;

public interface ActionInterface extends Evented {
	
	public Jsonable getJsonable();
	public void setEnabled(boolean enabled, boolean modifyActive);
	public void setActive(boolean active);
	public void setActiveDirection(int direction);
	public boolean isEnabled();
	public boolean isActive();
	
}
