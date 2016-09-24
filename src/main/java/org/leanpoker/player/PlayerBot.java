package org.leanpoker.player;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class PlayerBot {

    static final String VERSION = "No Test, No Fear";
	private final static Gson gson = new GsonBuilder().create();

    public static int betRequest(String gameStateJson) {
    	GameState game = gson.fromJson(gameStateJson, GameState.class);
    	int current_buy_in = game.getCurrent_buy_in();
    	Player ourself = game.getPlayers().get(game.getIn_action());
    	List<Card> ourCards = ourself.getHole_cards();
    	
    	
    	if (current_buy_in != 0) {
			 return current_buy_in + 3;
		}
		return 1000;
    }

    public static void showdown(JsonElement game) {
    }
}
