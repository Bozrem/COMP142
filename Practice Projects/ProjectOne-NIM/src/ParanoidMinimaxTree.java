import java.util.ArrayList;
import java.util.List;

public class ParanoidMinimaxTree {
    Pile[] piles;
    Move moveFromParent;
    List<ParanoidMinimaxTree> subTrees = new ArrayList<>();
    private final int activePlayer;
    private final int computerID;


    ParanoidMinimaxTree(Pile[] piles, Move moveFromParent, int activePlayer, int computerID){
        this.piles = Pile.deepClonePiles(piles);
        this.moveFromParent = moveFromParent;
        this.activePlayer = activePlayer;
        this.computerID = computerID;

        if (Main.debugMode){
            System.out.println("\nInitialized new Movetree under player " + activePlayer);
            if (moveFromParent != null)
                System.out.println("Move was " + moveFromParent.getSticks() + " sticks from pile " + moveFromParent.getPile());
            Pile.printPiles(this.piles);
        }

        populateChildren();
    }

    private void populateChildren(){
        List<Move> availableMoves = Move.getAvailableMoves(Pile.deepClonePiles(piles));
        for (Move move : availableMoves){
            Pile[] pilesAfterMove = move.getPilesAfterMove(Pile.deepClonePiles(piles)); // Check what piles are after
            if (!Pile.areEmpty(pilesAfterMove)){
                // Only create a child if it doesn't lose
                subTrees.add(new ParanoidMinimaxTree(pilesAfterMove, move, getNextPlayer(), computerID));
            }
        }
    }

    public Move getMove(){
        if (subTrees.isEmpty()) return Move.getAvailableMoves(Pile.deepClonePiles(piles)).get(0); // Do losing move if lost
        int bestStrength = 0;
        Move bestMove = new Move(-1, -1); // Here so it's initialized
        for (ParanoidMinimaxTree child : subTrees){
            int childStrength = child.getStrength();
            if (isBetterStrength(bestStrength, childStrength)){
                bestStrength = childStrength; // Filter for the best move for the active player
                bestMove = child.moveFromParent;
            }
        }
        return bestMove;
    } // TODO Might break when it has to lose

    public int getStrength(){
        if (subTrees.isEmpty()){
            // Having no children means that this move doesn't have any other valid moves after, and is losing
            return strengthModifier(); // -1 if computer lost, 1 if other lost
        }
        int bestStrength = 0;
        for (ParanoidMinimaxTree child : subTrees){
            int childStrength = child.getStrength();
            if (isBetterStrength(bestStrength, childStrength)) bestStrength = childStrength; // Filter for the best move for the active player
        }
        if (Main.debugMode){
            System.out.println("Sent up " + incrementStrength(bestStrength) + " for the following piles");
            Pile.printPiles(piles);
        }
        return incrementStrength(bestStrength);
    }

    /*
    Increments a strength in the direction of its sign to maintain win or loss state for computer, while updating how many moves down it happens in
     */
    private int incrementStrength(int strength){
        if (strength < 0) return (strength-1);
        if (strength > 0) return (strength+1);
        return 0;
    }

    /*
    Key function of Minimax. Compares two children strength to see who is better for the player of this node
     */
    private boolean isBetterStrength(int currentBestStrength, int newStrength){
        if (Main.debugMode){
            System.out.println("\nCurrent strength: " + currentBestStrength);
            System.out.println("New strength: " + newStrength);
            System.out.println("Active player is " + activePlayer + " and computer player id is " + computerID);
        }
        if (currentBestStrength == 0) return true;
        if (activePlayer == computerID){
            if (newStrength > 0 && currentBestStrength < 0) return true; // New kills the opponent, old lost to it
            if (newStrength > 0 && currentBestStrength > 0 && newStrength < currentBestStrength) return true; // Both kill opponent (positive), new kills quicker (smaller number)
            if (newStrength < 0 && currentBestStrength < 0 && newStrength < currentBestStrength) return true; // Both are losing (negative), new delays longer (larger number)
            if (Main.debugMode) System.out.println("AI active, not better");
            // Computer has choice, wants to win or delay loss
            // Wants low positive or high negative (in that order)
            return false;
        }
        if (newStrength < 0 && currentBestStrength > 0) return true; // New kills the computer, old lost to it
        if (newStrength < 0 && currentBestStrength < 0 && newStrength > currentBestStrength) return true; // Both kill computer (negative), new does it quicker (smaller number)
        if (newStrength > 0 && currentBestStrength > 0 && newStrength > currentBestStrength) return true; // Both are losing (positive), new delays longer (larger number)
        if (Main.debugMode) System.out.println("Opponent active, not better");
        // Opponents choice, they want computer to lose
        // Wants low negative or high positive (in that order)
        return false;
    }

    /*
    Adds a positive or negative skew based on which player loses
     */
    private int strengthModifier(){
        if (activePlayer == computerID) return -1; // computer player lost
        return 1; // somebody else lost
    }

    /*
    For use of passing the next player down to child nodes
     */
    private int getNextPlayer(){
        if (activePlayer < Player.players.length - 1) {
            return (activePlayer +1);
        }
        return 0;
    }

    public Pile[] getPiles(){
        return piles;
    }
}
