package player;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import utils.JsonUtil;
import utils.KeyHandle;
import actions.ActionDecorator;
import actions.Attack;

import com.golden.gamedev.object.Timer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import enemy.AbstractEnemy;
import gameCharacter.GameCharacter;

public class Attacking extends ActionDecorator {

	private static final long serialVersionUID = 1L;

	private KeyHandle keys;
	private Timer timer = new Timer(250);
	private List<ActionDecorator> attacks = new ArrayList<ActionDecorator>();

	public Attacking(Attack attack) {
		super(attack);
		initResources();
	}

	public void initResources() {
		timer.setActive(false);
		
		keys = new KeyHandle(getWrapper().getCharacter().getGame());

		JsonObject attacking = getJsonObject();
		JsonArray jAttackingKeys = attacking.getAsJsonArray("keys");
		int[] attackingKeys = JsonUtil.JsonArrayToIntArray(jAttackingKeys);

		if (attackingKeys == null)
			new RuntimeException("Attack keys undefined");

		keys.add(Attack.ATTACK_BASIC, attackingKeys);

		JsonArray attackingAttacks = attacking.getAsJsonArray("attacks");
		for (int j = 0; j < attackingAttacks.size(); j++) {
			attacks.add(new StdAttack(new Attack(getWrapper(), attackingAttacks
					.get(j).getAsJsonObject())));
		}

	}

	public int getDamage(AbstractEnemy abstractEnemy) {
		for (ActionDecorator attack : attacks)
			if (attack.isEnabled()) {
				StdAttack attk = (StdAttack) attack;
				return attk.getDamage(abstractEnemy);
			}
		return 0;
	}

	public void setActiveDirection(int direction) {
		for (ActionDecorator attack : attacks)
			if (attack.isEnabled()) {
				getWrapper().getCharacter().setCurrentDirection(direction);
				StdAttack attk = (StdAttack) attack;
				attk.getAttack().directions.get(direction)
						.changeCharacter(true);
			}
	}

	public boolean isEnabled() {
		for (ActionDecorator attack : attacks)
			if (attack.isEnabled())
				return true;
		return false;
	}

	public void update(long elapsed) {
		if (isEnabled()) {
			super.update(elapsed);

			int status = keys.checkKeys();
			GameCharacter character = getWrapper().getCharacter();

			if (status != -1) {
				if (!isActive() && !timer.isActive()) {
					setActive(true);
					timer.setActive(true);
					character.stop();
					setActiveDirection(character.getCurrentDirection());
					getWrapper().get("walking").setEnabled(false, true);
				}
			} else if (!getWrapper().get("walking").isEnabled())
				if (timer.action(elapsed))
					finishAttack();
		}
	}
	
	public void finishAttack() {
		getWrapper().get("walking").setEnabled(true, false);
		setActive(false);
		timer.refresh();
		timer.setActive(false);
	}

	public void render(Graphics2D g) {
	}

}
