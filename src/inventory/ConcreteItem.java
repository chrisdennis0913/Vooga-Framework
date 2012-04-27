package inventory;

import com.google.gson.JsonObject;
import evented.EventedWrapper;
import app.RPGame;


/**
 * Concrete class for Items. You can subclass this one or subclass the abstract
 * item directly. Most usable methods and instance variables are already in use
 * and implemented
 * 
 * @author Chris Dennis
 */

public class ConcreteItem extends Item {

    private static final long serialVersionUID = -4599650031278387506L;
    protected boolean isForSale;
    private boolean quantifiable;
    private boolean equippable = true;
    protected boolean equipped;
    protected int healValue;
    protected int statChange;
    protected int damage;
    protected int relX;
    protected int relY;
    protected String weaponType;
    protected String statCategory;
    private boolean canDrop = true;


    public ConcreteItem (RPGame game, JsonObject item) {
        super(game, item);
        initResources();
    }


    public ConcreteItem (EventedWrapper<Item> wrapper, JsonObject item) {
        super(wrapper, item);
        initResources();
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
        if (!canBeEquipped()) return;
        Inventory myWrapper = (Inventory) wrapper;
        if (myWrapper.getEquipped() != null) {
            myWrapper.getEquipped().unequip();
        }
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
    public void removeWhenUsed (int quantity) {
        if (quantifiable) remove(quantity);
    }


    @Override
    public void drop () {
        if (!canDrop) return;
        Inventory myInv = (Inventory) getWrapper();
        myInv.remove(this);
        getWrapper().getCharacter()
                    .getGame()
                    .getLevel()
                    .getInventory()
                    .add(this);
        setLocation(getWrapper().getCharacter().getX(),
                    getWrapper().getCharacter().getY());
        getWrapper().getCharacter()
                    .getGame()
                    .getField()
                    .getGroup("items")
                    .add(this);
        setActive(true);
    }


    public void setDroppable (boolean drop) {
        canDrop = drop;
    }


    public boolean isDroppable () {
        return canDrop;
    }


    @Override
    public boolean isEquipped () {
        return equipped;
    }


    @Override
    public boolean canBeEquipped () {
        return equippable;
    }


    @Override
    public void setEquippable (boolean equip) {
        equippable = equip;
    }


    @Override
    public void setPotionValue (int value) {
        healValue = value;
    }


    @Override
    public int getPotionValue () {
        return healValue;
    }


    @Override
    public int getDamage () {
        return damage;
    }


    @Override
    public void setDamage (int damageValue) {
        damage = damageValue;
    }


    @Override
    public String getWeaponType () {
        return weaponType;
    }


    @Override
    public void setWeaponType (String type) {
        weaponType = type;
    }


    @Override
    public void setStatCategory (String statistic) {
        statCategory = statistic;

    }


    @Override
    public void setStatChange (int value) {
        statChange = value;
    }


    @Override
    public String getStatCategory () {
        return statCategory;
    }


    @Override
    public int getStatChange () {
        return statChange;
    }


    @Override
    public void setRelPosition (int x, int y) {
        relX = x;
        relY = y;
    }


    @Override
    public int[] getRelPosition () {
        int[] relPos = { relX, relY };
        return relPos;
    }


    @Override
    public void setPrice (int price) {
        this.price = price;
    }


    @Override
    public int getPrice () {
        return price;
    }
}
