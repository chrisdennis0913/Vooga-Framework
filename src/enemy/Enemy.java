package enemy;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

import java.util.HashMap;

import counters.Health;

import ai.AbstractPathFindingAI;
import ai.SimpleAttackAI;
import app.RPGame;
import attacks.AbstractAttack;
import attacks.ShootingAttack;

public class Enemy extends CharacterDecorator {

	private HashMap<String, AbstractAttack> attacks = new HashMap<String, AbstractAttack>();
	@SuppressWarnings("unused")
	private String configURL;

	public Enemy(GameCharacter character, String configURL) {
		super(character);
		this.configURL = configURL;
		initResources();
	}

	public void initResources() {
		// TODO: initialize from JSON
		getCharacter().setDecorator(this);
		RPGame game = getCharacter().getGame();

		attacks.put("shooting", new ShootingAttack(game, this, "shooting"));
		getCharacter().getCounters().add("health",
				new Health(getCharacter().getCounters(), 5));
		initAI(game);
	}

	public void initAI(RPGame game) {
		character.getControllers().add("AttackAI",
				new SimpleAttackAI(game, this));
		character.getControllers().add("MovementAI",
				new AbstractPathFindingAI(game, this.getCharacter()));
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);
		System.out.println(getCharacter().getCounters().get("health"));
	}

	public HashMap<String, AbstractAttack> getAttacks() {
		return attacks;
	}

}
