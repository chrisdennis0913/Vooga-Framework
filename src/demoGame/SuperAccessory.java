package demoGame;

import inventory.ConcreteItem;
import inventory.Inventory;
import inventory.Item;
import app.RPGame;
import com.google.gson.JsonObject;
import evented.EventedWrapper;


public class SuperAccessory extends ConcreteItem {

    /**
     * Great example for super Item
     */
    private static final long serialVersionUID = 1L;
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

    public SuperAccessory (RPGame game, JsonObject item) {
        super(game, item);
        initResources();
    }


    public void initResources () {
        image = game.getImage("rsc/items/bow.png");
        price = 0;
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



}
