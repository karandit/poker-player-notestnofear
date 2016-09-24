package org.leanpoker.player;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class PlayerBot {

	private final static Gson gson = new GsonBuilder().create();

    public static int betRequest(String gameStateJson) {
    	GameState game = gson.fromJson(gameStateJson, GameState.class);
    	int current_buy_in = game.getCurrent_buy_in();
    	Player ourself = game.getPlayers().get(game.getIn_action());
    	List<Card> ourCards = ourself.getHole_cards();
    	
    	List<String> highCards = Arrays.asList("9", "10", "J", "Q", "K", "A");
    	
    	boolean haveAtLeastOneHighCard = ourCards
    			.stream()
    			.map(card -> card.getSuit())
    			.anyMatch(suit -> highCards.contains(suit));
    	
    	if (haveAtLeastOneHighCard) {
			 return current_buy_in;
		}
    	
		return 0;
    }

    public static void showdown(JsonElement game) {
    }
}
