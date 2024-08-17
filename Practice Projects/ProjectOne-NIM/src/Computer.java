public class Computer extends Player {
    MoveTree currentTree;


    /*
    Controller to decide what kind of move to make. Makes it easier for other classes to call it
     */
    @Override
    public void makeMove(){
        System.out.println("This overrides the normal player move");
        printGame();
        makeBasicMove();
    }

    /*
    Prints out the game given to the computer. Since this is private in both Player and Computer, no override is necessary
     */
    private void printGame(){
        System.out.println("\n\n\n"); // To space it nicely
        // TODO make computer have its own number so you can have Player 1 and Computer 1
        System.out.println("It is Computer " + (getActivePlayerNumber() + 1) + "'s turn"); // Have to use function because activePlayer is private
        System.out.println("Current Board:");
        Pile.printPiles();
        try{
            System.out.print("Computer " + (getActivePlayerNumber() + 1) + " is thinking.");
            Thread.sleep(1500);
            System.out.print(".");
            Thread.sleep(1500);
            System.out.print(".");
            Thread.sleep(1500);
        } catch (InterruptedException e){
            System.out.println("Error handling \"Computer Thinking\" Sleep");
        }

    }

    /*
    Simply takes the next available stick
     */
    private void makeBasicMove(){
        int stickNumber = 1;
        Pile[] piles = Pile.getPiles();
        for (int i = 0; i < piles.length; i++){
            if (piles[i].getCount() >= stickNumber){
                piles[i].takeSticks(stickNumber);
                System.out.print("\nComputer " + (getActivePlayerNumber() + 1) + " takes " + stickNumber + " sticks from Pile " + i);
                return;
            }
        }
        System.out.println("Unable to make any valid moves");
    }

    public void buildTree(){
        int[] intPiles = Pile.pilesToInts();
        currentTree = new MoveTree(intPiles, playerID);
    }

}
