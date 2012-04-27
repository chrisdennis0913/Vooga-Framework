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

		return gson.fromJson(jsonBuilder.toString(), JsonElement.class)
				.getAsJsonObject();
	}

	public static int[] JsonArrayToIntArray(JsonArray json) {
		int[] ret = new int[json.size()];
		for (int i = 0; i < json.size(); i++) {
			ret[i] = json.get(i).getAsInt();
		}
		return ret;
	}
}
