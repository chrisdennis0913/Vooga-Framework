package level;

import inventory.Item;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import app.RPGame;
import evented.Evented;


/**
 * Keeps track of the variety of Items kept and equipped by each game level
 * Different from regular inventory. Takes game in constructor instead of the
 * game character and also does not extend the eventedWrapper
 * 
 * @author chrisdennis0913
 */
public class LevelInventory<T extends Item> implements Evented, Iterable<T> {
    protected HashMap<String, T> list = new HashMap<String, T>();
    private RPGame game;


    public LevelInventory (RPGame game) {
        this.game = game;
    }


    public RPGame getGame () {
        return game;
    }


    public void add (String itemName, T item) {
        list.put(itemName, item);
    }


    @SuppressWarnings("unchecked")
    public void add (Item itm) {
        if (!contains(itm)) add(itm.getName(), (T) itm);
    }


    public void add (Item itm, int quantity) {
        add(itm);
        itm.add(quantity);
    }


    public void remove (String itemName) {
        if (contains(itemName)) list.remove(itemName);
    }


    public void remove (Item itm) {
        remove(itm.getName());
    }


    public void remove (Item itm, int quantity) {
        itm.remove(quantity);
    }


    public boolean contains (Item itm) {
        return contains(itm.getName());
    }


    public Integer getCount (Item itm) {
        return itm.getQuantity();
    }


    public T get (String itemName) {
        itemName = itemName.toLowerCase();
        return list.get(itemName);
    }


    public boolean contains (String itemName) {
        return list.containsKey(itemName);
    }


    public int getSize () {
        return list.size();
    }


    public boolean isEmpty () {
        return list.isEmpty();
    }


    public Iterator<T> iterator () {
        return list.values().iterator();
    }


    public void initResources () {}


    public void render (Graphics2D g) {
        if (!list.isEmpty()) for (Item itm : list.values()) {
            itm.render(g);
        }
    }


    public void update (long elapsed) {
        if (!list.isEmpty()) for (Item itm : list.values()) {
            itm.update(elapsed);
        }
    }

}
