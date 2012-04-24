package menu;

import inventory.Inventory;
import inventory.Item;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class InventoryMenu extends Menu {

    private Inventory inventory;
    private ArrayList<Item> optionsList = new ArrayList<Item>();
    private BufferedImage emphasisBullet;
    private boolean firstTime = true;


    public InventoryMenu (Inventory inv) {
        super(inv.getCharacter().getGame(),
              "Arrow.png",
              "resources/items/itemMenuBackground.gif",
              "BitmapFont.png");
        inventory = inv;
        numOptions = inv.getSize();
        emphasisBullet = game.getImage("resources/items/goldStar.gif");
    }


    public void initResources () {

    }


    public void update (long elapsedTime) {
        if (blinkTimer.action(elapsedTime)) {
            blink = !blink;
        }

        switch (game.bsInput.getKeyPressed()) {
            case KeyEvent.VK_ENTER:
                if (option == 0) {
                    // Back to main game screen.
                    game.unPauseGameForInventory();
                }
                else {
                    Item currentItem = optionsList.get(option - 1);
                    currentItem.use();
                    if (currentItem.getCategory()
                                   .equalsIgnoreCase("healthpotion")) inventory.remove(currentItem,
                                                                                1);
                }
                optionsList = new ArrayList<Item>();
                firstTime = true;
                break;

            case KeyEvent.VK_UP:
                option--;
                if (option < 0) option = numOptions;
                break;

            case KeyEvent.VK_DOWN:
                option++;
                if (option > numOptions) option = 0;
                break;

            case KeyEvent.VK_ESCAPE:
                game.finish();
                break;
        }
    }


    public void render (Graphics2D g) {
        g.drawImage(menuBackground, 0, 0, null);
        font.drawString(g, "BACK TO GAME", 30, 20);
        int count = 0;
        for (Item itm : inventory) {
            font.drawString(g, itm.getName().toUpperCase(), 30, 40 + count * 20);
            count++;
            if (itm.isEquipped()) g.drawImage(emphasisBullet, 4, 17 + count * 20, null);
            if (firstTime) optionsList.add(itm);
        }
        firstTime = false;

        if (!blink) {
            g.drawImage(cursor, 14, 17 + (option * 20), null);
        }
    }

}