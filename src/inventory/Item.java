package inventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import store.Sellable;
import app.RPGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import evented.EventedItem;
import evented.EventedWrapper;


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


    public Item (EventedWrapper<Item> wrapper) {
        super(wrapper);
    }


    // Can subclass to create other instance variables
    // such as weight
    protected Item () {}


    public Item (RPGame game2, String name, String gifName, String categ) {
        Item.game = game2;
        this.myName = name;
        myGroup = new SpriteGroup(myName);
        this.image = game2.getImage("resources/items/" + gifName + ".gif");
        category = categ;
    }


    public Item (RPGame game2, String name, String gifName) {
        Item.game = game2;
        this.myName = name;
        category = "Item";
        myGroup = new SpriteGroup(myName);
        System.out.println("Item's GifName is: " + gifName);
        this.image = game2.getImage("resources/items/" + gifName + ".gif");
    }


    public void add (int[] loc, int layer) {
        mySprite = new Sprite(image, loc[0], loc[1]);
        mySprite.setLayer(layer);
        myGroup.add(mySprite);
    }


//    public void generate () {
//        game.getField().addGroup(myGroup);
//        setCollision();
//    }
//
//
//    public void setCollision () {
//        ItemCollision collision =
//            new ItemCollision(game, myName, this, mySprite);
//        game.getField().addCollisionGroup(game.getPlayer().getGroup(),
//                                          getGroup(),
//                                          collision);
//    }

    public SpriteGroup getGroup () {
        return myGroup;
    }


    public String getName () {
        return myName;
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
            wrapper.remove(this.myName);
        }
    }


    public void removeAll () {
        quantity = 0;
        wrapper.remove(this.myName);
    }


    public int getQuantity () {
        return quantity;
    }


    /**
     * @return string representation of item
     */
    public String toString () {
        StringBuffer result = new StringBuffer();
        result.append("(");
        result.append(myName + " ");
        result.append("is a " + category + ".");
        result.append(")");

        return result.toString();
    }


    /**
     * Return value that meets criteria of compareTo conventions.
     * 
     * @param if is the Item to which this is compared Sort by category, then by
     *        name, then by price Higher price is greater
     * @return appropriate value less than zero, zero, or greater than zero
     */
    public int compareTo (Item it) {
        if (category != it.getCategory()) return category.compareTo(it.getCategory());
        if (myName != it.getName()) return myName.compareTo(it.getName());
        return 0;
    }


    public boolean equals (Object o) {
        Item it = (Item) o;

        return compareTo(it) == 0;
    }


    public abstract void removeWhenUsed (int quantity);


    public abstract boolean isThisKindOfItem (String toParse);


    public abstract Item parseItem (RPGame game2, String toParse);


    public void render (Graphics2D g) {}


    public void changeWrapper (EventedWrapper<Item> wrappr) {
        wrapper = wrappr;
    }


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
