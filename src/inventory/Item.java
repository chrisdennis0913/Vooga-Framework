
package inventory;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;

import player.Player;
import store.Sellable;
import utils.Location;
import app.RPGame;
import com.golden.gamedev.util.ImageUtil;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import evented.EventedItem;
import evented.EventedWrapper;
import gameCharacter.GameCharacter;


public abstract class Item extends EventedItem<Item>
    implements Sellable, EquipItemInterface //Potion, Accessory, Weapon
{

    /**
* Can subclass to create other instance variables such as weight, damage,
* price ItemNames should be lowerCase
*
* @author chrisdennis0913
*/
    private static final long serialVersionUID = 6760280693009697161L;
    protected static RPGame game;
    protected BufferedImage image;
    protected Sprite mySprite;
    protected SpriteGroup myGroup;
    protected String myName;
    protected String category;
    protected int quantity = 1; // make sure this gets instantiated properly
    private final JsonObject item;
    
    // Can subclass to create other instance variables
    // such as weight
    public Item (RPGame game, JsonObject item) {
        super(game);
        this.item = item;
    }


    public Item (EventedWrapper<Item> wrapper, JsonObject item) {
        super(wrapper);
        this.item = item;
    }


    public void initResources () {
    	JsonArray jLocation = item.getAsJsonArray("location");			
        Location loc = new Location(new int[]{jLocation.get(0).getAsInt(), jLocation.get(1).getAsInt()});

        if (getWrapper() != null) image =
            getWrapper().getCharacter().getGame().getImage(item.get("image").getAsString());
        else try {
            image =
                ImageUtil.getImage(new File(item.get("image").getAsString()).toURI().toURL(),
                                   new Color(255));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        setImage(image);
        setLocation(loc.getX(), loc.getY());
        quantity = item.get("quantity").getAsInt();
        myName = item.get("name").getAsString();
    }


    public void add (int[] loc, int layer) {
        mySprite = new Sprite(image, loc[0], loc[1]);
        mySprite.setLayer(layer);
        myGroup.add(mySprite);
    }


// public void generate () {
// game.getField().addGroup(myGroup);
// setCollision();
// }
//
//
// public void setCollision () {
// ItemCollision collision =
// new ItemCollision(game, myName, this, mySprite);
// game.getField().addCollisionGroup(game.getPlayer().getGroup(),
// getGroup(),
// collision);
// }

    public SpriteGroup getGroup () {
        return myGroup;
    }


    public String getName () {
        return myName;
    }
    

    public BufferedImage getImage(){
        return image;
    }


    public String getMessage () {
        return "Picked up " + myName + ".";
    }


    public String getCategory () {
        return category;
    }


    public void add (int quant) {
        quantity += quant;
    }


    public void remove (int quant) {
        quantity -= quant;
        if (quantity <= 0) {
            delete();
        }
    }


    public void removeAll () {
        quantity = 0;
        delete();
    }


    public int getQuantity () {
        return quantity;
    }


    /**
* @return string representation of item
*/
    public String toString () {
        return "[" + getName() + ": " + getQuantity() + "]";
    }


    public boolean equals (Object o) {
        Item it = (Item) o;

        return compareTo(it) == 0;
    }

    private void delete () {
        if (hasWrapper()) getWrapper().remove(myName);
        else {
            getGame().getLevel().getInventory().remove(myName);
            setActive(false);
        }
    }


    public void setQuantity (int quanity) {
        this.quantity = quanity;
    }


    /**
* Return value that meets criteria of compareTo conventions.
*
* @param if is the Item to which this is compared Sort by category, then by
* name, then by price Higher price is greater
* @return appropriate value less than zero, zero, or greater than zero
*/
    public int compareTo (Item it) {
        if (myName != it.getName()) return myName.compareTo(it.getName());
        return 0;
    }


    public abstract void removeWhenUsed (int quantity);


    public abstract boolean isThisKindOfItem (String toParse);


    public abstract Item parseItem (RPGame game2, String toParse);


    public String parseName (String toParse) {
        String[] parseArray = toParse.split(",");
        return parseArray[0].trim();
    }


    public String parseGifName (String toParse) {
        String[] parseArray = toParse.split(",");
        return parseArray[1].trim();
    }


    public String parseCategory (String toParse) {
        String[] parseArray = toParse.split(",");
        return parseArray[2].trim();
    }
}
