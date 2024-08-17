public class Move {
    private final int pile;
    private final int sticks;

    Move(int pile, int sticks){
        this.pile = pile;
        this.sticks = sticks;
    }

    public boolean makeMove(){
        if (!isMoveViable(Pile.getPiles())) return false;
        Pile.getPiles()[pile].takeSticks(sticks);
        return true;
    }

    public Pile[] getPilesAfterMove(Pile[] piles){
        piles[pile].takeSticks(sticks);
        return piles;
    }

    public static Move[] getAvailableMoves(Pile[] piles){
        // TODO
        return null;
    }

    public boolean isMoveViable(Pile[] piles){
        // TODO
        return true;
    }
}
