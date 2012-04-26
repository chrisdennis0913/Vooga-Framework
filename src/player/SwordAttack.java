package player;

import actions.Attack;

public class SwordAttack {

	Attack attack;

	public SwordAttack(Attack attack) {
		this.attack = attack;
	}

	public boolean isEnabled() {
		return attack.getWrapper().getCharacter().getInventory()
				.isEquipped("sword");
	}

}
