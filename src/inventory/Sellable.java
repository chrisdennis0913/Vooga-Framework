package inventory;

public interface Sellable {
	
	public void setPrice();
	public int getPrice();
	public void adjustPrice();
	boolean isSellable();

}
