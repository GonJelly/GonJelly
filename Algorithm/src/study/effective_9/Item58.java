package study.effective_9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Item58 {

	static Collection<Suit> suits  = Arrays.asList(Suit.values());
	static Collection<Rank> rank  = Arrays.asList(Rank.values());

	public static void main(String[] args) {
		List<Card> deck = new ArrayList();
		for(Iterator<Suit> s = suits.iterator(); s.hasNext();) {
			for(Iterator<Rank> r = rank.iterator(); r.hasNext();) {
				deck.add(new Card(s.next(), r.next()));
			}
		}
		deck.removeIf((Predicate<? super Card>)deck.get(1));
		for(int i = 0; i < deck.size(); i++) {
			if(deck.get(i).suit.equals(Suit.CLUB)) {
				deck.remove(i);
			}
		}
	}
}

class Card {
	Suit suit;
	Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
}

enum Suit {
	CLUB,
	DIAMOND,
	HEART,
	SPADE
}

enum Rank {
	ACE,
	DEUCE,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE,
	TEN,
	JACK,
	QUEEN,
	KING
}