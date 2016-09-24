package org.leanpoker.player;
import java.util.List;

import lombok.Data;

@Data
public class Player {
	private final int id;
	private final String name;
    private final String status;
    private final String version;
    private final int stack;
    private final int bet;
    private final List<Card> hole_cards;
    
}
