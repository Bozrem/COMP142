public class Computer extends Player {
    public ParanoidMinimaxTree currentTree;

    Computer(int computerID){
        super(computerID);
    }

    /*
       Overrides Player.makeMove to get the computer move and do it
       Parameters: piles, the piles for the computer to make a move from
       Returns: void
    */
    @Override
    public void makeMove(Pile[] piles) {
        printGame(piles, this);
        buildTree(piles);
        //TreeViewer viewer = new TreeViewer(currentTree);
        //viewer.browseTree();
        Move move = currentTree.getMove();
        System.out.println("Computer " + playerID + " takes " + move.getSticks() + " sticks from Pile " + move.getPile());
        System.out.println("Searched " + ParanoidMinimaxTree.totalNodes + " possible future situations");
        move.makeMove(piles);
    }

    /*
       Populates the player objects with computers moving first
       Parameters: piles, the piles for the computer to make a move from
       Returns: void
    */
    @Override
    public void printGame(Pile[] piles, Player activePlayer) {
        System.out.println("\n\n\n"); // To space it nicely
        // TODO make computer have its own number so you can have Player 1 and Computer 1
        System.out.println("It is Computer " + (getActivePlayerNumber() + 1) + "'s turn"); // Have to use function because activePlayer is private
        System.out.println("Current Board:");
        Pile.printPiles(piles);
        System.out.println("Computer is thinking...");
    }

    /*
       Builds the current tree with the correct piles
       Parameters: piles, the piles for the tree to use as the root
       Returns: void
    */
    public void buildTree(Pile[] piles) {
        currentTree = new ParanoidMinimaxTree(Pile.deepClone(piles), null, playerID, playerID, 0, 0, this);
    }
}
