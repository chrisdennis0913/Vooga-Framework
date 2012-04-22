package player;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

public class Player extends CharacterDecorator {

	public Player(GameCharacter character) {
		super(character);
	}

}
