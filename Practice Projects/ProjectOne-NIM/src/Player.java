import com.sun.source.tree.Tree;

import java.util.Scanner;

public class Player {
    public int playerID;

    // TODO cool score assessment thing using how good its moves were on the tree

    Player(int playerID){
        this.playerID = playerID;
    }

    /*
       Prompts the user for a move, and checks that it is viable
       Parameters: piles, to check if the user move is viable
       Returns: Move, the move the user wants to make
    */
    public Move getMove(GameObject game) {
        Scanner scan = new Scanner(System.in);
        Move move = new Move(-1, -1, this);

        while (!move.isMoveViable(game.getPiles())) {
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
       Populates the player objects
       Parameters: amount of human and computer players
       Returns: void
    */
    public static Player[] initializePlayers(int humans, int computers) {
        Player[] players = new Player[humans + computers];
        // TODO make these switch back and forth
        for (int i = 0; i < humans; i++) {
            players[i] = new Player(i);
            players[i].playerID = i;
        }
        for (int i = humans; i < humans + computers; i++) {
            players[i] = new Computer(i);
            players[i].playerID = i;
        }
        return players;
    } // TODO implement impossible mode

    public static Player getNextPlayer(Player activePlayer, Player[] players){
        if (activePlayer.playerID == players.length - 1){
            return players[0];
        }
        return players[activePlayer.playerID + 1];
    }

    /*
    Creates a clone of a Player[] array with cloned players
     */
    public static Player[] deepClone(Player[] players){
        Player[] newPlayers = new Player[players.length];
        for (int i = 0; i < players.length; i++){
            newPlayers[i] = players[i].clone();
        }
        return newPlayers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Player.class) return false; // Cannot be cast to Player
        Player otherPlayer = (Player) obj;
        return (otherPlayer.playerID == playerID);
    }


    public static String playersToString(Player[] players){
        StringBuilder builder = new StringBuilder("Players:\n");
        for (Player player : players){
            builder.append(player.getClass())
                    .append(" ")
                    .append(player.playerID)
                    .append(", ");
        }
        return builder.toString();
    }

    public Player clone(){
        return new Player(this.playerID);
    }

    public String getPlayerType(){
        return "Player";
    }
}
