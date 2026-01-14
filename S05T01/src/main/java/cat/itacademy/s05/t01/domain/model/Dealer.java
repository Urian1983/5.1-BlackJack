package cat.itacademy.s05.t01.domain.model;

public class Dealer extends Player{

    public Dealer(String name, Hand hand) {
        super(name, hand);
    }
    public boolean shouldDrawCard(){
        return getHand().calculateValue()< 17;
    }
}
