package cat.itacademy.s05.t01.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck = new ArrayList<>();

    public Deck(){
        Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, suit)))
                .forEach(deck::add);
    }

    public List<Card> getDeck() {
        return List.copyOf(deck);
    }

    public void shuffle(){
        Collections.shuffle(deck);

    }
    public Card draw(){

        Card cardToDraw = deck.get(0);
        deck.remove(0);
        return cardToDraw;
    }

    public int size(){
        return deck.size();

    }

    public boolean isEmpty(){
       return deck.isEmpty();
    }

}



