package cat.itacademy.s05.t01.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Information of a player inside the ranking")
public class PlayerRankingDTO {

    @Schema(description = "Name of the player", example = "John Doe")
    private String name;

    @Schema(description = "Number of gameplays won by the player", example = "15")
    private int gamesWon;
}
