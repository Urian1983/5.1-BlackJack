package cat.itacademy.s05.t01.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    final List<Card> cardsInHand = new ArrayList<>();

    public void addCard(Card card){
        cardsInHand.add(card);
    }

    public List<Card> getCards(){
        return cardsInHand;
    }

    public int calculateValue(){

        int total = cardsInHand.stream()
                .mapToInt(card -> card.getRank().getMinValue())
                .sum();

        boolean hasAce = cardsInHand.stream()
                .anyMatch(card -> card.getRank() == Rank.ACE);

        if (hasAce && total + 10 <= 21) {
            total += 10;
        }

        return total;
    }

    public boolean isBust(){
        return calculateValue() > 21;
    }

}
