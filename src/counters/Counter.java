package counters;

import java.awt.Graphics2D;

import evented.EventedItem;
import evented.EventedWrapper;


public abstract class Counter extends EventedItem<Counter>{
	

	private Integer count;
	protected Integer init; // Initial is used as maximum
	private boolean full = false;
	private boolean empty = false;

	public Counter(EventedWrapper<Counter> wrapper, int count) {
		super(wrapper);
		this.count = count;
		this.init = count;
	}
	
	public abstract void update(long elapsed);
	
	public abstract void render(Graphics2D g);

	public void increase() {
		if (count <= init)
			count++;
		if (count == init)
			full = true;
	}

	public void decrease() {
		if (count >= 0) {
			count--;
			full = false;
		} else
			empty = true;
	}

	public void increase(int count) {
		if (!full) {
			this.count = this.count + count;
			if (this.count >= init) {
				this.count = init;
				full = true;
			}
		}
	}

	public void decrease(int count) {
		if (!empty) {
			this.count = this.count - count;
			if (this.count <= 0) {
				this.count = 0;
				empty = true;
			}
		}
	}

	public boolean isEmpty() {
		return empty;
	}

	public boolean isFull() {
		return full;
	}

	public void reset() {
		count = init;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public Integer getInitial() {
		return init;
	}

	public void boostTotal(int statChange) {
		this.init += statChange;
	}
}
