package utils;

import java.util.HashMap;
import java.util.Iterator;

import app.RPGame;

public class KeyHandle implements Iterable<Integer> {
	
	private HashMap<Integer, int[]> keyMap = new HashMap<Integer, int[]>();
	private RPGame game;

	public KeyHandle(RPGame game) {
		this.game = game;
	}

	public void add(int direction, int[] keys) {
		keyMap.put(direction, keys);
	}
	
	public int[] get(int direction) {
		return keyMap.get(direction);
	}
	
	public Iterator<Integer> iterator() {
		return keyMap.keySet().iterator();
	}	
	
	public int checkKeys() {
		boolean isKey = false;

		for (Integer keyCode : this) {
			for (int key : get(keyCode)) {
				if (!game.keyDown(key)) {
					isKey = false;
					continue;
				}
				isKey = true;
			}

			if (isKey)
				return keyCode;
		}

		return -1;
	}
	
}
