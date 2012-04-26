package state;


public interface State 
{	
	public void update(long elapsedTime);
	
	public String getStatus();
}
