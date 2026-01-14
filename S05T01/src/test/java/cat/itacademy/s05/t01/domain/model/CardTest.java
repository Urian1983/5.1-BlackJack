package cat.itacademy.s05.t01.domain.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CardTest {

    @Test
    void card_should_have_name_and_value() {
        Card card = new Card(Rank.ACE, Suit.HEARTS);

        assertThat(card.getRank()).isEqualTo(Rank.ACE);
        assertThat(card.getSuit()).isEqualTo(Suit.HEARTS);
    }


}