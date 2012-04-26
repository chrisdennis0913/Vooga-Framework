package store;

import inventory.Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.object.font.SystemFont;

import counters.Counter;
import evented.EventedWrapper;

public class Money extends Counter {
	
	Integer init = Integer.MAX_VALUE;
	private int startCash;
	private Inventory myInventory;

	public Money(EventedWrapper<Counter> wrapper, int count) {
		super(wrapper, count);
	}

	@Override
	public void initResources() {
		startCash = 1000;

	}

	@Override
	public void update(long elapsed) {
//		if(player buys something){
//			startCash -= item.getPrice();
//		}
//		else if (player sells something){
//			startCash += item.getPrice();
//		}
	}

	@Override
	public void render(Graphics2D g) {
		SystemFont font = new SystemFont(new Font("Arial", Font.BOLD, 20), new Color(255,0,0));
		g.setColor(new Color(0));
		g.drawRect(10, 10, 25, 25);
		g.fillRect(10, 10, 25, 25);
		font.drawText(g, getCount().toString(), SystemFont.LEFT, 12, 12, 25, 2, 0);
		
	}
	

}


