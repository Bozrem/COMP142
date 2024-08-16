import java.util.Scanner;

public class Player {
    public static Player[] players;
    private static int activePlayer = -1; // advances to player 0 on first turn

    Player(){
        // Doesn't really need to do anything but exist
    }

    /*
    Prompts the player to make a move, modifies the pile accordingly
     */
    public void makeMove(Pile[] piles){
        printGame();

        Pile pile = getValidPile(piles);
        int sticks = getValidSticks(pile);
        pile.takeSticks(sticks);
    }

    private void printGame(){
        System.out.println("\n\n\n"); // To space it nicely
        System.out.println("It is Player " + (activePlayer + 1) + "'s turn");
        System.out.println("Current Board:");
        Pile.printPiles();
    }

    private Pile getValidPile(Pile[] piles){
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
        if (Main.debugMode) System.out.println("Selected pile " + pile);
        return piles[pile];
    }

    private int getValidSticks(Pile pile){
        Scanner scan = new Scanner(System.in);
        int sticks = -1;
        while (sticks <= 0 || sticks > pile.getCount()){
            System.out.print("Amount of sticks you want to take: ");
            sticks = scan.nextInt();
        }
        if (Main.debugMode) System.out.println("Taking " + sticks + " sticks");
        return sticks;
    }

    /*
    Returns the player object of the player whose turn it is
     */
    public static Player getActivePlayer(){
        return players[activePlayer];
    }

    public static int getActivePlayerNumber(){
        return activePlayer;
    }

    /*
    Initialize the players array with Player and Computer objects
     */
    public static void initializePlayers(int humans, int computers){
        players = new Player[humans + computers];
        // TODO make these switch back and forth
        for (int i = 0; i < humans; i++){
            players[i] = new Player();
        }
        for (int i = humans; i < humans + computers; i++){
            players[i] = new Computer();
        }
    }

    /*
    Go to the next players turn, reset to the first if at the end
     */
    public static void advanceTurn(){
        if (activePlayer < players.length - 1) {
            activePlayer++;
            return;
        }
        activePlayer = 0;
    }
}
