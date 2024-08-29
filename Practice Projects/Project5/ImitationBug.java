package Project5;

public class ImitationBug extends DancingBug {
    LeaderBug leaderBug;

    public ImitationBug(int row, int col, LeaderBug leaderBug){
        super(row, col, "pink");
        this.leaderBug = leaderBug;
    }

    @Override
    public void step() {
        doStep(leaderBug.getLastStep());
    }
}
