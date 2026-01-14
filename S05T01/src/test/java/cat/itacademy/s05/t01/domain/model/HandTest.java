package cat.itacademy.s05.t01.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    void shouldCalculateSimpleScore() {
        hand.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand.addCard(new Card(Rank.FIVE, Suit.CLUBS));
        assertThat(hand.calculateValue()).isEqualTo(7);
    }

    @Test
    void shouldCalculateScoreWithFaceCards() {
        hand.addCard(new Card(Rank.KING, Suit.SPADES));
        hand.addCard(new Card(Rank.JACK, Suit.DIAMONDS));
        assertThat(hand.calculateValue()).isEqualTo(20);
    }

    @Test
    void shouldCalculateScoreWithAceAsEleven() {
        hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.addCard(new Card(Rank.NINE, Suit.SPADES));
        // 11 + 9 = 20
        assertThat(hand.calculateValue()).isEqualTo(20);
    }

    @Test
    void shouldCalculateScoreWithAceAsOneToAvoidBust() {
        hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.addCard(new Card(Rank.NINE, Suit.SPADES));
        hand.addCard(new Card(Rank.FIVE, Suit.CLUBS));
        assertThat(hand.calculateValue()).isEqualTo(15);
    }

    @Test
    void shouldHandleMultipleAces() {
        hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
        hand.addCard(new Card(Rank.NINE, Suit.CLUBS));
        // 11 + 1 + 9 = 21 (Solo un As puede valer 11)
        assertThat(hand.calculateValue()).isEqualTo(21);
    }

    @Test
    void shouldHandleThreeAces() {
        hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS));
        // 11 + 1 + 1 = 13 (solo un As vale 11)
        assertThat(hand.calculateValue()).isEqualTo(13);
    }
}
