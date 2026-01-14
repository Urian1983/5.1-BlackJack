package cat.itacademy.s05.t01.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    Deck deck;
    final List<Card> cardsInHand = new ArrayList<>();

    public void addCard(){
        Card cardToDraw= deck.draw();
        cardsInHand.add(cardToDraw);
    }

    public List<Card> getCards(){
        return cardsInHand;
    }

    public int calculateValue(){
        return cardsInHand.stream()
                .mapToInt(card -> card.getRank().getValue())
                .sum();
    }

    public boolean isBust(){

    }

}
