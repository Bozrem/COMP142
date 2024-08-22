import java.util.Scanner;

// TODO merge the functionality of this class with Main, or expand it to make things easier for other classes.
// Could be able to keep Pile and Player info here, that way it could be changed from Main and still reset properly

public class Game {

    /*
       Begins to play a new game
       Parameters: piles, the piles to play the game with
       Returns: void
    */
    public static void playNewGame(Pile[] piles) {
        printGameInstructions();
        while (!Pile.areEmpty(piles)) {
            Player.advanceTurn();
            Player.getActivePlayer().makeMove(piles);
        }
    }

    /*
       Prints the game instructions if the user wants them
       Parameters:
       Returns: void
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
       Gets a yes or no answer from the user
       Parameters: prompt, the prompt to give the user for the yes or no
       Returns: boolean, did the user say yes?
    */
    public static boolean userYesOrNo(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.print(prompt + " (Y/N): ");
        String response = scan.next().toLowerCase();
        return response.contains("y");
    }


}
