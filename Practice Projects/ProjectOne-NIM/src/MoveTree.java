import java.util.*;

public class MoveTree {
    public final Move moveFromParent;
    private List<MoveTree> subTrees = new ArrayList<>();
    private final Pile[] parentPiles;
    private final Pile[] piles;
    private final int player;
    private final int depth;

    MoveTree(Pile[] parentPiles, int player, Move moveFromParent, int depth){
        this.moveFromParent = moveFromParent;
        this.player = player;
        this.parentPiles = deepClonePiles(parentPiles); // Deep clone here
        if (moveFromParent != null)
            piles = moveFromParent.getPilesAfterMove(this.parentPiles);
        else
            piles = this.parentPiles.clone();
        this.depth = depth;

        if (Main.debugMode){
            System.out.println("Initialized new Movetree under player " + player + " at depth " + depth);
            if (moveFromParent != null)
                System.out.println("Move was " + moveFromParent.getSticks() + " sticks from pile " + moveFromParent.getPile());
            Pile.printPiles(this.piles);
        }
        populateChildren();
    }

    private Pile[] deepClonePiles(Pile[] originalPiles) {
        Pile[] clone = new Pile[originalPiles.length];
        for (int i = 0; i < originalPiles.length; i++) {
            clone[i] = new Pile(originalPiles[i].getCount()); // Assuming Pile has a constructor that takes the count
        }
        return clone;
    }


    private void populateChildren(){
        List<Move> availableMoves = Move.getAvailableMoves(piles.clone());
        for (Move move : availableMoves){
            MoveTree newChild = new MoveTree(piles, getNextPlayer(), move, depth+1);
            subTrees.add(newChild);
        }
    }

    public Move getMove(){
        float leastLossChance = 2;
        MoveTree bestChild = subTrees.get(0);
        for (MoveTree child : subTrees){
            if (Main.debugMode){
                System.out.println();
                System.out.println("Old: " + bestChild.moveFromParent.getSticks() + " sticks from pile " + bestChild.moveFromParent.getPile());
                System.out.println("Old next piles " + Pile.pilesToString(bestChild.piles.clone()));
                System.out.println("Old Loss array: " + printLossArray(bestChild.getLosses()));
                System.out.println("New: " + child.moveFromParent.getSticks() + " sticks from pile " + child.moveFromParent.getPile());
                System.out.println("New next piles " + Pile.pilesToString(child.piles.clone()));
                System.out.println("New Loss array: " + printLossArray(child.getLosses()));
            }
            float childChance = chanceFromLossArray(child.getLosses());
            if (childChance < leastLossChance){
                leastLossChance = childChance;
                bestChild = child;
            }
        }
        return bestChild.moveFromParent;
    }

    // TODO remove temp function
    private static String printLossArray(int[] losses){
        String lossString = "";
        for (int loss : losses){
            lossString += (loss + ", ");
        }
        return lossString;
    }

    private float chanceFromLossArray(int[] losses){
        int totalLosses = 0;
        for (int playerLosses : losses) totalLosses += playerLosses;
        return (float)losses[player] / totalLosses;
    }

    /*
    For use of passing the next player down to child nodes
     */
    private int getNextPlayer(){
        if (player < Player.players.length - 1) {
            return (player+1);
        }
        return 0;
    }

    /*
    For the parent to determine viability of given path. Of all scenarios stemming from this, how many times does each player lose
     */
    public int[] getLosses(){
        int[] losses = new int[Player.players.length];

        if (isLoss()){
            losses[player] = 1;
            return losses;
        } // Base case

        for (MoveTree child : subTrees) {
            int[] childLosses = child.getLosses();
            for (int i = 0; i < losses.length; i++) {
                losses[i] += childLosses[i];
            } // Add each loss from the child's array into this one
        }
        return losses;
    }

    /*
    To calculate if this node is loss (base case)
     */
    private boolean isLoss(){
        int total = 0;
        for (Pile pile : piles) total += pile.getCount();
        return total == 1;
    }

    public void printChildren(){
        // TODO add functionality to print a trees children
    }

    public Pile[] getPiles(){
        return piles;
    }
}
