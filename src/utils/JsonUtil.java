
package utils;

import java.io.File;

import com.golden.gamedev.util.FileUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * JSON loader utility for different classes.
 * 
 * @author Kirill Klimuk
 */

public class JsonUtil {
	public static JsonObject getJSON(String url) {
		Gson gson = new Gson();
		String[] jsonPacked = FileUtil.fileRead(new File(url));

		StringBuilder jsonBuilder = new StringBuilder();
		for (String line : jsonPacked) {
			jsonBuilder.append(line);
		}

		return gson.fromJson(jsonBuilder.toString(), JsonElement.class).getAsJsonObject();
	}

	
	public static int[] JsonArrayToIntArray(JsonArray json){
		int[] ret = new int[json.size()];
		for(int i=0; i<json.size(); i++){
			ret[i] = json.get(i).getAsInt();
		}		
		return ret;		
	}
/*
 * 
 * 
	public class JSONPlayerAttacking implements Jsonable {
		public int[] keys;
		public JSONAttack[] attacks;
	}
	
	public class JSONAttack implements Jsonable {
		public String type;
		public JSONDirections directions;
	}
	
	public class JSONDirections implements Jsonable {
		public int frames;
		public int delay;
		public JSONDirection[] directions;
	}

	public class JSONDirection implements Jsonable {
		public String direction;
		public String image;
	}

	public class JSONPlayerWalking implements Jsonable {
		public int[] up;
		public int[] down;
		public int[] left;
		public int[] right;
	}

	public class JSONPlayerAttacking implements Jsonable {
		public int[] keys;
		public JSONDirections directions;
	}

	public class JSONLevel implements Jsonable {
		public String nextLevel;
		public String upperFilename;
		public String lowerFilename;
		public JSONPlayer player;
		// public Enemy[] enemies;
		public NPC[] npcs;
	}

	public class JSONGame implements Jsonable {
		public String level;

	}
	
	public class JSONPlayerTalking implements Jsonable {
		public int[] keys;
	}

	public class JSONLevel implements Jsonable {
		public String nextLevel;
		public String upperFilename;
		public String lowerFilename;
		public JSONPlayer player;
		public JSONNpc[] npcs;
	}

	public class JSONNpc implements Jsonable {
		public int[] location;
		public String directions;
	}
	
	public class JSONGame implements Jsonable {
		public String level;
	}
	
	public class JSONPlayer implements Jsonable {
		public int[] location;
		public String directionsURL;
		public String actionsURL;
	}

	public class JSONEnemy implements Jsonable {
		public int[] location;
		public String directions;
		public String actions;
	}

	public class JSONScenery implements Jsonable {
		public int[] locations;
		public String imageURL;
		public int layer;
	}

	public class JSONPlayerActions {
		public JSONPlayerWalking walking;
		public JSONPlayerAttacking attacking;
		public JSONPlayerTalking talking;
	}
	*/
}
