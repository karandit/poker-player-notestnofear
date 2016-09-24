package org.leanpoker.player;

import java.util.List;

import lombok.Data;

@Data
public class GameState {
	private final List<Player> players;
	private final String tournament_id;
	private final String game_id;
	private final int round;
	private final int bet_index;
	private final int small_blind;
	private final int current_buy_in;
	private final int pot;
	private final int minimum_raise;
	private final int dealer;
	private final int orbits;
	private final int in_action;
	private final List<Card> community_cards;
	
}
