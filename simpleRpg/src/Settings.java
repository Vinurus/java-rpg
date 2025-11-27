import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Settings {

    private final Scanner sc = new Scanner(System.in);

    public void settings(){

        while(true){
            int choice;

            System.out.println("Wybierz: ");
            System.out.println("1: losowa gra automatyczna");
            System.out.println("2: ustawienia własne");



            if(sc.hasNextInt()){
                choice = sc.nextInt();
                if(choice <= 2) {
                    switch (choice) {
                        case 1:
                            automaticGame();
                            break;
                        case 2:
                            customSettings();
                            break;
                    }
                    break;
                }
            }
            else{
                System.out.println();
                System.out.println("Nieprawidłowy wybór!");
                System.out.println();
            }

            sc.nextLine();

        }

    }

    private void automaticGame(){
        Dice dice = new Dice(new Random().nextInt(20));
        GeneratePlayers gn = new GeneratePlayers();
        gn.generate(new Random().nextInt(10)+1);

        Game game = new Game(gn.players, dice,
                new Random().nextInt(200), 0);
        game.run();
    }

    private void customSettings(){
        Dice dice;
        ArrayList <Player> players;
        int fields, rounds;


        System.out.println("Podaj imiona graczy (0 przerywa wprowadzanie): ");
        players = inputPlayers();

        System.out.println("Podaj rozmiar kostki do gry (3-20): ");
        dice = inputDice();

        System.out.println("Ustawić ilość pól gry? (Y/N)");
        fields = inputFields();

        System.out.println("Ustawić ilość rund? (Y/N)");
        rounds = inputFields();

        Game game = new Game(players, dice, fields, rounds);
        game.run();
    }


    private ArrayList<Player> inputPlayers(){
        ArrayList <Player> players = new ArrayList<>();

        while(true){
            String name;
            name = sc.nextLine().trim();
            if(!name.isEmpty() && !name.equals("0")){
                Player gracz = new Player(name);
                players.add(gracz);
            }
            if(name.equals("0")) break;
        }
        return players;

    }
    private Dice inputDice(){
        while(true){
            String input = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value >= 3 && value <= 20)
                    return new Dice(value);
                else
                    System.out.println("Podaj liczbę z zakresu 3–20:");

            } catch (NumberFormatException e) {
                System.out.println("To nie jest liczba, spróbuj ponownie:");
            }
        }
    }
    private int inputFields(){

        while(true) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("Y")) {
                while (true) {
                    try {
                        System.out.println("(10-200)");
                        input = sc.nextLine().trim();
                        int value = Integer.parseInt(input);
                        if (value >= 10 && value <= 200)
                            return value;
                        else
                            System.out.println("Podaj liczbę z zakresu 10-200:");

                    } catch (NumberFormatException e) {
                        System.out.println("To nie jest liczba, spróbuj ponownie:");
                    }
                }
            }
            if (input.equalsIgnoreCase("N")) {
                return 0;
            }
        }
    }
}
