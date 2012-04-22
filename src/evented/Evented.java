package evented;

import java.awt.Graphics2D;

public interface Evented {
	public void initResources();
	public void render(Graphics2D g);
	public void update(long elapsed);
}
