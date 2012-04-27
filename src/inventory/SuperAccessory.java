package inventory;

import java.awt.Graphics2D;
import app.RPGame;
import com.google.gson.JsonObject;
import evented.EventedWrapper;


public class SuperAccessory extends ConcreteItem {
    private static final long serialVersionUID = -2524687894009056049L;
    private int timesToHeal;


    /**
     * Great example for super Item
     */

    public SuperAccessory (EventedWrapper<Item> wrapper, JsonObject item) {
        super(wrapper, item);
        initMyResources();
    }


    public SuperAccessory (RPGame game, JsonObject item) {
        super(game, item);
        initMyResources();
    }


    public void initMyResources () {
        price = 0;
        isForSale = true;
        equipped = false;
        healValue = 4;
        statCategory = "health";
        statChange = 20;
        damage = 5;
        weaponType = "bow";
        relX = 0;
        relY = 0;
        timesToHeal = 5;
    }


    public void render (Graphics2D g) {
        super.render(g);
        if (!isEquipped()) return;
        setActive(true);
        setLayer(10);
        setLocation(game.getPlayer().getCharacter().getX() + relX,
                             game.getPlayer().getCharacter().getY() + relY);
    }


    @Override
    public void use () {
        if (timesToHeal > 0) {
            game.getPlayer()
                .getCharacter()
                .getCounters()
                .get(statCategory)
                .increase();
            timesToHeal--;
        }
        equip();
    }


    @Override
    public void equip () {
        if (isEquipped()) return;
        Inventory myWrapper = (Inventory) wrapper;
        if (myWrapper.getEquipped() != null) myWrapper.getEquipped().unequip();
        myWrapper.setEquipped(this);
        equipped = true;
        game.getPlayer()
            .getCharacter()
            .getCounters()
            .get(statCategory)
            .boostTotal(statChange);
        game.getPlayer()
            .getCharacter()
            .getCounters()
            .get(statCategory)
            .decrease(1);
        game.getPlayer()
            .getCharacter()
            .getCounters()
            .get(statCategory)
            .increase(statChange + 1);
    }


    @Override
    public void unequip () {
        Inventory myWrapper = (Inventory) wrapper;
        myWrapper.removeEquipped(this);
        equipped = false;
        game.getPlayer()
            .getCharacter()
            .getCounters()
            .get(statCategory)
            .boostTotal(-statChange);
    }

}
