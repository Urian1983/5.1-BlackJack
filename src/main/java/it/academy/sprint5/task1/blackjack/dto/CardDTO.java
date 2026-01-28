package it.academy.sprint5.task1.blackjack.dto;

import it.academy.sprint5.task1.blackjack.domain.enums.Rank;
import it.academy.sprint5.task1.blackjack.domain.enums.Suit;

public record CardDTO(Rank rank, Suit suit ) {
}
