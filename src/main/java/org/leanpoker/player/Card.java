package org.leanpoker.player;

public class Card {
	
	private String rank;
	private String suit;
	private int rankInt;
	
	public int getRankInt() {
		return rankInt;
	}
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
		this.rankInt = calcrankInt(rank);
		
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	

	private int calcrankInt(String rank) {
		if (rank.matches("[1-9]")) {
			return Integer.valueOf(rank);
		};
		switch (rank) {
			case "10": return 10;
			case "J": return 11;
			case "Q": return 12;
			case "K": return 13;
			default: return 14;
		}
	}
	
	

}
