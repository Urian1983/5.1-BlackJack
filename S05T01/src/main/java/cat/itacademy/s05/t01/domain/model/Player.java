package cat.itacademy.s05.t01.domain.model;

import lombok.Getter;

public class Player {
    @Getter
    private final String name;
    @Getter
    private Hand hand;
    private boolean stay = false;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void changeStay(){
        this.stay = !this.stay;
    }

}
