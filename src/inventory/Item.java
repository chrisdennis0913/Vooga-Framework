package inventory;

import java.awt.image.BufferedImage;
import app.RPGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import evented.EventedItem;
import evented.EventedWrapper;

/**
 * Can subclass to create other instance variables
 * such as weight, damage, price
 * @author chrisdennis0913
 *
 */
public abstract class Item extends EventedItem<Item> implements EquipItemInterface{

    protected static RPGame game;
    protected String myName;
    protected String category;
    protected SpriteGroup myGroup;
    protected BufferedImage image;
    protected Sprite mySprite;
    
    public Item(EventedWrapper<Item> wrapper){
        super(wrapper);
    }

    // Can subclass to create other instance variables
    // such as weight
    protected Item () {
        super();
    }


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
        System.out.println(gifName);
        this.image = game2.getImage("resources/items/" + gifName + ".gif");
    }


    public void add (int[] loc, int layer) {
        mySprite = new Sprite(image, loc[0], loc[1]);
        mySprite.setLayer(layer);
        myGroup.add(mySprite);
    }


    public void generate () {
        game.getField().addGroup(myGroup);
        setCollision();
    }


    public void setCollision () {
        ItemCollision collision =
            new ItemCollision(game, myName, this, mySprite);
        game.getField().addCollisionGroup(game.getPlayer().getGroup(),
                                          getGroup(),
                                          collision);
    }


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
