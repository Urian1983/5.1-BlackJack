package cat.itacademy.s05.t01.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class DealerTest {
    private Dealer dealer;
    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
        dealer = new Dealer(hand);
    }

    @Test
    void should_Draw_Card_Is_True(){
        hand.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand.addCard(new Card(Rank.FIVE, Suit.CLUBS));
        assertThat(dealer.shouldDrawCard()).isTrue();

    }

    @Test
    void should_Not_Draw_Card_Is_True(){
        hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
        hand.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
        assertThat(dealer.shouldDrawCard()).isFalse();
    }

}