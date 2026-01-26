package cat.itacademy.s05.t01.mapper;

import cat.itacademy.s05.t01.domain.Game;
import cat.itacademy.s05.t01.domain.model.Card;
import cat.itacademy.s05.t01.domain.model.PlayerRanking;
import cat.itacademy.s05.t01.dto.CardDTO;
import cat.itacademy.s05.t01.dto.GameResponseDTO;
import cat.itacademy.s05.t01.dto.PlayerRankingDTO;

import java.util.List;
import java.util.stream.Collectors;

public class GameMapper {

    private static List<CardDTO> mapToCardDTOList(List<Card> cards) {
        return cards.stream()
                .map(card -> new CardDTO(
                        card.getSuit().toString(),
                        card.getRank().toString()
                ))
                .collect(Collectors.toList());
    }

    public static GameResponseDTO toResponse(Game game){
        GameResponseDTO dto = new GameResponseDTO();

        dto.setId((game.getId().toString()));
        dto.setPlayerName(game.getPlayer().getName());
        dto.setStatus(game.getState().toString());

        dto.setPlayerHand(mapToCardDTOList(game.getPlayer().getHand().getCards()));
        dto.setPlayerValue(game.getPlayer().getHand().calculateValue());

        dto.setDealerHand(mapToCardDTOList(game.getDealer().getHand().getCards()));
        dto.setDealerValue(game.getDealer().getHand().calculateValue());

        return dto;
    }

    public static PlayerRankingDTO playerRankingResponse (PlayerRanking ranking) {
        return new PlayerRankingDTO(ranking.getName(), ranking.getGamesWon());
    }
}
