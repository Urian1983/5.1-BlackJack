package cat.itacademy.s05.t01.domain.model;

public class Player {
    private final String name;
    private Hand hand;
    private boolean stay = false;

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void changeStay(){
        this.stay = !this.stay;
    }


    public Hand getHand() {
        return hand;
    }

}
