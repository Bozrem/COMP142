package Project4;

import java.awt.*;
import java.io.InputStream;
import java.util.Scanner;

public class Maze {
    private SimpleCanvas canvas; // the canvas the maze will be drawn on.
    int[][] maze;
    int startX;
    int startY;
    int rows;
    int columns;
    int patronusSpeed;
    private int solverCalls;

    // Declare your instance variables here.

    public Maze(String file, int patronusSpeed) { // you can add any parameters you want to the constructor
        this.patronusSpeed = patronusSpeed;
        solverCalls = 0;
        loadMaze(file);
        canvas = new SimpleCanvas(columns * 30, rows * 30); // Canvas is switched from int[][]
        canvas.show();
    }

    /** Load a maze from the given text file and store into appropriate
     * instance variables.
     */
    private void loadMaze(String filename) {
        InputStream is = Maze.class.getResourceAsStream(filename);
        if (is == null) {
            System.err.println("Could not open file: " + filename);
            System.err.println("Spells are finicky things Mr. Potter sir, should have chosen your words more carefully...");
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        this.rows = scan.nextInt();
        this.columns = scan.nextInt();

        scan.nextLine(); // skip the enter keypress

        maze = new int[rows][columns];

        int countRow = 0;
        while (scan.hasNextLine()) {
            String str = scan.nextLine();
            for (int i = 0; i < str.length(); i++){
                char unit = str.charAt(i);
                if (unit == ' ') maze[countRow][i] = 0; // Unvisited
                if (unit == '#') maze[countRow][i] = -1; // Hedge
                if (unit == 'C') maze[countRow][i] = 3; // Cup
                if (unit == 'H'){
                    startX = countRow;
                    startY = i;
                    maze[countRow][i] = 2; // Harry
                }
            }
            countRow++;
        }
    }

    /** Draw the maze on the canvas. */
    public void drawMaze() {
        canvas.clear();

        for (int i = 0; i < maze.length; i++){

            for (int e = 0; e < maze[i].length; e++){
                int mazeValue = maze[i][e];

                if (mazeValue == -1) canvas.setPenColor(Color.DARK_GRAY); // Wall
                if (mazeValue == 3) canvas.setPenColor(Color.orange); // Cup
                if (mazeValue == 2) canvas.setPenColor(Color.green); // Start
                if (mazeValue == 0) canvas.setPenColor(Color.white); // Space
                if (mazeValue == 1) canvas.setPenColor(Color.lightGray);

                canvas.drawFilledRectangle(e*30, i*30,30, 30);
            }
        }

        canvas.update();
    }

    /** Draw the maze on the canvas, including the current position of the patronus. */
    public void drawMazeWithPatronus(int patronusRow, int patronusCol) {
        drawMaze();
        canvas.setPenColor(Color.CYAN);
        canvas.drawFilledCircle(patronusCol * 30 + 15, patronusRow * 30 + 15, 7);

        canvas.update();
    }

    /** Initial function to get the recursion started for recursive formulation 1:
     * Is it possible to solve this maze?
     */
    public boolean canSolve() {
        drawMaze();
        canvas.waitForClick();

        return canSolve(startX, startY);
    }

    /** Helper function for canSolve that takes the current position of the patronus
     * as parameters.
     */
    private boolean canSolve(int patronusRow, int patronusCol) {
        solverCalls++;
        if (patronusCol < 0 || patronusRow < 0) return false; // Out of range
        if (maze[patronusRow][patronusCol] == -1 || maze[patronusRow][patronusCol] == 1) return false; // Hedge or already visited
        System.out.println("Patronus arrived at row " + patronusRow + ", column " + patronusCol);
        drawMazeWithPatronus(patronusRow, patronusCol);
        canvas.pause(patronusSpeed);


        if (maze[patronusRow][patronusCol] == 3) {
            System.out.println("Found cup");
            return true;
        } // Base case that it was found

        maze[patronusRow][patronusCol] = 1;

        if (canSolve(patronusRow - 1, patronusCol)) return true;
        if (canSolve(patronusRow + 1, patronusCol)) return true;
        if (canSolve(patronusRow, patronusCol - 1)) return true;
        if (canSolve(patronusRow, patronusCol + 1)) return true;


        System.out.println("Patronus backtracking");
        maze[patronusRow][patronusCol] = 0;
        return false;
        // Check other directions
        // If all are false:
        // set square back to 0 (unvisited)
        // return false
    }

    /** Initial function to get the recursion started for recursive formulation 2:
     * What are the directions (sequence of N/S/E/W steps) to solve the maze?
     */
    public String directionalSolve() {
        drawMaze();
        canvas.waitForClick();

        return directionalSolve(startX, startY);
    }

    /** Helper function for directionalSolve(). */
    private String directionalSolve(int patronusRow, int patronusCol) {
        solverCalls++;
        if (patronusCol < 0 || patronusRow < 0) return "F"; // Out of range
        if (maze[patronusRow][patronusCol] == -1 || maze[patronusRow][patronusCol] == 1) return "F"; // Hedge or already visited
        //System.out.println("Patronus arrived at row " + patronusRow + ", column " + patronusCol);
        drawMazeWithPatronus(patronusRow, patronusCol);
        canvas.pause(patronusSpeed);


        if (maze[patronusRow][patronusCol] == 3) {
            //System.out.println("Found cup");
            return "C";
        } // Base case that it was found

        maze[patronusRow][patronusCol] = 1;

        String northPath = directionalSolve(patronusRow - 1, patronusCol);
        if (isPathWinning(northPath)) return "N" + northPath;
        String southPath = directionalSolve(patronusRow + 1, patronusCol);
        if (isPathWinning(southPath)) return "S" + southPath;
        String eastPath = directionalSolve(patronusRow, patronusCol - 1);
        if (isPathWinning(eastPath)) return "E" + eastPath;
        String westPath = directionalSolve(patronusRow, patronusCol + 1);
        if (isPathWinning(westPath)) return "W" + westPath;


        //System.out.println("Patronus backtracking");
        maze[patronusRow][patronusCol] = 0;
        // Check other directions
        // If all are false:
        // set square back to 0 (unvisited)
        // return false
        return "F";
    }

    boolean isPathWinning(String path){
        return path.endsWith("C");
    }

    public void printMaze(){
        for (int i = 0; i < maze.length; i++){
            System.out.println();
            for (int e = 0; e < maze[i].length; e++){
                System.out.print(maze[i][e] + " ");
            }
        }
    }

    public int getSolverCalls() {
        return solverCalls;
    }
}


