package inventory;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import evented.EventedWrapper;
import gameCharacter.GameCharacter;


/**
 * Keeps track of quantity of Items Given to every game character
 * 
 * @author chrisdennis0913
 */

public class Inventory extends EventedWrapper<Item> implements Iterable<Item> {
//  private InventoryMenu menu;
    private Item equippedItem;


    public Inventory (GameCharacter character) {
        super(character);
        equippedItem = null;
    }


    public void add (Item itm) {
        if (!contains(itm)) add(itm.myName, itm);
    }


    public void add (Item itm, int quantity) {
        if (equippedItem == null & itm.canBeEquipped()) equippedItem = itm;
        add(itm);
        itm.add(quantity);
    }


    public void remove (Item itm) {
        if (equippedItem == itm) {
            equippedItem = null;
        }
        remove(itm.myName);
        itm.removeAll();
    }


    public void remove (Item itm, int quantity) {
        itm.remove(quantity);
    }


    public boolean contains (Item itm) {
        return contains(itm.myName);
    }


    public Integer getCount (Item itm) {
        return itm.getQuantity();
    }


    public Item getEquipped () {
        return equippedItem;
    }


    public void setEquipped (Item itm) {
        if (contains(itm)) {
            equippedItem = itm;
        }
    }
    public boolean isEquipped(Item itm){
        return equippedItem == itm;
    }

    @Override
    public void initResources () {
        list = new HashMap<String, Item>();
//        menu = new InventoryMenu(this);
    }


    @Override
    public void render (Graphics2D g) {
        for (Item itm : this.list.values()) {
            itm.render(g);
        }
//         inventory menu?
//        menu.render(g);
    }


    @Override
    public void update (long elapsed) {
//        menu.update(elapsed);
    }
}
