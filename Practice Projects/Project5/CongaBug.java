package Project5;

public class CongaBug extends DancingBug implements LeaderBug{
    private DanceStep lastMove;
    private DanceStep nextMove;
    LeaderBug leaderBug;

    CongaBug(int row, int col, LeaderBug leaderBug){
        super(row, col, "green");
        this.leaderBug = leaderBug;
        nextMove = leaderBug.getLastStep();
        lastMove = leaderBug.getLastStep();
    }

    @Override
    public DanceStep getLastStep() {
        return lastMove;
    }

    @Override
    public void step() {
        doStep(nextMove);
        lastMove = nextMove;
        nextMove = leaderBug.getLastStep();
    }
}
