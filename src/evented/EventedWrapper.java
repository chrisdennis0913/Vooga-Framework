package evented;

import gameCharacter.GameCharacter;
import inventory.Item;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;


/**
 * The evented wrapper is the connection between the item like a counter or
 * action and the character it is used on. It executes common evented tasks on
 * the items it contains and can be used to retrieve internal items.
 * 
 * @author Kirill Klimuk
 */

public class EventedWrapper<T extends Evented>
    implements Evented, Iterable<T> {

    protected HashMap<String, T> list = new HashMap<String, T>();
    private GameCharacter character;


    public EventedWrapper (GameCharacter character) {
        this.character = character;
    }


    public GameCharacter getCharacter () {
        return character;
    }


    /* storage methods */

    public void add (String itemName, T item) {
        list.put(itemName, item);
    }


    public void remove (String itemName) {
        if (contains(itemName)) list.remove(itemName);
    }


    public T get (String itemName) {
        itemName = itemName.toLowerCase();
        return list.get(itemName);
    }


    public boolean contains (String itemName) {
        return list.containsKey(itemName);
    }


    public boolean containsType (String itemType) { // fix this
        for (T item : this) {
            if (((Item) item).getCategory().equalsIgnoreCase(itemType)) {
                return true;
            }
        }
        return false;
    }
    
    public int getSize(){
        return list.size();
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }


    public Iterator<T> iterator () {
        return list.values().iterator();
    }


    /* evented methods */

    public void render (Graphics2D g) {
        if (!list.isEmpty()) for (T item : list.values())
            item.render(g);
    }


    public void update (long elapsed) {
        if (!list.isEmpty()) for (T item : list.values())
            item.update(elapsed);
    }


    public void initResources () {}

}
