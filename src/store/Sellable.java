package store;

public interface Sellable {
	
	public void setPrice(int price);
	public int getPrice();
	boolean isSellable();
	void setSellable(boolean sell);

}
