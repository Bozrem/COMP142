package Project5;

import java.util.ArrayList;

public class RoutineBug extends DancingBug implements LeaderBug {
    protected ArrayList<DanceStep> routine;
    private int currentStep;

    RoutineBug(int row, int col) {
        super(row, col, "red");
        currentStep = 0;
    }

    @Override
    public void step() {
        if (currentStep >= routine.size()) currentStep = 0;
        this.doStep(routine.get(currentStep));
        currentStep++;
    }

    public void setRoutine(ArrayList<DanceStep> steps){
        this.routine = steps;
    }

    @Override
    public DanceStep getLastStep() {
        if (currentStep == 0) return routine.get(currentStep);
        return routine.get(currentStep - 1);
    }
}
