package org.leanpoker.player;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class PlayerBotSafety {

    private static final String VERSION = "No Test, No Fear";
	private static final Gson gson = new GsonBuilder().create();

    public static int betRequest(String gameState) {

    	Map<String, Object> request = gson.fromJson(gameState, Map.class);
    	Object current_buy_in = request.get("current_buy_in");
    		if (current_buy_in != null) {
    			 Double d = Double.valueOf(current_buy_in.toString());
    			 return d.intValue();
    		}
    		return 1000;
    }

    public static void showdown(JsonElement game) {
    }
}
