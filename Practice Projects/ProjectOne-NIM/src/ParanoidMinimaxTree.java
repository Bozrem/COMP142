import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ParanoidMinimaxTree {
    private Pile[] piles;
    Move moveFromParent;
    List<ParanoidMinimaxTree> subTrees = new ArrayList<>();
    public final int activePlayer;
    public final int computerID;
    public static int totalNodes = 0;
    public final int treeID;
    public int strength;
    public static Hashtable<Integer, ParanoidMinimaxTree> index = new Hashtable<>();


    ParanoidMinimaxTree(Pile[] piles, Move moveFromParent, int activePlayer, int computerID, int parentID){
        this.piles = Pile.deepClonePiles(piles);
        this.moveFromParent = moveFromParent;
        this.activePlayer = activePlayer;
        this.computerID = computerID;
        treeID = totalNodes + 1;
        totalNodes++;
        index.put(treeID, this);

        if (Main.debugMode){
            System.out.println("\nInitialized new Movetree under player " + activePlayer + " from Parent node " + parentID);
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

            boolean arePilesDuplicates = false;
            for (ParanoidMinimaxTree child : subTrees){
                if (Pile.equalPiles(child.getPiles(), pilesAfterMove)){
                    arePilesDuplicates = true;
                    break;
                }
            }

            subTrees.add(new ParanoidMinimaxTree(pilesAfterMove, move, getNextPlayer(), computerID, treeID));

            if (!arePilesDuplicates){
                // Only create a child if some variation of the piles does not already exist
                subTrees.add(new ParanoidMinimaxTree(pilesAfterMove, move, getNextPlayer(), computerID, treeID));
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
        System.out.println("Computer moves towards best strength of " + bestStrength);
        return bestMove;
    }

    public int getStrength(){
        if (strength != 0) return strength;
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
        strength = incrementStrength(bestStrength);
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
        if (activePlayer == computerID) return 1; // Empty piles given to computer, opponent before them lost
        return -1; // Computer lost on the round before
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
