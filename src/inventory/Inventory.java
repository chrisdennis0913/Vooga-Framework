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
    private ArrayList<Accessory> accessoryList;


    public Inventory (GameCharacter character) {
        super(character);
        accessoryList = new ArrayList<Accessory>();
        equippedItem = null;
    }


    public void add (Item itm) {
        if (itm.getCategory().equalsIgnoreCase("accessory")) {
            Accessory acc = (Accessory) itm;
            acc.equip();
        }
        if (!contains(itm)) add(itm.myName, itm);
    }


    public void add (Item itm, int quantity) {
        if (equippedItem==null & itm.canBeEquipped())
            equippedItem=itm;
        if (itm.getCategory().equalsIgnoreCase("accessory")) {
            Accessory acc = (Accessory) itm;
            acc.equip();
        }
        add(itm);
        itm.add(quantity);
    }


    public void remove (Item itm) {
        if (itm.getCategory().equalsIgnoreCase("accessory")) {
            Accessory acc = (Accessory) itm;
            acc.unequip();
        }
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


    @Override
    public void initResources () {
        list = new HashMap<String, Item>();
    }


    @Override
    public void render (Graphics2D g) {
        // TODO Auto-generated method stub
        // inventory menu?

    }


    @Override
    public void update (long elapsed) {
        // TODO Auto-generated method stub

    }
}
