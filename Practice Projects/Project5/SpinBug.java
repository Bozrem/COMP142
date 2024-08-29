package Project5;

public class SpinBug extends DancingBug implements LeaderBug{
    SpinBug(int row, int col){ super(row, col, "yellow");}

    @Override
    public void step() {
        this.turnRight();
    }

    @Override
    public DanceStep getLastStep() {
        return DanceStep.TURN_RIGHT;
    }
}
