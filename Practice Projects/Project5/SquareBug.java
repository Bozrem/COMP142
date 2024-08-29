package Project5;

import java.util.ArrayList;

public class SquareBug extends RoutineBug {
    private int size;
    SquareBug(int row, int col, int size){
        super(row, col);
        this.size = size;
        this.setColor("orange");

        buildRoutine(size);
    }

    @Override
    public void setRoutine(ArrayList<DanceStep> steps) {
        System.out.println("Cannot change Square Bug routine");
    }

    private void buildRoutine(int size){
        this.routine = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            for (int e = 0; e < size; e++){
                routine.add(DanceStep.FORWARD);
            }
            routine.add(DanceStep.TURN_RIGHT);
        }
    }

    public void setSize(int size) {
        this.size = size;
        buildRoutine(size);
    }
}
