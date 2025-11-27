import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Game {
    private HashMap<Player, Integer> fields = new HashMap <Player,Integer>();
    private final ArrayList <Player> players;
    private final Dice dice;
    private final int fieldsNumber;
    private int rounds;

    public Game (ArrayList <Player> players, Dice dice, int fieldsNumber, int rounds){
        this.players = players;
        this.dice = dice;
        this.rounds = rounds;
        if(fieldsNumber == 0 || fieldsNumber > 200)
            this.fieldsNumber = 100;
        else
            this.fieldsNumber = fieldsNumber;

        for (Player player: players)
            fields.put(player, 0);
    }

    public void run() {
        Player winner = null;
        boolean endless = (rounds == 0);

            while (winner == null) {


               //tylko dla żywych graczy
                for (Player player :
                        players.stream()
                        .filter(Player::isAlive)
                        .toList()) {
                    int result = dice.roll();
                    int position = fields.get(player) + result;
                    fields.put(player, position);

                    if (position >= fieldsNumber) {
                        position = fieldsNumber;
                    }


                    //damage na polach podzielnych przez 7
                    if(position%7 == 0){
                        player.takeDamage(dice.roll());
                        if(!player.isAlive())
                            System.out.println("Player "+ player.getName() + " died.");
                    }

                    //damage od innych graczy
                    List<Player> skirmish;
                    int finalPosition = position;

                    skirmish =  fields.entrySet()
                                    .stream()
                                    .filter(entry -> entry.getValue() == finalPosition) //na tej samej pozycji
                                    .filter(entry -> entry.getKey() != player) //różny od player
                                    .map(Map.Entry::getKey)
                                    .toList();

                    if(!skirmish.isEmpty()){
                        for(Player fight : skirmish){
                            int i = dice.roll();
                            player.takeDamage(i);

                            System.out.println("Player "+ player.getName() + " took " + i + " damage from " + fight.getName());
                            if(!player.isAlive())
                                System.out.println("Player "+ player.getName() + " died.");
                        }
                    }

                    System.out.println(player.getName() + " rolled " + result + ". Now is on position " + position+ " | HP "+ player.getHealthPoints());

                    //winner check - endless
                    if (position >= fieldsNumber) {
                        System.out.println(player.getName() + " won!");
                        winner = player;
                        break;
                    }

                }
                if(!endless) rounds--;

                //winner check - przy określonej ilości rund
                if(!endless && rounds == 0) {
                    winner = fields.entrySet()
                            .stream()
                            .max(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey)
                            .orElse(null);
                    if(winner != null)
                        System.out.println(winner.getName() + " won!");
                    break;
                }
            }


    }
}