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
        Research Computer mutual interest
         */

        System.out.println("Welcome to NeoNim!\n");

        Player[] players = getPlayers();
        Pile[] piles = Pile.fromUserInput();
        startGameLoop(players, piles);

        System.out.println("\nThanks for playing!");
    }

    /*
        This function controls the game loop, beginning a game and calling a function that can call this again
        Parameters: piles, the piles to play with for the game being created
        Returns: void
     */
    public static void startGameLoop(Player[] players, Pile[] piles) {
        GameObject game = new GameObject(players, piles);
        Player loser = game.playGame();
        System.out.println("\n" + loser.getPlayerType() + " " + loser.playerID + " Loses!\n\n");
        askPlayAnother(game);
    } // TODO find a way that deletes the old instance of this running when a new one is made

    /*
        This function asks the user if they would like to play another game after the first one ends, and gets new game info if the user wants to change it. Can call startGameLoop
        Parameters: piles, the piles to play with for the game being created
        Returns: void
     */
    public static void askPlayAnother(GameObject oldGame) {
        boolean playAnother = GameObject.getUserBoolean("Good Game! Would you like to play another?");
        if (!playAnother) return;
        Pile[] piles = oldGame.getInitialPiles();
        Player[] players = oldGame.getPlayers();
        if (GameObject.getUserBoolean("Would you like to change the pile sizes?")) {
            piles = Pile.fromUserInput();
        }
        if (GameObject.getUserBoolean("Would you like to change the amount of players?")) {
            players = getPlayers();
        }
        startGameLoop(players, piles);
    }

    /*
        Gets the players playing the game from the user, sets them in the Player class
        Parameters:
        Returns:
     */
    public static Player[] getPlayers() {
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
        return Player.initializePlayers(humans, computers);
    }
}
