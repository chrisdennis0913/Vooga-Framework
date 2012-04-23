package utils;

import java.util.HashMap;
import java.util.Iterator;

import com.golden.gamedev.Game;

public class KeyHandle implements Iterable<Integer> {
	
	private HashMap<Integer, int[]> keyMap = new HashMap<Integer, int[]>();
	private Game game;
	
	public KeyHandle(Game game) {
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
