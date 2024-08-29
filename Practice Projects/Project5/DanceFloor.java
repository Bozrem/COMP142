package Project5;

import java.awt.*;
import java.util.ArrayList;

/** A DanceFloor is where the bugs dance.  It's basically a 2-d grid,
 * though the grid is not explicitly represented; instead, we store
 * an ArrayList of Bugs and each bug remembers its own location.
 */
public class DanceFloor {
    private SimpleCanvas canvas;
    private ArrayList<DancingBug> bugs;
    private boolean isWindowShowing;
    private int pauseTime;
    private int rows, cols;

    private static int SQUARESIZE = 40;

    public DanceFloor(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        canvas = new SimpleCanvas(cols * SQUARESIZE, rows * SQUARESIZE);
        bugs = new ArrayList<DancingBug>();
        isWindowShowing = false;
        pauseTime = 542; // picked to coincide with video, but can be changed
    }

    /** Add a dancing bug to the floor. */
    public void addDancer(DancingBug bug) {
        bugs.add(bug);
    }

    /** Remove a dancing bug from the floor. */
    public void removeDancer(DancingBug bug) { bugs.remove(bug); }

    /**
     * Tells all the dancers to take a step.
     */
    public void stepAll() {
        // make a copy of the arraylist in case a bug is deleted from the list
        // while we are iterating through it (Java doesn't like this).
        ArrayList<DancingBug> temporaryBugs = new ArrayList<DancingBug>(bugs);
        for (DancingBug bug : temporaryBugs) {
            bug.step();
        }
    }

    /** Draw the dance floor. */
    public void draw() {
        canvas.clear();
        canvas.setPenColor(Color.GRAY);
        for (int row = 0; row < rows; row++) {
            canvas.drawLine(0, row * SQUARESIZE, cols * SQUARESIZE, row * SQUARESIZE);
        }
        for (int col = 0; col < cols; col++) {
            canvas.drawLine(col * SQUARESIZE, 0, col * SQUARESIZE, rows * SQUARESIZE);
        }

        for (DancingBug bug : bugs) {
            int row = bug.getLocationRow();
            int col = bug.getLocationCol();
            canvas.drawImage(col * SQUARESIZE, row * SQUARESIZE, bug.getImageFilename(), SQUARESIZE, SQUARESIZE);
        }

        if (isWindowShowing) {
            canvas.update();
        } else {
            canvas.show();
            isWindowShowing = true;
        }
    }

    /** Have everyone dance! */
    public void everyoneDance() {
        draw();
        canvas.waitForClick();
        while (true)
        {
            stepAll();
            draw();
            canvas.pause(pauseTime);
        }
    }
}
