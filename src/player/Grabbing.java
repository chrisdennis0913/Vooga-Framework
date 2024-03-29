package player;

import inventory.Inventory;
import inventory.Item;
import utils.JsonUtil;
import utils.KeyHandle;
import actions.Action;
import actions.ActionDecorator;

import com.golden.gamedev.object.Timer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Grabbing extends ActionDecorator {

    private static final long serialVersionUID = 1L;

    private static final int GRAB_BASIC = 20;

    private KeyHandle keys;
    private Timer timer = new Timer(200);
    private Item item;


    public Grabbing (Action action) {
        super(action);
        initResources();
    }


    public void initResources () {
             
        keys = new KeyHandle(getWrapper().getCharacter().getGame());

		JsonObject grabbing = getJsonObject();
		JsonArray jGrabbingKeys = grabbing.getAsJsonArray("keys");
		int[] grabbingKeys = JsonUtil.JsonArrayToIntArray(jGrabbingKeys);
		if (grabbingKeys == null)
			new RuntimeException("Grabbing keys undefined");

		keys.add(GRAB_BASIC, grabbingKeys);
    }


    public Item getItem () {
        return item;
    }


    public void unsetItem () {
        this.item = null;
    }


    public void setItem (Item item) {
        this.item = item;
    }


    public void update (long elapsed) {
        super.update(elapsed);

        if (isEnabled()) {
            int status = keys.checkKeys();

            if (status != -1) {
                Inventory playerInventory = getWrapper().getCharacter().getInventory();
                getItem().setWrapper(playerInventory);
                playerInventory.add(getItem());
                getWrapper().getCharacter()
                            .getGame()
                            .getLevel()
                            .getInventory()
                            .remove(getItem());
                
                getItem().setActive(false);
                getItem().setLocation(-100, -100);
                setEnabled(false, true);
            }
            reactToTimer(elapsed);
        }
    }


    private void reactToTimer (long elapsed) {
        if (timer.action(elapsed)) setEnabled(false, true);
    }

}
