package cat.itacademy.s05.t01.domain.model;

public class Player {
    private final String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

}
