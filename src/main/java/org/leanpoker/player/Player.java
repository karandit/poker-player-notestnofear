package org.leanpoker.player;

import java.util.Map;

import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "No Test, No Fear";

    public static int betRequest(Map<String, Object> request) {
    		Object current_buy_in = request.get("current_buy_in");
    		if (current_buy_in != null) {
    			 Double d = Double.valueOf(current_buy_in.toString());
    			 return d.intValue();
    		}
    		return 15;
    }

    public static void showdown(JsonElement game) {
    }
}
