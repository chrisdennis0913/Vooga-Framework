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

	public class JSONPlayerActions {
		public JSONPlayerWalking walking;
	}
}
