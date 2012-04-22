package ai;

import gameCharacter.AttackController;
import gameCharacter.AutomatedCharacter;
import app.RPGame;
import attacks.AbstractAttack;


public abstract class AbstractAttackAI extends AttackController{

	protected RPGame game;
	protected AutomatedCharacter character;

	public AbstractAttackAI(RPGame game, AutomatedCharacter character){
		super(game,character);
	}

	public AbstractAttack chooseAttack(){
		return pickBestSpontaneousAttack();
	}
	
	public abstract void update(long elapsedTime);
	public abstract boolean shouldAttack();
	public abstract AbstractAttack pickBestSpontaneousAttack();
	public abstract AbstractAttack pickBestReactiveAttack();
	public abstract void onCollision();
	
}
