/*
    Name: Remy Bozung
    Date: 8/10/2024
    Class: CS142
    Pledge: I have neither given nor received unauthorized aid on this program.
    Description: A CLI game of Nim with two players. This class specifically is the main controller class, used to initialize games
*/
import java.util.Scanner;

public class Main {

    private static boolean debugMode = false;
    static int[] pileSizes;

    public static void main(String[] args) {
        if (args.length > 0) debugMode = Boolean.parseBoolean(args[0]);

        System.out.println("Welcome to Nim!");

        pileSizes = getInfo();

        if (debugMode) {
            System.out.println("You have entered the following pile sizes");
            for (int pile : pileSizes) System.out.println(pile);
        }

        boolean playAnother = true;
        while (playAnother){
            Game game = new Game(pileSizes, debugMode);
            int loser = game.play();
            System.out.println("\n\n\nPlayer " + loser + " Loses! :(");
            playAnother = askPlayAnother();
        }
        System.out.println("\nThanks for playing!");
    }

    /*
    Ask the user if they would like to play another game
     */
    public static boolean askPlayAnother(){
        boolean playAnother = Game.userYesOrNo("Good Game! Would you like to play another?");
        if (!playAnother) return false;
        boolean changePiles = Game.userYesOrNo("Would you like to change the pile sizes?");
        if (changePiles) pileSizes = getInfo();
        return true;
    }

    /*
    Provides the user interface to set up the game, returning the parsed input as the pile sizes
     */
    public static int[] getInfo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the pile sizes you would like to play with, separated by commas:");
        String input = scan.nextLine();
        return parsePileInput(input);
    }

    /*
    Parses a user input by splitting by any non-digits using Regular Expressions, then converts the String[] into int[]
     */
    public static int[] parsePileInput(String input){
        String[] pileStrings = input.split("\\D+");

        int[] pileSizes = new int[pileStrings.length];
        for (int i = 0; i < pileStrings.length; i++){
            pileSizes[i] = Integer.parseInt(pileStrings[i]);
        }

        return pileSizes;
    }
}
