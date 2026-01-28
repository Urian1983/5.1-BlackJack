package it.academy.sprint5.task1.blackjack.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("player_ranking")
public class PlayerRanking {

    @Id
    private Long id;

    //@Schema(description = "Name of the player", example = "John Doe")
    private String name;

    //@Schema(description = "Number of gameplays won by the player", example = "15")
    private int gamesWon;

}
