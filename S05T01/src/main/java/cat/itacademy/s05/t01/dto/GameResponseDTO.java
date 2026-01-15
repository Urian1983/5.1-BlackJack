package cat.itacademy.s05.t01.dto;

import lombok.Data;

import java.util.List;

@Data
public class GameResponseDTO {
    private String id;
    private String playerName;
    private List<CardDTO> playerHand;
    private int playerValue;
    private List<CardDTO> dealerHand;
    private int dealerValue;
    private String status;
}
