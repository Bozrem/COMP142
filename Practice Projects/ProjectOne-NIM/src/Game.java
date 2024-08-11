import java.util.Scanner;

public class Game {
    private final Pile[] piles;
    private final boolean debugMode;
    private final int playerCount;

    /*
    Initializes a game object and sets up the piles
     */
    Game(int[] piles, int playerCount, boolean debugMode){
        // Set variables
        this.piles = Pile.fromIntArray(piles);
        this.playerCount = playerCount;
        this.debugMode = debugMode;

        // Provide game info to debug mode
        if (debugMode){
            System.out.println("Piles initialized");
            for (int i = 0; i < this.piles.length; i++){
                System.out.println("Pile " + (i + 1) + " has " + this.piles[i].getCount() + " sticks");
            }
        }
    }

    /*
    Begins playing the Nim game. Returns an int for which player won
     */
    public int play() {
        try{
            printGameInstructions();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Start a round if piles are not all empty
        int activePlayer = playerCount;
        while (!Pile.areEmpty(piles)){
            activePlayer = changeActivePlayer(activePlayer);
            doNextMove(activePlayer);
        }
        return activePlayer;
    }

    /*
    Increases player number if not at the last one
     */
    private int changeActivePlayer(int currentPlayer){
        if (currentPlayer == playerCount) return 1;
        return currentPlayer + 1;
    }

    /*
    Sees if next move should be a computer move
     */
    private void doNextMove(int player){
        if (playerCount == 2 && player == 2){
            //doComputerMove();
            //return;
        }
        printCurrentGame(player);
        doPlayerMove();
    }

    /*
    Gets, checks, and executes player move
     */
    private void doPlayerMove(){
        int pile = getValidPile();
        int sticks = getValidSticks(pile);

        piles[pile].takeSticks(sticks);
    }

    private void doComputerMove(){
        // Still working on
    }

    private int getValidPile(){
        Scanner scan = new Scanner(System.in);
        int pile = -1;
        while (pile <= 0 || pile > piles.length){
            System.out.print("Pile to take from: ");
            pile = scan.nextInt();

            try{
                if (piles[pile].getCount() != 0) break;
                System.out.println("Cannot take from an empty pile!"); // prints when exists but empty
                pile = -1; // Make sure while loop runs again
            } catch (RuntimeException e) {
                System.out.println("Cannot take from non-existing pile!");
                pile = -1; // Make sure while loop runs again
            }
        }
        if (debugMode) System.out.println("Selected pile " + pile);
        return pile;
    }

    private int getValidSticks(int pile){
        Scanner scan = new Scanner(System.in);
        int sticks = -1;
        while (sticks < 0 || sticks > piles[pile].getCount()){
            System.out.print("Amount of sticks you want to take from Pile " + pile + ": ");
            sticks = scan.nextInt();
        }
        if (debugMode) System.out.println("Taking " + sticks + " sticks");
        return sticks;
    }

    /*
    Prints out current game status when playing a round
     */
    private void printCurrentGame(int player){
        System.out.println("\n\n\nIt is Player " + player + "'s turn");
        System.out.println("Current board:");
        for (int i = 0; i < piles.length; i++){
            System.out.print(i + ":  ");
            for (int e = 0; e < piles[i].getCount(); e++){
                System.out.print("| ");
            }
            System.out.println(); // Make new line after each pile
        }
    }

    /*
    Prints game instructions when the user wants them
     */
    private void printGameInstructions() throws InterruptedException {
        int millisBetweenInstructions = 3000;

        boolean userWantsInstructions = userYesOrNo("Would you like game instructions?");
        if (!userWantsInstructions) return;

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
    }

    /*
    Gets a Y/N answer from the user as a boolean
     */
    public static boolean userYesOrNo(String prompt){
        Scanner scan = new Scanner(System.in);
        System.out.print(prompt + " (Y/N): ");
        String response = scan.next().toLowerCase();
        return response.contains("y");
    }


}
