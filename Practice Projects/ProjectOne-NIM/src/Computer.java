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
    public Move getMove(GameObject game) {
        buildTree(game);
        if (Main.debugMode){
            TreeViewer viewer = new TreeViewer(currentTree);
            viewer.browseTree();
        }
        Move move = currentTree.getMove();
        System.out.println("Evaluated " + ParanoidMinimaxTree.totalNodes + " futures, moved towards strength of " + (currentTree.strength - 2));
        return move;
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
        System.out.println("It is Computer " + playerID + "'s turn");
        System.out.println("Current Board:");
        Pile.printPiles(piles);
        System.out.println("Computer is thinking...");
    }

    /*
       Builds the current tree with the correct piles
       Parameters: piles, the piles for the tree to use as the root
       Returns: void
    */
    public void buildTree(GameObject game) {
        currentTree = new ParanoidMinimaxTree(Pile.deepClone(game.getPiles()), null, this, 0, 0, this, game.getPlayers());
    }

    @Override
    public Computer clone(){
        return new Computer(this.playerID);
    }

    @Override
    public String getPlayerType(){
        return "Computer";
    }
}
