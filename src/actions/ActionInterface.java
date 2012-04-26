package actions;

import com.google.gson.JsonObject;

import evented.Evented;

public interface ActionInterface extends Evented {
	
	public JsonObject getJsonObject();
	public void setEnabled(boolean enabled, boolean modifyActive);
	public void setActive(boolean active);
	public void setActiveDirection(int direction);
	public boolean isEnabled();
	public boolean isActive();
	
}
