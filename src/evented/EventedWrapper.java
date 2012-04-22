package evented;

import gameCharacter.GameCharacter;

import java.awt.Graphics2D;
import java.util.HashMap;

/**
 * The evented wrapper is the connection between the item
 * like a counter or action and the character it is used on.
 * It executes common evented tasks on the items it contains
 * and can be used to retrieve internal items.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
 * 
 * @author Kirill Klimuk
 */

public class EventedWrapper<T extends EventedItem<T>> implements
		Evented {

	private HashMap<String, T> list = new HashMap<String, T>();
	private GameCharacter character;

	public EventedWrapper(GameCharacter character) {
		this.character = character;
	}

    public GameCharacter getCharacter() {
		return character;
	}
	
	public void add(String itemName, T item) {
		list.put(itemName, item);
	}
	
	public T get(String itemName) {
		itemName = itemName.toLowerCase();
		return list.get(itemName);
	}

	public void render(Graphics2D g) {
		if (!list.isEmpty())
			for (T item : list.values())
				item.render(g);
	}

	public void update(long elapsed) {
		if (!list.isEmpty())
			for (T item : list.values())
				item.update(elapsed);
	}

	public void initResources() {}

}
