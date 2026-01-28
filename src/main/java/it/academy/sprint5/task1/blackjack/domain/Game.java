package it.academy.sprint5.task1.blackjack.domain;

import it.academy.sprint5.task1.blackjack.domain.model.Dealer;
import it.academy.sprint5.task1.blackjack.domain.model.Deck;
import it.academy.sprint5.task1.blackjack.domain.model.Hand;
import it.academy.sprint5.task1.blackjack.domain.model.Player;
import it.academy.sprint5.task1.blackjack.exceptions.IlegalMoveException;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import it.academy.sprint5.task1.blackjack.domain.enums.GameState;

import java.util.UUID;

@Getter
@Document(collection ="game")
public class Game {

    @Id
    private String id = UUID.randomUUID().toString();

    private Player player;
    private Dealer dealer;
    private Deck deck;
    private GameState state;

    public Game(String playerName){
        this.deck = new Deck();
        this.player = new Player(playerName,new Hand());
        this.dealer = new Dealer(new Hand());
        this.state = GameState.IN_PROGRESS;

        dealInitialCards();
    }

    public void dealInitialCards(){
        deck.shuffle();

        player.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());
        player.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());

        this.state = GameState.IN_PROGRESS;
    }

    public void playerHit(){
        if (state != GameState.IN_PROGRESS) {
            throw new IlegalMoveException("Cannot hit. The game is already over with state: " + state);
        }

        player.getHand().addCard(deck.draw());

        if(player.getHand().isBust()){
            this.state =GameState.PLAYER_BUST;
        }

    }

    public void playerStand(){
        if (state != GameState.IN_PROGRESS) {
            throw new IlegalMoveException("Cannot stand. The game is already over.");
        }

        player.changeStay();
        playDealerTurn();

    }

    public void playDealerTurn(){
        while(dealer.shouldDrawCard()){
            dealer.getHand().addCard(deck.draw());
        }

        if(dealer.getHand().isBust()){
            this.state = GameState.DEALER_BUST;
        }

        else {
            resolveGame();
        }

    }

    public void resolveGame(){
        int playerPoints = player.getHand().calculateValue();
        int dealerPoints = dealer.getHand().calculateValue();

        if (playerPoints > dealerPoints){
            this.state = GameState.PLAYER_WIN;
        }

        else if(playerPoints < dealerPoints){
            this.state = GameState.DEALER_WIN;
        }

        else{
            this.state = GameState.DRAW;
        }

    }
}
