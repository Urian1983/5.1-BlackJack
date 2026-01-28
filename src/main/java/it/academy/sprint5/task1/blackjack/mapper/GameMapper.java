package it.academy.sprint5.task1.blackjack.mapper;

import it.academy.sprint5.task1.blackjack.domain.Game;
import it.academy.sprint5.task1.blackjack.domain.model.PlayerRanking;
import it.academy.sprint5.task1.blackjack.dto.GameResponseDTO;
import it.academy.sprint5.task1.blackjack.dto.PlayerRankingDTO;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(target = "playerName", source = "player.name")
    @Mapping(target = "playerHand", source = "player.hand.cards")
    @Mapping(target = "playerValue", expression = "java(game.getPlayer().getHand().calculateValue())")
    @Mapping(target = "dealerHand", source = "dealer.hand.cards")
    @Mapping(target = "dealerValue", expression = "java(game.getDealer().getHand().calculateValue())")
    @Mapping(target = "status", source = "state")
    GameResponseDTO toDTO(Game game);

    @Mapping(target = "playerName", source = "name")
    @Mapping(target = "victories", source = "gamesWon")
    PlayerRankingDTO playerRankingResponse(PlayerRanking ranking);
}
