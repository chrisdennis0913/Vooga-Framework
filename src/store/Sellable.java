package store;

/**
 * The interface allowing items to be bought and sold
 * 
 * @author zahavaalston
 *
 */
public interface Sellable {
	
	public void setPrice(int price);
	public int getPrice();
	boolean isSellable();
	void setSellable(boolean sell);

}
