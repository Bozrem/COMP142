public class Computer extends Player {
    public ParanoidMinimaxTree currentTree;

    /*
    Controller to decide what kind of move to make. Makes it easier for other classes to call it
     */
    @Override
    public void makeMove(Pile[] piles){
        printGame(piles);
        buildTree(piles);
        //TreeViewer viewer = new TreeViewer(currentTree);
        //viewer.browseTree();
        Move move = currentTree.getMove();
        System.out.println("Computer " + playerID + " takes " + move.getSticks() + " sticks from Pile " + move.getPile());
        System.out.println("Searched " + ParanoidMinimaxTree.totalNodes + " possible future situations");
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

    /*
    Initializes the current tree, lets it build out its children
     */
    public void buildTree(Pile[] piles){
        currentTree = new ParanoidMinimaxTree(Pile.deepClonePiles(piles), null, playerID, playerID, 0, 0);
    }
}
