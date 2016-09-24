package org.leanpoker.player;
import java.util.List;

//@Data
public class Player {
	
	private int id;
	private String name;
    private String status;
    private String version;
    private int stack;
    private int bet;
    private List<Card> hole_cards;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getStack() {
		return stack;
	}
	public void setStack(int stack) {
		this.stack = stack;
	}
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	public List<Card> getHole_cards() {
		return hole_cards;
	}
	public void setHole_cards(List<Card> hole_cards) {
		this.hole_cards = hole_cards;
	}
    
}
