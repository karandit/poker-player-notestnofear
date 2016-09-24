package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class PlayerBot {

    static final String VERSION = "No Test, No Fear";
	private final static Gson gson = new GsonBuilder().create();

    public static int betRequest(String gameStateJson) {
    	GameState request = gson.fromJson(gameStateJson, GameState.class);
    	int current_buy_in = request.getCurrent_buy_in();
    		if (current_buy_in != 0) {
    			 return current_buy_in;
    		}
    		return 1000;
    }

    public static void showdown(JsonElement game) {
    }
}
