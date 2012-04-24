package store;

import java.awt.Graphics2D;

import counters.Counter;
import evented.EventedWrapper;

public class Money extends Counter {
	
	Integer init = Integer.MAX_VALUE;
	private int startCash;

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
//			decrease(item.getPrice());
//		}
//		else if (player sells something){
//			increase(item.getPrice());
//		}
	}

	@Override
	public void render(Graphics2D g) {
		
		
	}
	

}
