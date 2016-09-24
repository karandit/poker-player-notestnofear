package org.leanpoker.player;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    	
    
    	
    	if (isPoker(game) || isStraight(game)) {
    		return current_buy_in - ourself.getBet() + game.getMinimum_raise() + 450;
		}
    		
    		
    	if (isHazard(game)) {
    		return current_buy_in - ourself.getBet() + game.getMinimum_raise() + 100;
		}

    	boolean raise = isJustRaise(ourCards);
    	if (raise) {
    		return current_buy_in - ourself.getBet() + game.getMinimum_raise();
		}

    	long highCards = ourCards
    			.stream()
    			.map(card -> card.getRank())
    			.filter(rank -> highRanks.contains(rank))
    			.count();
    	
    	if (highCards > 1 && current_buy_in < ourself.getBet() + 450) {
    		return current_buy_in - ourself.getBet();
		}
    	
    	if (highCards == 1 && current_buy_in < ourself.getBet() + 100) {
    		return current_buy_in - ourself.getBet();
		}
		return 0;
    }

	private static boolean isPoker(GameState game) {
    	Player ourself = game.getPlayers().get(game.getIn_action());
    	
    	Map<String, List<Card>> groupedByRank = ourself.getHole_cards()
	    		.stream()
	    		.collect(groupingBy(card -> card.getRank()));

    	return groupedByRank.entrySet().stream().anyMatch(entry -> entry.getValue().size() == 4);
	}

	private static boolean isStraight(GameState game) {
    	Player ourself = game.getPlayers().get(game.getIn_action());
    	List<Card> allCards = new LinkedList<Card>();
    	allCards.addAll(ourself.getHole_cards());
    	allCards.addAll(game.getCommunity_cards());

    	if (allCards.size() < 5) {
    		return false;
    	}

    	List<Integer> sortedranks = allCards.stream()
	    	.map(card -> card.getRankInt())
	    	.sorted(Integer::compare).collect(Collectors.toList());
    	
    	for (int i = 0; i < sortedranks.size() - 1; i++) {
			if (Math.abs(sortedranks.get(i) - sortedranks.get(i+1)) != 1) {
				return false;
			}
		}
    	
		return true;
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
