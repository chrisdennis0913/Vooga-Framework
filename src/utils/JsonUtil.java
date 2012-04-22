package utils;

import java.io.File;

import com.golden.gamedev.util.FileUtil;

/**
 * JSON loader utility for different classes.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
 * 
 * @author Kirill Klimuk
 */

public class JsonUtil {
	public static String getJSON(String url) {
		String[] jsonPacked = FileUtil.fileRead(new File(url));

		StringBuilder jsonBuilder = new StringBuilder();
		for (String line : jsonPacked) {
			jsonBuilder.append(line);
		}
		
		return jsonBuilder.toString();
	}
	
	public class JSONDirections {
		public int frames;
		public int delay;
		public JSONDirection[] directions;
	}
	
	public class JSONDirection {
		public String direction;
		public String image;
	}

	public class JSONPlayerWalking {
		public int[] up;
		public int[] down;
		public int[] left;
		public int[] right;
	}

	public class JSONLevel{
		public String background;
		public String nextLevel;
		//public Player player;
		//public Enemy[] enemies;
		//public NPC[] npcs;		
	}
	 public class JSONPlayer{
		 public int[] location;
		 public String directions;
		 public String actions;		 
	 }
	 
	 public class JSONEnemy{
		 public int[] location;
		 public String directions;
		 public String actions;		 
	 }
	 
	 public class JSONNpc{
		 public int[] location;
	}
	 
	 public class JSONScenery{
		 public int[] locations;
		 public String imageURL;
		 public int layer;
	 }
	 
	public class JSONPlayerActions {
		public JSONPlayerWalking walking;
	}
}
