package evented;

import gameCharacter.GameCharacter;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class EventedWrapper<T extends EventedItem> implements Evented{

	private List<T> list = new ArrayList<T>();
	private GameCharacter character;
	
	public EventedWrapper(GameCharacter character) {
		this.character = character;
	}
	
	public GameCharacter getCharacter() {
		return character;
	}
	
	public List<T> getList() {
		return Collections.unmodifiableList(list);
	}
	
	public void render(Graphics2D g) {
		for (T item : list) item.render(g);
	}

	public void update(long elapsed) {
		for (T item : list) item.update(elapsed);
	}

}
