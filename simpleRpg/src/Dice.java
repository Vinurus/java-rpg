import java.util.Random;

class Dice {

    private final int sides;

    public Dice (int sides){
        if(0 < sides && sides < 20){
            this.sides = sides;
        }
        else this.sides = 20;
    }

    public int roll() {
        return new Random().nextInt(sides) + 1;
    }
}
