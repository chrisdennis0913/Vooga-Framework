package inventory;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;

import store.Sellable;
import utils.JsonUtil.JSONItem;
import utils.Location;
import app.RPGame;

import com.golden.gamedev.util.ImageUtil;

import evented.EventedItem;
import evented.EventedWrapper;

/**
 * Can subclass to create other instance variables such as weight, damage, price
 * ItemNames should be lowerCase
 * 
 * @author chrisdennis0913
 */

public abstract class Item extends EventedItem<Item> implements Sellable,
		EquipItemInterface {

	private static final long serialVersionUID = 6760280693009697161L;
	private final JSONItem item;

	private String myName;
	protected String category;
	private BufferedImage image;
	protected int quantity;

	public Item(EventedWrapper<Item> wrapper, JSONItem item) {
		super(wrapper);
		this.item = item;
	}

	public void initResources() {
		Location loc = new Location(item.location);

		if (getWrapper() != null)
			image = getWrapper().getCharacter().getGame().getImage(item.image);
		else
			try {
				image = ImageUtil.getImage(new File(item.image).toURI().toURL(), new Color(255));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		setImage(image);
		setLocation(loc.getX(), loc.getY());
		quantity = item.quantity;
		myName = item.name;
	}

	public String getName() {
		return myName;
	}

	public String getMessage() {
		return "Picked up " + myName + ".";
	}

	public String getCategory() {
		return category;
	}

	public void add(int quant) {
		quantity += quant;
	}

	public void remove(int quant) {
		quantity -= quant;
		if (quantity <= 0) {
			wrapper.remove(this.myName);
		}
	}

	public void removeAll() {
		quantity = 0;
		wrapper.remove(this.myName);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quanity) {
		this.quantity = quanity;
	}

	/**
	 * @return string representation of item
	 */
	public String toString() {
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
	public int compareTo(Item it) {
		if (category != it.getCategory())
			return category.compareTo(it.getCategory());
		if (myName != it.getName())
			return myName.compareTo(it.getName());
		return 0;
	}

	public boolean equals(Object o) {
		Item it = (Item) o;

		return compareTo(it) == 0;
	}

	public abstract void removeWhenUsed(int quantity);

	public abstract boolean isThisKindOfItem(String toParse);

	public abstract Item parseItem(RPGame game2, String toParse);

	public void changeWrapper(EventedWrapper<Item> wrapper) {
		this.wrapper = wrapper;
	}

	public String parseName(String toParse) {
		String[] parseArray = toParse.split(",");
		return parseArray[0].trim();
	}

	public String parseGifName(String toParse) {
		String[] parseArray = toParse.split(",");
		return parseArray[1].trim();
	}

	public String parseCategory(String toParse) {
		String[] parseArray = toParse.split(",");
		return parseArray[2].trim();
	}
}
