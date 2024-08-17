import java.util.HashSet;
import java.util.Set;

public class MoveTree {
    private Set<MoveTree> subTrees = new HashSet<>();
    private int[] piles;
    private final int player;

    MoveTree(int[] piles, int player){
        this.player = player;
        this.piles = piles;
        populateChildren();
    }

    private void populateChildren(){
        for (int pile = 0; pile < piles.length; pile++){
            for (int sticks = 1; sticks < piles[pile]; sticks++){
                int[] childPiles = piles.clone();
                childPiles[pile] = sticks;
                subTrees.add(new MoveTree(childPiles, simulateAdvanceTurn()));
            }
        }
    }

    /*
    For use of passing the next player down to child nodes
     */
    private int simulateAdvanceTurn(){
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
        for (int pile : piles) total += pile;
        return total == 1;
    }

    public void printChildren(){
        // TODO add functionality to print a trees children
    }


}
