package cat.itacademy.s05.t01.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("player_ranking")
public class PlayerRanking {

    @Id
    private Long id;

    private String name;

    private int gamesWon;

    public PlayerRanking(String name, int gamesWon) {
        this.name = name;
        this.gamesWon = gamesWon;
    }
}
