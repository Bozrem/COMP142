package Project5;// PUT YOUR HEADER HERE

import java.util.ArrayList;
import java.util.Scanner;

public class DanceTester {
    public static void main(String[] args) {
        System.out.println("Welcome to Dancing Bugs!");
        System.out.println("Which bugs would you like to see from the list below?");
        System.out.println("\nBored, Spinning, Routine, Square, Imitating, Mirroring, or Conga");
        Scanner scan = new Scanner(System.in);
        String bugSelected = scan.nextLine();
        System.out.println("Click to start!");
        if (bugSelected.equalsIgnoreCase("bored")) testBoredBug();
        if (bugSelected.equalsIgnoreCase("spinning")) testSpinBug();
        if (bugSelected.equalsIgnoreCase("routine")) testRoutineBug();
        if (bugSelected.equalsIgnoreCase("square")) testSquareBug();
        if (bugSelected.equalsIgnoreCase("imitating")) testImitationBug2();
        if (bugSelected.equalsIgnoreCase("mirroring")) testMirrorBug();
        if (bugSelected.equalsIgnoreCase("conga")) testCongaLine();
    }

    public static void testBoredBug() {
        DanceFloor floor = new DanceFloor(3, 5);
        BoredBug boredbug = new BoredBug(1, 2);
        floor.addDancer(boredbug);
        floor.everyoneDance();
    }

    public static void testSpinBug(){
        DanceFloor floor = new DanceFloor(9, 9);
        SpinBug spinBug = new SpinBug(4, 4);
        floor.addDancer(spinBug);
        floor.everyoneDance();
    }

    public static void testRoutineBug(){
        ArrayList<DanceStep> steps = new ArrayList<>();
        steps.add(DanceStep.STEP_RIGHT);
        steps.add(DanceStep.STEP_RIGHT);
        steps.add(DanceStep.STEP_RIGHT);
        steps.add(DanceStep.PAUSE);
        steps.add(DanceStep.STEP_LEFT);
        steps.add(DanceStep.STEP_LEFT);
        steps.add(DanceStep.STEP_LEFT);
        steps.add(DanceStep.PAUSE);
        steps.add(DanceStep.BACKWARD);
        steps.add(DanceStep.BACKWARD);
        steps.add(DanceStep.BACKWARD);
        steps.add(DanceStep.PAUSE);
        steps.add(DanceStep.FORWARD);
        steps.add(DanceStep.FORWARD);
        steps.add(DanceStep.FORWARD);
        steps.add(DanceStep.TURN_LEFT);
        DanceFloor floor = new DanceFloor(9,9);
        RoutineBug routineBug = new RoutineBug(4, 4);
        routineBug.setRoutine(steps);
        floor.addDancer(routineBug);
        floor.everyoneDance();
    }

    public static void testSquareBug(){
        DanceFloor floor = new DanceFloor(9, 9);
        SquareBug squareBug = new SquareBug(4, 4, 2);
        floor.addDancer(squareBug);
        floor.everyoneDance();
    }

    public static void testImitationBug(){
        DanceFloor floor = new DanceFloor(9, 9);
        SpinBug spinBug = new SpinBug(4, 4);
        ImitationBug imitationBug = new ImitationBug(7, 4, spinBug);
        floor.addDancer(spinBug);
        floor.addDancer(imitationBug);
        floor.everyoneDance();
    }

    public static void testImitationBug2(){
        ArrayList<DanceStep> steps = new ArrayList<>();
        steps.add(DanceStep.STEP_RIGHT);
        steps.add(DanceStep.STEP_RIGHT);
        steps.add(DanceStep.STEP_RIGHT);
        steps.add(DanceStep.PAUSE);
        steps.add(DanceStep.STEP_LEFT);
        steps.add(DanceStep.STEP_LEFT);
        steps.add(DanceStep.STEP_LEFT);
        steps.add(DanceStep.PAUSE);
        steps.add(DanceStep.BACKWARD);
        steps.add(DanceStep.BACKWARD);
        steps.add(DanceStep.BACKWARD);
        steps.add(DanceStep.PAUSE);
        steps.add(DanceStep.FORWARD);
        steps.add(DanceStep.FORWARD);
        steps.add(DanceStep.FORWARD);
        steps.add(DanceStep.TURN_LEFT);

        DanceFloor floor = new DanceFloor(9, 9);
        RoutineBug routineBug = new RoutineBug(4, 4);
        ImitationBug imitationBug = new ImitationBug(7, 4, routineBug);

        routineBug.setRoutine(steps);

        floor.addDancer(routineBug);
        floor.addDancer(imitationBug);

        floor.everyoneDance();
    }

    public static void testMirrorBug(){
        DanceFloor floor = new DanceFloor(9, 9);
        SquareBug squareBug = new SquareBug(6, 0, 4);
        MirrorBug mirrorBug = new MirrorBug(6, 8, squareBug);
        floor.addDancer(squareBug);
        floor.addDancer(mirrorBug);
        floor.everyoneDance();
    }

    public static void testCongaLine(){
        DanceFloor floor = new DanceFloor(9,9);
        SquareBug squareBug = new SquareBug(6, 0, 4);
        CongaBug congaBug = new CongaBug(7, 0, squareBug);
        CongaBug congaBug2 = new CongaBug(8,0,congaBug);

        floor.addDancer(squareBug);
        floor.addDancer(congaBug);
        floor.addDancer(congaBug2);

        floor.everyoneDance();
    }
}
