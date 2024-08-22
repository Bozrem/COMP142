import com.sun.source.tree.Tree;

import java.util.Scanner;

public class Player {
    public int playerID;
    public static Player[] players;
    private static int activePlayer = -1; // advances to player 0 on first turn

    /*
    Prints the game, gets a valid move from the user, and plays it
     */
    public void makeMove(Pile[] piles){
        printGame(piles);

        Move move = getUserMove(piles);
        if (!move.makeMove(piles)) System.out.println("Move failed to execute. Was not viable");
    }

    /*
    Gets user input for a move and verifies that it can be done
     */
    private Move getUserMove(Pile[] piles){
        Scanner scan = new Scanner(System.in);
        Move move = new Move(-1, -1);

        while (!move.isMoveViable(piles)){
            System.out.print("Pile to take from: ");
            int pile = scan.nextInt();
            System.out.print("Amount of sticks you want to take: ");
            int sticks = scan.nextInt();
            move = new Move(pile, sticks);
        }

        return move;
    }

    /*
    Print out game info for the player to make a decision
     */
    private void printGame(Pile[] piles){
        System.out.println("\n\n\n"); // To space it nicely
        System.out.println("It is Player " + (activePlayer + 1) + "'s turn");
        System.out.println("Current Board:");
        Pile.printPiles(piles);
    }

    /*
    Returns the player object of the player whose turn it is
     */
    public static Player getActivePlayer(){
        return players[activePlayer];
    }

    /*
    Returns the player number as an integer
     */
    public static int getActivePlayerNumber(){
        return activePlayer;
    }

    /*
    Initialize the players array with Player and Computer objects
     */
    public static void initializePlayers(int humans, int computers){
        players = new Player[humans + computers];
        // TODO make these switch back and forth
        for (int i = 0; i < humans; i++){
            players[i] = new Player();
            players[i].playerID = i;
        }
        for (int i = humans; i < humans + computers; i++){
            players[i] = new Computer();
            players[i].playerID = i;
        }
    }

    public static void initializePlayersReverse(int humans, int computers){
        players = new Player[humans + computers];
        // TODO make these switch back and forth
        for (int i = 0; i < computers; i++){
            players[i] = new Computer();
            players[i].playerID = i;
        }
        for (int i = humans; i < humans + computers; i++){
            players[i] = new Player();
            players[i].playerID = i;
        }
    }

    /*
    Go to the next players turn, reset to the first if at the end
     */
    public static void advanceTurn(){
        if (activePlayer < players.length - 1) {
            activePlayer++;
            return;
        }
        activePlayer = 0;
    }
}
