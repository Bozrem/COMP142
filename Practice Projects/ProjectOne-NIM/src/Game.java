public class Game {
    private Pile[] piles;
    private boolean debugMode;

    /*
    Initializes a game object and sets up the piles
     */
    Game(int[] piles, boolean debugMode){
        // Set variables
        this.piles = Pile.fromIntArray(piles);
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
    public int play(){

        return 1;
    }


}
