/*
    Name: Remy Bozung
    Date: 8/10/2024
    Class: CS142
    Pledge: I have neither given nor received unauthorized aid on this program.
    Description: A CLI game of Nim, with some modifications and computer opponents. See README.md for more details
*/

import java.util.Scanner;

public class Main {
    public static boolean debugMode = false;

    public static void main(String[] args) {
        if (args.length > 0) debugMode = Boolean.parseBoolean(args[0]);

        /*
        Future TODO:
        Unittests
        Fix bug with play another game
        // TODO needs to reset active player to 0 on game restart
        Add AI difficulty selector
            Impossible
                Computer moves first
            Impossible evil
                Computer moves first, prolongs your loss
            Winnable
                You go first, computer is still perfect
            Winnable evil
                You go first, but if you screw up it prolongs your loss
            Random
                Computer plays random moves
        Find way to allow AI to gang up on a player
         */

        System.out.println("Welcome to NeoNim!\n");

        getPlayers();
        Pile[] piles = Pile.fromIntArray(Pile.fromUserInput());

        startGameLoop(piles);

        System.out.println("\nThanks for playing!");
    }

    /*
        This function controls the game loop, beginning a game and calling a function that can call this again
        Parameters: piles, the piles to play with for the game being created
        Returns: void
     */
    public static void startGameLoop(Pile[] piles) {
        Game.playNewGame(Pile.deepClone(piles));
        System.out.println("\nPlayer " + (Player.getActivePlayerNumber() + 1) + " Loses!\n\n");
        askPlayAnother(piles);
        // TODO switch to GameObject
    }

    /*
        This function asks the user if they would like to play another game after the first one ends, and gets new game info if the user wants to change it. Can call startGameLoop
        Parameters: piles, the piles to play with for the game being created
        Returns: void
     */
    public static void askPlayAnother(Pile[] piles) {
        boolean playAnother = Game.userYesOrNo("Good Game! Would you like to play another?");
        if (!playAnother) return;
        boolean changePiles = Game.userYesOrNo("Would you like to change the pile sizes?");
        if (changePiles) {
            piles = Pile.fromIntArray(Pile.fromUserInput());
        }
        boolean changePlayers = Game.userYesOrNo("Would you like to change the amount of players?");
        if (changePlayers) getPlayers();
        startGameLoop(piles);
    }

    /*
        Gets the players playing the game from the user, sets them in the Player class
        Parameters:
        Returns:
     */
    public static void getPlayers() {
        Scanner scan = new Scanner(System.in);
        System.out.println("NeoNim allows for a wide variety of player and pile options!");
        System.out.println("You can play both with other people and against computers.");
        System.out.println("Try 1 human 1 computer for a more classic game.\n");
        int humans = -1;
        int computers = -1;
        while (humans < 0 || computers < 0 || computers + humans < 2) {
            System.out.print("Amount of human players: ");
            humans = scan.nextInt();
            System.out.print("Amount of computer opponents: ");
            computers = scan.nextInt();
        }
        System.out.println(); // To space out before pile sizes
        Player.initializePlayers(humans, computers);
    }
}
