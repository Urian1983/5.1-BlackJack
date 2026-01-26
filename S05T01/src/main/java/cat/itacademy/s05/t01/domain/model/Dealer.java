package cat.itacademy.s05.t01.domain.model;

public class Dealer extends Player{

    public Dealer(Hand hand) {
        super("Dealer", hand);
    }
    public boolean shouldDrawCard(){
        if(getHand().calculateValue()< 17)
        {
            return true;
        }

        else{
            return false;
        }
    }
}
