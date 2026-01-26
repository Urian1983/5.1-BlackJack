package cat.itacademy.s05.t01.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a card of the Blackjack game")
public class CardDTO {

    @Schema(description = "Suit of the card", example = "HEARTS")
    private String suit;
    @Schema(description = "Value of the card", example = "ACE")
    private String rank;
}
