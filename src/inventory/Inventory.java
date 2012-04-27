package inventory;

import java.awt.Graphics2D;
import java.util.HashMap;
import app.RPGame.Pausable;
import menu.InventoryMenu;
import evented.EventedWrapper;
import gameCharacter.GameCharacter;


/**
 * Keeps track of the variety of Items kept and equipped by each game character
 * 
 * @author chrisdennis0913
 */

public class Inventory extends EventedWrapper<Item> implements Iterable<Item> {
    private Item equippedItem;
    private InventoryMenu invMenu;


    public Inventory (GameCharacter character) {
        super(character);
        initResources();
    }


    public void add (Item itm) {
        if (!contains(itm)) add(itm.getName(), itm);
        if (equippedItem == null & itm.canBeEquipped()) {
            itm.equip();
        }
        itm.add(1);
    }


    public void add (Item itm, int quantity) {
        add(itm);
        itm.add(quantity);
    }


    public void remove (Item itm) {
        if (equippedItem == itm) {
            equippedItem = null;
        }
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


    public Item getEquipped () {
        return equippedItem;
    }


    public void setEquipped (Item itm) {
        if (contains(itm) & itm.canBeEquipped()) {
            equippedItem = itm;
        }
    }


    public void removeEquipped () {
        equippedItem = null;
    }


    public void removeEquipped (Item itm) {
        if (equippedItem == itm) {
            equippedItem = null;
        }
    }


    public boolean isEquipped (Item itm) {
        return equippedItem == itm;
    }


    public boolean isEquipped (String itmName) {
        if (equippedItem == null) return false;
        String name = equippedItem.getName().toLowerCase();
        return name.contains(itmName.toLowerCase());
    }


    @Override
    public void initResources () {
        list = new HashMap<String, Item>();
        invMenu = new InventoryMenu(this);
        equippedItem = null;
    }


    @Override
    public void render (Graphics2D g) {
        for (Item itm : this.list.values()) {
            itm.render(g);
        }
        if (character.getGame().isPausedFor(Pausable.INV)) invMenu.render(g);
    }


    @Override
    public void update (long elapsed) {
        if (character.getGame().keyPressed(java.awt.event.KeyEvent.VK_I)) {
            character.getGame().pauseGameFor(Pausable.INV);
            invMenu.updateInventory(this);
        }

        if (character.getGame().isPausedFor(Pausable.INV)) invMenu.update(elapsed);
    }
}
