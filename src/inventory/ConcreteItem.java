package inventory;

import utils.JsonUtil.JSONItem;
import evented.EventedWrapper;
import app.RPGame;


/**
 * Concrete class for Items. You can subclass this one or subclass item directly
 * 
 * @author Chris Dennis
 */

public class ConcreteItem extends Item {

    private static final long serialVersionUID = -4599650031278387506L;
    private int cost;
    private boolean isForSale;
    private boolean quantifiable;
    private boolean equipped;


    public ConcreteItem (RPGame game, JSONItem item) {
        super(game, item);
        initResources();
        cost = 0;
    }


    public ConcreteItem (EventedWrapper<Item> wrapper, JSONItem item) {
        super(wrapper, item);
        initResources();
        cost = 0;
    }


    @Override
    public void setPrice (int price) {
        cost = price;
    }


    @Override
    public int getPrice () {
        return cost;
    }


    @Override
    public boolean isSellable () {
        return isForSale;
    }


    public void setSellable (boolean sell) {
        isForSale = sell;

    }


    @Override
    public void use () {
        equip();

    }


    @Override
    public void equip () {
        Inventory myWrapper = (Inventory) wrapper;
        if (myWrapper.getEquipped() != null) myWrapper.getEquipped().unequip();
        myWrapper.setEquipped(this);
        equipped = true;
    }


    @Override
    public void unequip () {
        Inventory myWrapper = (Inventory) wrapper;
        myWrapper.removeEquipped(this);
        equipped = false;
    }


    @Override
    public void drop () {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean isEquipped () {
        return equipped;
    }


    @Override
    public boolean canBeEquipped () {
        // TODO Auto-generated method stub
        return true;
    }


    @Override
    public void removeWhenUsed (int quantity) {
        if (quantifiable) remove(quantity);
    }

}
