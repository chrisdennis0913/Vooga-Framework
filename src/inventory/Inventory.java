package inventory;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import evented.EventedWrapper;
import gameCharacter.GameCharacter;


/**
 * Keeps track of quantity of Items
 * Given to every game character
 * 
 * @author chrisdennis0913
 */
public class Inventory extends EventedWrapper<Item> implements Iterable<Item> {
    protected Map<Item, Integer> myItemMap;


    public Inventory (GameCharacter character) {
        super(character);
    }


    public void add (Item itm) {
        if (!myItemMap.containsKey(itm)) myItemMap.put(itm, 1);
        // implicitly calls compareTo
        // sorts items by category alphabetically, then by name, then by price
        // done automatically within the TreeMap
    }


    public void add (Item itm, int quantity) {
        int quant = 0;
        if (myItemMap.containsKey(itm)) {
            quant = myItemMap.get(itm);
        }
        myItemMap.put(itm, quant + quantity);
    }


    public void remove (Item itm) {
        if (myItemMap.containsKey(itm)) myItemMap.remove(itm);
        else System.out.println("Inventory does not contain " + itm.getName() +
                                ".");
    }


    public void remove (Item itm, int quantity) {
        int quant = 0;
        if (myItemMap.containsKey(itm)) {
            quant = myItemMap.get(itm);
        }
        quant -= quantity;
        if (quant < 0) {
            System.out.println("That is " + quant * -1 + " too many of " +
                               itm.getName() + ".");
            return;
        }
        if (quant == 0) {
            System.out.println("That is the last of the" + itm.getName() + "s.");
            myItemMap.remove(itm);
            return;
        }
        myItemMap.put(itm, quant);
        System.out.println("There are now " + quant + " left of the " +
                           itm.getName() + "s.");
    }


    public boolean contains (Item itm) {
        return myItemMap.containsKey(itm);
    }


    public boolean contains (String itmName) {
        for (Item itm : myItemMap.keySet()) {
            if (itm.getName().equalsIgnoreCase(itmName)) return true;
        }
        return false;
    }


    public Integer getCount (Item itm) {
        return myItemMap.get(itm);
    }


    @Override
    public Iterator<Item> iterator () {
        return myItemMap.keySet().iterator();
    }

    @Override
    public void initResources () {
        myItemMap = new TreeMap<Item, Integer>();
    }


    @Override
    public void render (Graphics2D g) {
        // TODO Auto-generated method stub

    }


    @Override
    public void update (long elapsed) {
        // TODO Auto-generated method stub

    }
}
