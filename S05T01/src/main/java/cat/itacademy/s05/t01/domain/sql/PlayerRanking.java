package cat.itacademy.s05.t01.domain.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("player_ranking")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PlayerRanking {
    @Id
    private Long id;
    private String name;
    private int gamesWon;
}
