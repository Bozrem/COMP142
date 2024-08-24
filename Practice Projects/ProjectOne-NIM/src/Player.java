import com.sun.source.tree.Tree;

import java.util.Scanner;

public class Player {
    public int playerID;
    public static Player[] players;
    private static int activePlayer = -1; // advances to player 0 on first turn

    // TODO cool score assessment thing using how good its moves were on the tree

    Player(int playerID){
        this.playerID = playerID;
    }

    /*
       Print out piles, get the move from the player, and make it if its valid
       Parameters: piles, the current piles that the user is making a move off of
       Returns: void
    */
    public void makeMove(Pile[] piles) {
        printGame(piles, this);
        Move move = getMove(piles);
        if (!move.makeMove(piles)) System.out.println("Move failed to execute. Was not viable");
    }

    /*
       Prompts the user for a move, and checks that it is viable
       Parameters: piles, to check if the user move is viable
       Returns: Move, the move the user wants to make
    */
    public Move getMove(Pile[] piles) {
        Scanner scan = new Scanner(System.in);
        Move move = new Move(-1, -1, this);

        while (!move.isMoveViable(piles)) {
            System.out.print("Pile to take from: ");
            int pile = scan.nextInt();
            System.out.print("Amount of sticks you want to take: ");
            int sticks = scan.nextInt();
            move = new Move(pile, sticks, this);
        }

        return move;
    }

    /*
       Prints out the current game for the user to make a decision from
       Parameters: piles, the piles to display to the user
       Returns: void
    */
    public void printGame(Pile[] piles, Player activePlayer) {
        System.out.println("\n\n\n"); // To space it nicely
        System.out.println("It is Player " + (activePlayer.playerID) + "'s turn");
        System.out.println("Current Board:");
        Pile.printPiles(piles);
    }

    /*
       Get player whose turn it is
       Parameters:
       Returns: Player, the player whose turn it is
    */
    public static Player getActivePlayer() {
        return players[activePlayer];
    }

    /*
       Get player whose turn it is as a number
       Parameters: self
       Returns: int, the player whose turn it is, as a number
    */
    public static int getActivePlayerNumber() {
        return activePlayer;
    }

    /*
       Populates the player objects
       Parameters: amount of human and computer players
       Returns: void
    */
    public static void initializePlayers(int humans, int computers) {
        players = new Player[humans + computers];
        // TODO make these switch back and forth
        for (int i = 0; i < humans; i++) {
            players[i] = new Player(i);
            players[i].playerID = i;
        }
        for (int i = humans; i < humans + computers; i++) {
            players[i] = new Computer(i);
            players[i].playerID = i;
        }
    }

    /*
       Populates the player objects with computers moving first
       Parameters: amount of human and computer players
       Returns: void
    */
    public static void initializePlayersReverse(int humans, int computers) {
        players = new Player[humans + computers];
        // TODO make these switch back and forth
        for (int i = 0; i < computers; i++) {
            players[i] = new Computer(i);
            players[i].playerID = i;
        }
        for (int i = humans; i < humans + computers; i++) {
            players[i] = new Player(i);
            players[i].playerID = i;
        }
    }

    /*
    Go to the next players turn, reset to the first if at the end
     */
    public static void advanceTurn() {
        if (activePlayer < players.length - 1) {
            activePlayer++;
            return;
        }
        activePlayer = 0;
    }

    public static Player getNextPlayer(Player activePlayer, Player[] players){
        for (int i = 0; i < players.length; i++){
            if (activePlayer.equals(players[i])){
                if (i == players.length - 1) return players[0];
                return players[i + 1];
            }
        }

        try{
            throw new Exception("Could not find the active player in players list");
        }
        catch(Exception e){
            System.out.println("Could not find the active player in players list");
            return new Player(-1);
        }
    }

    /*
    Creates a clone of a Player[] array with cloned players
     */
    public static Player[] deepClone(Player[] players){
        Player[] newPlayers = new Player[players.length];
        for (int i = 0; i < players.length; i++){
            newPlayers[i] = new Player(players[i].playerID);
        }
        return newPlayers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Player.class) return false; // Cannot be casted to Player
        Player otherPlayer = (Player) obj;
        return (otherPlayer.playerID == playerID);
    }
}
