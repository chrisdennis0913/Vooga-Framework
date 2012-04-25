package player;

import npc.NPC;
import utils.JsonUtil.JSONPlayerTalking;
import utils.KeyHandle;
import actions.ActionDecorator;
import actions.Talk;

import com.golden.gamedev.object.Timer;

public class Talking extends ActionDecorator {

	private static final long serialVersionUID = 1L;

	private KeyHandle keys;
	private Timer timer = new Timer(1000);
	private Talk talk;

	public Talking(Talk talk) {
		super(talk);
		
		this.talk = talk;
		initResources();
	}

	public void initResources() {
		keys = new KeyHandle(getWrapper().getCharacter().getGame());

		JSONPlayerTalking talking = (JSONPlayerTalking) getJsonable();
		if (talking.keys == null)
			new RuntimeException("Talking keys undefined");

		keys.add(Talk.TALK_BASIC, talking.keys);
	}
	
	public void setNPC(NPC npc) {
		talk.setNpc(npc);
	}
	
	public NPC getNPC() {
		return talk.getNpc();
	}

	public void update(long elapsed) {
		super.update(elapsed);

		if (isEnabled()) {
			int status = keys.checkKeys();

			if (status != -1) {;
				if (!isActive()) {
					timer.setActive(true);
					setActive(true);
					talk.setMessage("You very nice lady!");
					getNPC().stop();
				}
			} else if (isActive()) {
				if (timer.action(elapsed)) {
					timer.refresh();
					timer.setActive(false);
					setActive(false);
					setEnabled(false, true);
				}

			}
		}
	}

}
