package Project5;

/** Defines an enumerated type (like a class) for Directions.  There
 * are only 4 possible directions: NORTH, SOUTH, EAST, WEST.
 */

public enum Direction {
    NORTH(-1, 0, 'N'),
    EAST(0, 1, 'E'),
    SOUTH(1, 0, 'S'),
    WEST(0, -1, 'W');

    private int rowChange, colChange;
    private char abbreviation;

    Direction(int rChange, int cChange, char abbrev) {
        rowChange = rChange;
        colChange = cChange;
        abbreviation = abbrev;
    }

    public int getRowChange() { return rowChange; }
    public int getColChange() { return colChange; }
    public char getAbbreviation() { return abbreviation; }

}
