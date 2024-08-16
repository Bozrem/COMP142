import java.util.Scanner;

public class Game {
    public static void playNewGame() {
        printGameInstructions();
        while (!Pile.areEmpty()){
            Player.advanceTurn();
            Player.getActivePlayer().makeMove();
        }
    }

    /*
    Prints game instructions when the user wants them
     */
    private static void printGameInstructions() {
        int millisBetweenInstructions = 3000;

        boolean userWantsInstructions = userYesOrNo("Would you like game instructions?");
        if (!userWantsInstructions) return;

        try {
            System.out.println("Nim is a game of mathematical strategy");
            Thread.sleep(millisBetweenInstructions);
            System.out.println("Sticks are organized into piles, based on the numbers entered at the beginning");
            Thread.sleep(millisBetweenInstructions);
            System.out.println("During your turn, you may decide one of the piles to take a positive amount of sticks");
            Thread.sleep(millisBetweenInstructions);
            System.out.println("The goal is to force your opponent to take the final stick");
            Thread.sleep(millisBetweenInstructions);
            System.out.println("Good Luck!");
            Thread.sleep(millisBetweenInstructions);
        } catch (Exception e) {
            System.out.println("Failed to sleep Thread while printing instructions");
        }
    }

    /*
    Gets a Y/N answer from the user as a boolean
     */
    public static boolean userYesOrNo(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.print(prompt + " (Y/N): ");
        String response = scan.next().toLowerCase();
        return response.contains("y");
    }


}
