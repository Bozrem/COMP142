package Project4;// Write your header comment & pledge here.

import java.util.Scanner;

public class RunTournament {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nWhich maze are you stuck in Mr. Potter sir?");
        System.out.print("Maze filepath (e.g. maze5.txt): ");
        String mazePath = scan.nextLine();
        System.out.println("How speedy doth thou Patronus need to go (ms/move)");
        int patronusSpeed = scan.nextInt();
        scan.nextLine();
        System.out.println("Seeking to find if doomed or how thou shall livest? (Doom/Directions): ");
        String goal = scan.nextLine();

        if (goal.equalsIgnoreCase("doom")){
            Maze maze = new Maze(mazePath, patronusSpeed);
            boolean canSolve = maze.canSolve();
            System.out.println("Patronus checked " + maze.getSolverCalls() + " places.");
            if (canSolve) System.out.println("There is hope for you yet...");
            if (!canSolve) System.err.println("Bad news Mr. Potter sir...");
            return;
        }
        if (goal.equalsIgnoreCase("directions")){
            Maze maze = new Maze(mazePath, patronusSpeed);
            String directions = maze.directionalSolve();
            System.out.println("Patronus checked " + maze.getSolverCalls() + " places.");
            if (directions.equalsIgnoreCase("f")) {
                System.err.println("Bad news Mr. Potter sir...");
                return;
            }
            System.out.println("Listen carefully Mr. Potter. You need to go " + directions);
            System.out.println("Best of luck sir!");
            return;
        }
        System.err.println("Spells are finicky things Mr. Potter sir, should have chosen your words more carefully...");
        System.err.println("Best of luck to you.");
    }

    public static void testFileReading() {
        Maze m = new Maze("maze1.txt", 250);
        m.printMaze(); // when you write this, this should print the maze
    }

    public static void testDrawing() {
        Maze m = new Maze("maze1.txt",250);
        m.printMaze();
        m.drawMaze();
    }

    public static void testDrawingPatronus() {
        Maze m = new Maze("maze5.txt",250);
        m.printMaze();
        m.drawMazeWithPatronus(2, 2);
    }

    public static void testBooleanSolver() {
        Maze m = new Maze("maze5.txt",100);
        m.printMaze();
        boolean solvable =  m.canSolve();
        System.out.println(solvable);
    }

    public static void testSolver(){
        Maze m = new Maze("maze5.txt",100);
        m.printMaze();
        boolean solvable =  m.canSolve();
        System.out.println(solvable);
    }
}
