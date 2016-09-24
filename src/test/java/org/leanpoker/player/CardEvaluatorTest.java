package org.leanpoker.player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CardEvaluatorTest {

	@Test
	public void createRequest() throws IOException {
		Card card1 = new Card();
		card1.setRank("8");
		card1.setSuit("diamonds");
		Card card2 = new Card();
		card2.setRank("10");
		card2.setSuit("diamonds");
		List<Card> cards = new ArrayList<>(Arrays.asList(card1, card2));
		
		CardEvaluator cardEvaluator = new CardEvaluator();
		cardEvaluator.createRequest(cards);
	}
	
}
