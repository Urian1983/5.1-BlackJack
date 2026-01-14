package cat.itacademy.s05.t01.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
class DeckTest {
    private Deck deck;

    @BeforeEach
    void setUp(){
        deck = new Deck();
    }


    @Test
    void deck_should_have_52_cards() {

        assertThat(deck.getDeck()).hasSize(52);
    }

    @Test
    void deck_should_have_one_less_afterDraw(){
        assertThat(deck.getDeck()).hasSize(52);
        deck.draw();
        assertThat(deck.getDeck()).hasSize(51);
    }

    @Test
    void deck_should_not_be_empty(){
        assertThat(deck.isEmpty()).isFalse();
    }

}