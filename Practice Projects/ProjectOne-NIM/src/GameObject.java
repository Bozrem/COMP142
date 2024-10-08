import java.util.Scanner;

public class GameObject {
    private final Player[] players;
    private final Pile[] initialPiles;
    private final Pile[] piles;
    private Player activePlayer;

    /*
        This function constructs a new GameObject by cloning each important aspect of the game
        Parameters: players, the array of Player objects in the game,
                piles, the array of Pile objects for the games piles
     */
    GameObject(Player[] players, Pile[] piles){
        this.players = Player.deepClone(players); // Create immutable
        this.piles = Pile.deepClone(piles); // Create clone of other piles to not change the old ones
        this.initialPiles = Pile.deepClone(piles); // Create clone of other piles to not change the old ones
        this.activePlayer = players[0];
    }

    /*
        Begins a game based on the information when initializing the object
        Parameters:
        Returns: Player, the losing player of the game
     */
    public Player playGame(){
        if (getUserBoolean("Do you want game instructions?")) printGameInstructions();

        Move playerMove = null;
        while(Pile.pilesHaveSticks(piles)){
            activePlayer.printGame(piles, activePlayer);

            playerMove = activePlayer.getMove(this); // Mutable object
            activePlayer = Player.getNextPlayer(activePlayer, players); // Increment player

            if (playerMove.makeMove(piles)){
                System.out.println(playerMove);
                continue;
            }
            System.out.println("Error when attempting to make player move");
        }
        assert playerMove != null;
        return playerMove.player;
    }

    /*
        Getter method for a copy of the Players array
        Parameters:
        Returns: Player[], array of Player objects for the game
     */
    public Player[] getPlayers(){
        return Player.deepClone(players);
    }

    /*
        Getter method for a copy of the Piles array
        Parameters:
        Returns: Pile[], array of piles currently in the GameObject
    */
    public Pile[] getPiles(){
        return Pile.deepClone(piles);
    }

    /*
        Getter method for a copy of the InitialPiles array, to restart the game with the same piles when requested
        Parameters:
        Returns: Pile[], array of piles originally in the GameObject
     */
    public Pile[] getInitialPiles(){
        return Pile.deepClone(initialPiles);
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
