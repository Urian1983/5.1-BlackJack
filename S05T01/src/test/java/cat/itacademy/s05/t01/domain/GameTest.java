package cat.itacademy.s05.t01.domain;

import cat.itacademy.s05.t01.domain.model.Card;
import cat.itacademy.s05.t01.domain.model.Rank;
import cat.itacademy.s05.t01.domain.model.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

    class GameTest {

        Game game;

        @Test
        @DisplayName("Should initialize a new game with 2 cards for each actor and IN_PROGRESS state")
        void shouldInitializeNewGameCorrectly() {
            Game game = new Game("Tester");
            assertThat(game.getId()).isNotNull();
            assertThat(game.getPlayer().getName()).isEqualTo("Tester");
            assertThat(game.getPlayer().getHand().getCards()).hasSize(2);
            assertThat(game.getDealer().getHand().getCards()).hasSize(2);
            assertThat(game.getState()).isEqualTo(GameState.IN_PROGRESS);
        }

        @Test
        @DisplayName("Player should bust when exceeding 21 points")
        void playerShouldBustWhenPointsExceed21() {
            Game game = new Game("Tester");
            game.getPlayer().getHand().addCard(new Card(Rank.TEN, Suit.SPADES));
            game.getPlayer().getHand().addCard(new Card(Rank.TEN, Suit.HEARTS));
            game.getPlayer().getHand().addCard(new Card(Rank.TEN, Suit.CLUBS));
            game.playerHit();
            assertThat(game.getState()).isEqualTo(GameState.PLAYER_BUST);
        }

        @Test
        @DisplayName("Dealer must follow the rule: Hit until score is 17 or more")
        void dealerShouldFollowStayOn17Rule() {
            Game game = new Game("Tester");
            game.playerStand(); // This triggers the automatic dealer turn
            int dealerScore = game.getDealer().getHand().calculateValue();
            assertThat(dealerScore).isGreaterThanOrEqualTo(17);
        }

        @Test
        @DisplayName("Dynamic Ace adjustment: 3 Aces and a 9 should result in 12 points")
        void shouldAdjustMultipleAcesDynamically() {
            Game game = new Game("Tester");
            game.getPlayer().getHand().getCards().clear();
            game.getPlayer().getHand().addCard(new Card(Rank.ACE, Suit.SPADES));
            game.getPlayer().getHand().addCard(new Card(Rank.ACE, Suit.HEARTS));
            game.getPlayer().getHand().addCard(new Card(Rank.ACE, Suit.CLUBS));
            game.getPlayer().getHand().addCard(new Card(Rank.NINE, Suit.DIAMONDS));
            int totalValue = game.getPlayer().getHand().calculateValue();
            assertThat(totalValue).isEqualTo(12);
        }

}