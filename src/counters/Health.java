package counters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import utils.Location;
import utils.Meter;
import evented.EventedWrapper;

public class Health extends Counter {

	private static final long serialVersionUID = 1L;
	private Meter meter;

	public Health(EventedWrapper<Counter> wrapper, int count) {
		super(wrapper, count);
		initResources();
	}

	public void initResources() {
		meter = new Meter(this);
	}

	public void update(long elapsed) {
	}

	public void render(Graphics2D g) {
		drawMeter(g);
	}

	public void drawMeter(Graphics2D g) {
		int bgWidth = (int) getWrapper().getCharacter().getGame().getLevel()
				.getClip().getHeight() / 2;
		int bgHeight = 15;
		
		int status = (int) ((double) getCount()/getInitial()*100);
		
		Color color;
		if (status > 66)
			color = new Color(0, 255, 0);
		else if (status > 33) 
			color = new Color(255, 255, 0);
		else
			color = new Color(255, 0, 0);
		
		meter.show(g, new Location(10, 10), new Dimension(bgWidth, bgHeight), color);
		
	}

}
