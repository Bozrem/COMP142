/*
    Name: Remy Bozung
    Date: 8/10/2024
    Class: CS142
    Pledge: I have neither given nor received unauthorized aid on this program.
    Description: A CLI game of Nim with two players. This class specifically is the main controller class, used to initialize games
*/
import java.util.Scanner;

public class Main {
    public static boolean debugMode = false;

    public static void main(String[] args) {
        if (args.length > 0) debugMode = Boolean.parseBoolean(args[0]);

        System.out.println("Welcome to NeoNim!\n");

        getPlayers();
        Pile[] piles = Pile.fromIntArray(Pile.fromUserInput());

        startGameLoop(piles);

        System.out.println("\nThanks for playing!");
    }

    /*
    Begins the game loop to start a game and ask if another should be played after
     */
    public static void startGameLoop(Pile[] piles){
        boolean playAnother = true;
        while (playAnother){
            Game.playNewGame(piles);
            System.out.println("\nPlayer " + (Player.getActivePlayerNumber() + 1) + " Loses!\n\n");
            playAnother = askPlayAnother();
        }
    }

    /*
    Ask the user if they would like to play another game
     */
    public static boolean askPlayAnother(){
        boolean playAnother = Game.userYesOrNo("Good Game! Would you like to play another?");
        if (!playAnother) return false;
        //boolean changePiles = Game.userYesOrNo("Would you like to change the pile sizes?");
        //if (changePiles) ;
        // TODO fix this
        boolean changePlayers = Game.userYesOrNo("Would you like to change the amount of players?");
        if (changePlayers) getPlayers();
        return true;
    }

    /*
    Get the player count for the game
     */
    public static void getPlayers(){
        Scanner scan = new Scanner(System.in);
        System.out.println("NeoNim allows for a wide variety of player and pile options!");
        System.out.println("You can play both with other people and against computers.");
        System.out.println("Try 1 human 1 computer for a more classic game.\n");
        int humans = -1;
        int computers = -1;
        while (humans < 0 || computers < 0 || computers + humans < 2){
            System.out.print("Amount of human players: ");
            humans = scan.nextInt();
            System.out.print("Amount of computer opponents: ");
            computers = scan.nextInt();
        }
        System.out.println(); // To space out before pile sizes
        Player.initializePlayers(humans, computers);
    }
}
