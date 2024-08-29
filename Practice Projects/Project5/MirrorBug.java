package Project5;

public class MirrorBug extends DancingBug{

    LeaderBug leaderBug;

    public MirrorBug(int row, int col, LeaderBug leaderBug){
        super(row, col, "blue");
        this.leaderBug = leaderBug;
    }

    @Override
    public void step() {
        DanceStep lastStep = leaderBug.getLastStep();
        if (lastStep.equals(DanceStep.TURN_LEFT)) {
            doStep(DanceStep.TURN_RIGHT);
            return;
        }
        if (lastStep.equals(DanceStep.TURN_RIGHT)){
            doStep(DanceStep.TURN_LEFT);
            return;
        }
        doStep(lastStep);
    }
}

