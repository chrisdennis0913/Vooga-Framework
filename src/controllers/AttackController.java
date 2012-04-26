package controllers;

import evented.EventedItem;
import gameCharacter.GameCharacter;
import app.RPGame;
import attacks.AbstractAttack;

public abstract class AttackController extends EventedItem<Controller> implements Controller{

	private static final long serialVersionUID = 1L;

	protected RPGame game;
	protected GameCharacter character;
	
	public AttackController(RPGame game, GameCharacter character){
		this.game = game;
		this.character = character;
	}

	public abstract AbstractAttack chooseAttack();
	public abstract AbstractAttack getCurrentAttack();
	public abstract boolean isAttacking();
}
