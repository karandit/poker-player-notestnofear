package org.leanpoker.player;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    	
    	List<String> highRanks = Arrays.asList("9", "10", "J", "Q", "K", "A");
    	
    	boolean check = ourCards
    			.stream()
    			.map(card -> card.getRank())
    			.anyMatch(rank -> highRanks.contains(rank));
    
    	
    	boolean hazardRaise = isHazard(game);
    	boolean raise = isJustRaise(ourCards);
    	
    	if (hazardRaise) {
    		return current_buy_in - ourself.getBet() + game.getMinimum_raise() + 100;
		}
    	if (raise) {
    		return current_buy_in - ourself.getBet() + game.getMinimum_raise();
		}
    	if (check) {
    		return current_buy_in - ourself.getBet();
		}
		return 0;
    }

	private static boolean isJustRaise(List<Card> ourCards) {
		Map<String, List<Card>> groupedBySuit = groupedByRank(ourCards);
    	boolean raise = groupedBySuit
    			.entrySet()
    			.stream()
    			.anyMatch(entry -> entry.getValue().size() > 2);
		return raise;
	}

	private static boolean isHazard(GameState game) {
    	Player ourself = game.getPlayers().get(game.getIn_action());
    	List<String> ourRanks = ourself.getHole_cards().stream().map(card -> card.getRank()).collect(toList());
    	List<String> communityRanks = game.getCommunity_cards().stream().map(card -> card.getRank()).collect(toList());

		long countOfAtleatPairs = ourRanks.stream()
			.filter(ourRank -> communityRanks.contains(ourRank))
			.count();

		return countOfAtleatPairs > 1;
	}

	private static Map<String, List<Card>> groupedByRank(List<Card> ourCards) {
		Map<String, List<Card>> groupedBySuit = ourCards
    		.stream()
    		.collect(groupingBy(card -> card.getRank()));
		return groupedBySuit;
	}

    public static void showdown(JsonElement game) {
    }
}
