package it.academy.sprint5.task1.blackjack.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import it.academy.sprint5.task1.blackjack.domain.enums.Rank;
import it.academy.sprint5.task1.blackjack.domain.enums.Suit;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cards")
public class Card {

    @Schema(description = "Value of the card", example = "ACE")
    private final Rank rank;
    @Schema(description = "Suit of the card", example = "HEARTS")
    private final Suit suit;

}
