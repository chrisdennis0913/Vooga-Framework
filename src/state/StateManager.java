package state;

import gameCharacter.CharacterDecorator;

import java.util.ArrayList;

public class StateManager{

	ArrayList<State> states = new ArrayList<State>();
	State active;
	
	public void addState(State s){
		states.add(s);
	}
	
	public void setState(State s){
		active = s;
	}
	
	public State getActiveState(){
		return active;
	}
	
	public void update(long elapsedTime, CharacterDecorator cD){	
		for(State s: states){
			if(s.wantsActive()){
				setState(s);
				break;
			}
		}
		if(active == null){
			active = states.get(0);
		}
		active.update(elapsedTime, cD);
		System.err.println(active.getStatus());
	}
}
