public class Computer extends Player {
    MoveTree currentTree;

    /*
    Controller to decide what kind of move to make. Makes it easier for other classes to call it
     */
    @Override
    public void makeMove(Pile[] piles){
        buildTree(piles);
        printGame(currentTree.getPiles());
        Move move = getTreeMove();
        System.out.println("Computer " + playerID + " takes " + move.getSticks() + " sticks from Pile " + move.getPile());
        move.makeMove(piles);
    }

    /*
    Prints out the game given to the computer. Since this is private in both Player and Computer, no override is necessary
     */
    private void printGame(Pile[] piles){
        System.out.println("\n\n\n"); // To space it nicely
        // TODO make computer have its own number so you can have Player 1 and Computer 1
        System.out.println("It is Computer " + (getActivePlayerNumber() + 1) + "'s turn"); // Have to use function because activePlayer is private
        System.out.println("Current Board:");
        Pile.printPiles(piles);
        System.out.println("Computer is thinking...");
    }

    private Move getTreeMove(){
        return currentTree.getMove();
    }

    /*
    Initializes the current tree, lets it build out its children
     */
    public void buildTree(Pile[] piles){
        currentTree = new MoveTree(piles.clone(), playerID, null, 0);
    }

}
