import java.util.ArrayList;
import java.util.Random;

public class GeneratePlayers {

    ArrayList<Player> players = new ArrayList<>();

    public void generate (int count){
        String [] names = {"Karol",
                "Marek",
                "Zozol",
                "Karolina"};

        for(int i=0; i < count; i++){
            Player gracz =
                    new Player(names[new Random().nextInt(names.length)]);
            players.add(gracz);
        }

    }

}
