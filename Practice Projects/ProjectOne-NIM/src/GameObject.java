import java.util.Scanner;

public class GameObject {
    private final Player[] initialPlayers;
    public Player[] players;
    private final Pile[] initialPiles;
    private Pile[] piles;
    private Player activePlayer;

    GameObject(Player[] players, Pile[] piles){
        this.players = Player.deepClone(players); // Create immutable
        this.initialPlayers = Player.deepClone(players); // Create immutable
        this.piles = Pile.deepClone(piles); // Create clone of other piles to not change the old ones
        this.initialPiles = Pile.deepClone(piles); // Create clone of other piles to not change the old ones
        this.activePlayer = players[0];
    }

    public int playGame(){
        if (getUserBoolean("Do you want game instructions?")) printGameInstructions();

        while(!Pile.areEmpty(piles)){
            activePlayer.printGame(piles, activePlayer);
            Move playerMove = activePlayer.getMove(piles); // Mutable object
            incrementActivePlayer();
            if (playerMove.makeMove(piles)){
                // Print players move
                continue;
            }
            // Error message
        }
        // Make move object also store a player object for who made the move
        return 0; // TODO remove temp
    }

    public Player[] getInitialPlayers(){
        return Player.deepClone(initialPlayers);
    }

    public Pile[] getInitialPiles(){
        return Pile.deepClone(initialPiles);
    }

    private void incrementActivePlayer(){
        activePlayer = Player.getNextPlayer(activePlayer, players);
    }


    /*
       Prints the game instructions if the user wants them
       Parameters:
       Returns: void
    */
    private static void printGameInstructions() {
        int millisBetweenInstructions = 3000;
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
    public static boolean getUserBoolean(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.print(prompt + " (Y/N): ");
        String response = scan.next().toLowerCase();
        return response.contains("y");
    }
}
