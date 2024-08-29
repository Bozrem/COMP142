package Project5;

/**
 * A DancingBug is a class that knows its row & column in a grid,
 * what direction its facing, and its color.  Note that colors are
 * Strings in this project, not objects of the Color class.
 */
public abstract class DancingBug {
    private int locationRow, locationCol;
    private Direction directionFacing;
    private String color;

    /** Default constructor. */
    public DancingBug() {
        locationRow = 0;
        locationCol = 0;
        color = "red";
        directionFacing = Direction.NORTH;
    }

    /** Constructor that lets you set row/col/color. */
    public DancingBug(int row, int col, String newcolor) {
        locationRow = row;
        locationCol = col;
        color = newcolor;
        directionFacing = Direction.NORTH;
    }

    /** Constructor that lets you set row/col/color/direction. */
    public DancingBug(int row, int col, String newcolor, Direction newDir) {
        locationRow = row;
        locationCol = col;
        color = newcolor;
        directionFacing = newDir;
    }

    /** Lets the bug define the next step of the dance.  This function
     * will be called by the DanceFloor object whenever the bug needs
     * to take the next dance step.
     */
    public abstract void step();

    /** This function is usually called by step() to actually move or turn the
     * bug for a dance step.  Sometimes using this is easier than calling
     * moveForward()/moveBackward()/etc as separate functions.
     */
    protected void doStep(DanceStep step) {
        if (step == DanceStep.FORWARD) {
            moveForward();
        } else if (step == DanceStep.BACKWARD) {
            moveBackward();
        } else if (step == DanceStep.TURN_RIGHT) {
            turnRight();
        } else if (step == DanceStep.TURN_LEFT) {
            turnLeft();
        } else if (step == DanceStep.STEP_RIGHT) {
            stepRight();
        } else if (step == DanceStep.STEP_LEFT) {
            stepLeft();
        } else if (step == DanceStep.PAUSE) {
        }
    }

    public int getLocationRow() {
        return locationRow;
    }

    public void setLocationRow(int newRow) {
        locationRow = newRow;
    }

    public int getLocationCol() {
        return locationCol;
    }

    public void setLocationCol(int newCol) {
        locationCol = newCol;
    }

    public Direction getDirectionFacing() {
        return directionFacing;
    }

    public void setDirectionFacing(Direction newDir) {
        directionFacing = newDir;
    }

    public String getColor() {
        return color;
    }
    
    public void setColor(String newColor) {
        color = newColor;
    }

    public String getImageFilename() {
        return "bug-" + getColor() + "-" + directionFacing.getAbbreviation() + ".png";
    }

    protected void turnRight() {
        if (this.directionFacing == Direction.NORTH)
            this.directionFacing = Direction.EAST;
        else if (this.directionFacing == Direction.EAST)
            this.directionFacing = Direction.SOUTH;
        else if (this.directionFacing == Direction.SOUTH)
            this.directionFacing = Direction.WEST;
        else if (this.directionFacing == Direction.WEST)
            this.directionFacing = Direction.NORTH;
    }

    protected void turnLeft() {
        if (this.directionFacing == Direction.NORTH)
            this.directionFacing = Direction.WEST;
        else if (this.directionFacing == Direction.EAST)
            this.directionFacing = Direction.NORTH;
        else if (this.directionFacing == Direction.SOUTH)
            this.directionFacing = Direction.EAST;
        else if (this.directionFacing == Direction.WEST)
            this.directionFacing = Direction.SOUTH;
    }

    protected void moveForward() {
        locationRow += directionFacing.getRowChange();
        locationCol += directionFacing.getColChange();
    }

    protected void moveBackward() {
        locationRow -= directionFacing.getRowChange();
        locationCol -= directionFacing.getColChange();
    }

    protected void stepRight() {
        locationRow += directionFacing.getColChange();
        locationCol -= directionFacing.getRowChange();
    }

    protected void stepLeft() {
        locationRow -= directionFacing.getColChange();
        locationCol += directionFacing.getRowChange();
    }

    public String toString() {
        return getClass() + ", location=" + locationRow + "," + locationCol + ", color=" + color + ", dir=" + directionFacing;
    }
}
