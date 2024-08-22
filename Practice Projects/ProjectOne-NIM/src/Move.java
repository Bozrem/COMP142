import java.util.ArrayList;
import java.util.List;

public class Move {
    private final int pile;
    private final int sticks;

    /*
       Initializes a new move
       Parameters: pile, the pile to take from,
            sticks, the amount of sticks to take
    */
    Move(int pile, int sticks) {
        this.pile = pile;
        this.sticks = sticks;
    }

    /*
       Executes the move for an array of piles
       Parameters: piles, the array of pile objects to execute the move on
       Returns: boolean, was the move able to be made
    */
    public boolean makeMove(Pile[] piles) {
        if (!isMoveViable(piles)) return false;
        piles[pile].takeSticks(sticks);
        return true;
    }

    /*
       Gets what the piles would look like after a move is done
       Parameters: piles, the piles to modify
       Returns: Pile[], the piles after the move
    */
    public Pile[] getPilesAfterMove(Pile[] piles) {
        Pile[] clonedPiles = Pile.deepClonePiles(piles);
        clonedPiles[pile].takeSticks(sticks);
        return clonedPiles;
    }

    /*
       Gets all available moves for an array of Piles
       Parameters: piles, the array to get moves from
       Returns: List<Move>, the list of available moves from the given piles
    */
    public static List<Move> getAvailableMoves(Pile[] piles) {
        List<Move> availableMoves = new ArrayList<>();
        for (int pile = 0; pile < piles.length; pile++) {
            for (int sticks = 1; sticks <= piles[pile].getCount(); sticks++) {
                availableMoves.add(new Move(pile, sticks));
            }
        }
        return availableMoves;
        // TODO make this remove duplicates for optimization
    }

    /*
       Checks if a move is viable for a set of piles
       Parameters: piles, the piles to check if a move works for
       Returns: boolean, does the move work
    */
    public boolean isMoveViable(Pile[] piles) {
        if (pile < 0 || sticks < 1) return false; // Below lower limit
        if (pile >= piles.length) return false; // Pile doesn't exist
        if (piles[pile].getCount() < sticks) return false; // Stick request is more than is in pile
        return true;
    }

    /*
       Getter method for the pile
       Returns: int, the pile in the move
    */
    public int getPile() {
        return pile;
    }

    /*
       Getter method for the sticks
       Returns: int, the sticks for this move
    */
    public int getSticks() {
        return sticks;
    }

    /*
       Gets the string version of a move
       Parameters:
       Returns: String, the string of the move
    */
    @Override
    public String toString() {
        String string = "Take ";
        string += sticks;
        string += " from Pile ";
        string += pile;
        return string;
    }
}
