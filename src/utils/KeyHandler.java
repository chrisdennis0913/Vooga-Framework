package utils;

import java.util.HashMap;
import java.util.Iterator;

import app.RPGame;

import com.golden.gamedev.Game;

public class KeyHandler implements Iterable<Integer> {
	
	private HashMap<Integer, int[]> keyMap = new HashMap<Integer, int[]>();
	private RPGame game;
	
	public KeyHandler(RPGame game) {
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
		boolean isDirection = false;

		for (Integer direction : this) {
			for (int key : get(direction)) {
				if (!game.keyDown(key)) {
					isDirection = false;
					continue;
				}
				isDirection = true;
			}

			if (isDirection)
				return direction;
		}

		return -1;
	}
	
}
