package menu;

import inventory.Inventory;
import inventory.Item;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import com.golden.gamedev.object.Sprite;

import app.RPGame.Pausable;


public class InventoryMenu extends Menu {

    private Inventory inventory;
    private ArrayList<Item> optionsList = new ArrayList<Item>();
    private BufferedImage emphasisBullet;
    private boolean firstTime = true;


    public InventoryMenu (Inventory inv) {
        super(inv.getCharacter().getGame(),
              "rsc/menu/Arrow.png",
              "rsc/menu/itemMenuBackground.gif",
              "rsc/menu/BitmapFont.png");
        inventory = inv;
        numOptions = inv.getSize();
        emphasisBullet = game.getImage("rsc/menu/goldStar.gif");
    }


    public void initResources () {

    }


    public void updateInventory (Inventory inv) {
        inventory = inv;
        numOptions = inv.getSize();
    }


    public void update (long elapsedTime) {
        if (blinkTimer.action(elapsedTime)) {
            blink = !blink;
        }

        numOptions = inventory.getSize();

        switch (game.bsInput.getKeyPressed()) {
            case KeyEvent.VK_ENTER:
                if (option == 0) {
                    // Back to main game screen.
                    game.unPauseGameFor(Pausable.INV);
                }
                else {
                    Item currentItem = optionsList.get(option - 1);
                    currentItem.use();
                    currentItem.removeWhenUsed(1);
                }
                optionsList = new ArrayList<Item>();
                firstTime = true;
                break;
            case KeyEvent.VK_D:
                if (option != 0) {
                    Item currentItem = optionsList.get(option - 1);
                    currentItem.drop();
                    option = 0;
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
        font.drawString(g, "BACK TO GAME", 80, 20);
        int count = 0;
        for (Item itm : inventory) {
            font.drawString(g, itm.getName().toUpperCase(), 90, 50 + count * 30);
            count++;
            Sprite itemSprite = new Sprite(itm.getImage(), 44, 18 + count *30);
            itemSprite.render(g);
            if (itm.isEquipped()) {
                g.drawImage(emphasisBullet, 4, 17 + count * 30, null);
            }
            if (firstTime) optionsList.add(itm);

        }
        firstTime = false;

        if (!blink) {
            g.drawImage(cursor, 25, 17 + (option * 30), null);
        }
    }

}
