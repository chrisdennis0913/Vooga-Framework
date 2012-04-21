package inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Inventory implements Iterable<Item>{
    protected Map<Item, Integer> myItemMap;

    public Inventory() {
        myItemMap = new TreeMap<Item, Integer>();
    }

    public void add(Item itm) {
        if (!myItemMap.containsKey(itm))
            myItemMap.put(itm, 1);
        // implicitly calls compareTo
        // sorts items by category alphabetically, then by name, then by price
        // done automatically within the TreeMap
    }

    public void add(Item itm, int quantity) {
        int quant = 0;
        if (myItemMap.containsKey(itm)) {
            quant = myItemMap.get(itm);
        }
        myItemMap.put(itm, quant + quantity);
    }

    public void remove(Item itm) {
        if (myItemMap.containsKey(itm))
            myItemMap.remove(itm);
        else
            System.out.println("Inventory does not contain " + itm.getName()
                    + ".");
    }

    public void remove(Item itm, int quantity) {
        int quant = 0;
        if (myItemMap.containsKey(itm)) {
            quant = myItemMap.get(itm);
        }
        quant -= quantity;
        if (quant < 0) {
            System.out.println("That is " + quant * -1 + " too many of "
                    + itm.getName() + ".");
            return;
        }
          if (quant == 0) {
                System.out.println("That is the last of the"
                        + itm.getName() + "s.");
                myItemMap.remove(itm);
                return;
            }
        myItemMap.put(itm, quant);
        System.out.println("There are now " + quant+" left of the "
                + itm.getName() + "s.");
    }

    public boolean contains(Item itm) {
        return myItemMap.containsKey(itm);
    }
    
    public Integer getCount(Item itm)
    {
        return myItemMap.get(itm);
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyIterator(this);
    }

    private static class MyIterator implements Iterator<Item> {

        int index;
        private ArrayList<Item> data;

        public MyIterator(Inventory source) {
            index = 0;
            data = new ArrayList<Item>();
            data.addAll(source.myItemMap.keySet());
        }

        public boolean hasNext() {
            if (index >= data.size())
                return false;
            return true;
        }

        public Item next() {
            Item output = data.get(index);
            index++;
            return output;
        }

        public void remove() {
            //
        }

    }
}
