import java.util.ArrayList;
import java.util.List;

public class Move {
    private final int pile;
    private final int sticks;

    Move(int pile, int sticks){
        this.pile = pile;
        this.sticks = sticks;
    }

    public boolean makeMove(Pile[] piles){
        if (!isMoveViable(piles)) return false;
        piles[pile].takeSticks(sticks);
        return true;
    }

    public Pile[] getPilesAfterMove(Pile[] piles){
        Pile[] clonedPiles = Pile.deepClonePiles(piles);
        clonedPiles[pile].takeSticks(sticks);
        return clonedPiles;
    }

    /*
    Returns all moves available for an array of Piles
     */
    public static List<Move> getAvailableMoves(Pile[] piles){
        List<Move> availableMoves = new ArrayList<>();
        for (int pile = 0; pile < piles.length; pile++){
            for (int sticks = 1; sticks <= piles[pile].getCount(); sticks++){
                availableMoves.add(new Move(pile, sticks));
            }
        }
        return availableMoves;
        // TODO make this remove duplicates for optimization
    }

    /*
    Check if a move can be done for an array of Piles
     */
    public boolean isMoveViable(Pile[] piles){
        if (pile < 0 || sticks < 1) return false; // Below lower limit
        if (pile >= piles.length) return false; // Pile doesn't exist
        if (piles[pile].getCount() < sticks) return false; // Stick request is more than is in pile
        return true;
    }

    public int getPile(){
        return pile;
    }
    public int getSticks(){
        return sticks;
    }

    @Override
    public String toString() {
        String string = "Take ";
        string += sticks;
        string += " from Pile ";
        string += pile;
        return string;
    }
}
