package cat.itacademy.s05.t01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRankingDTO {

    private String name;
    private int gamesWon;
}
