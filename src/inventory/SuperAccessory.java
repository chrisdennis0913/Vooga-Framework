package inventory;

import com.google.gson.JsonObject;
import app.RPGame;
import evented.EventedWrapper;


public class SuperAccessory extends Item implements Accessory, Weapon, Potion {

    /**
     * Great example for super Item
     */
    private static final long serialVersionUID = 1L;
    private int cost;
    private boolean isForSale;
    private boolean equipped;
    private int healValue;
    private int statChange;
    private int damage;
    private int relX;
    private int relY;
    private String weaponType;
    private String statCategory;


    public SuperAccessory (EventedWrapper<Item> wrapper, JsonObject item) {
        super(wrapper, item);
        initResources();
    }
    public SuperAccessory(RPGame game){
        super(game);
    }


    public SuperAccessory (RPGame game, JsonObject item) {
        super(game, item);
        initResources();
    }


    public void initResources () {
        image = game.getImage("rsc/items/bow.png");
        cost = 0;
        isForSale = true;
        equipped = false;
        healValue = 1;
        statChange = 20;
        damage = 5;
        weaponType = "bow";
        relX = 0;
        relY = 0;
    }


    @Override
    public void use () {
        this.getGame()
            .getPlayer()
            .getCharacter()
            .getCounters()
            .get("health")
            .increase(statChange);
        equip();
    }


    @Override
    public void equip () {
        Inventory myWrapper = (Inventory) wrapper;
        if (myWrapper.getEquipped() != null) myWrapper.getEquipped().unequip();
        myWrapper.setEquipped(this);
        equipped = true;
        game.getPlayer().getCharacter().getCounters().get(statCategory).boostTotal(statChange);
        game.getPlayer().getCharacter().getCounters().get(statCategory).decrease(1);
        game.getPlayer().getCharacter().getCounters().get(statCategory).increase(statChange + 1);
    }


    @Override
    public void unequip () {
        Inventory myWrapper = (Inventory) wrapper;
        myWrapper.removeEquipped(this);
        equipped = false;
        game.getPlayer().getCharacter().getCounters().get(statCategory).boostTotal(-statChange);
        game.getPlayer().getCharacter().getCounters().get(statCategory).decrease(statChange);
    }


    @Override
    public void drop () {
        Inventory myInv = (Inventory) getWrapper();
        myInv.remove(this);
        getWrapper().getCharacter()
                    .getGame()
                    .getLevel()
                    .getInventory()
                    .add(this);
        setLocation(getWrapper().getCharacter().getX(),
                    getWrapper().getCharacter().getY());
    }


    @Override
    public boolean isEquipped () {
        return equipped;
    }


    @Override
    public boolean canBeEquipped () {
        return true;
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
        int[] relPos = {relX, relY};
        return relPos;
    }


    @Override
    public void removeWhenUsed (int quantity) {
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


    @Override
    public void setSellable (boolean sell) {
    }

}
