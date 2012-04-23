package gameCharacter;

/**
 * Interface for health/life status of game characters.
 * @author jameshong
 *
 */
public interface Attackable {

	public int getHealth();
	public void addToHealth(int delta);
	public void setAlive(boolean alive);
	public boolean isAlive();

}
