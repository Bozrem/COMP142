import java.util.*;

public class ParanoidMinimaxTree {
    private Pile[] piles;
    Move moveFromParent;
    List<ParanoidMinimaxTree> subTrees = new ArrayList<>();
    public final int activePlayer;
    public final int computerID;
    public static int totalNodes = 0;
    public final int treeID;
    public int strength;
    private final int depthFromCurrent;
    public static Hashtable<Integer, ParanoidMinimaxTree> index = new Hashtable<>(); // Used for viewing tree
    private static Hashtable<Integer, Integer> computedStrengths = new Hashtable<>(); // Used for not having to do same compute multiple times
    private final Computer computer;

    /*
       Initializes a new tree, saving all data about it and populating its children
       Parameters: piles, the array of piles for this specific node/situation
            moveFromParent, the move the parent did to get to this child node, so that the parent knows what move to make to get to a child
            activePlayer, the currently playing player
            computerID, the player ID of the computer object
            parentID, the integer from index of the parent, so that it can be tracked in TreeViewer
            depthFromCurrent, the int depth from the current situation so that it creates the children of the root
    */
    ParanoidMinimaxTree(Pile[] piles, Move moveFromParent, int activePlayer, int computerID, int parentID, int depthFromCurrent, Computer computer) {
        this.piles = piles;
        this.moveFromParent = moveFromParent;
        this.activePlayer = activePlayer;
        this.computerID = computerID;
        treeID = totalNodes + 1;
        totalNodes++;
        this.depthFromCurrent = depthFromCurrent;
        this.computer = computer;
        index.put(treeID, this);

        if (Main.debugMode) {
            System.out.println("\nInitialized new Movetree under player " + activePlayer + " from Parent node " + parentID);
            if (moveFromParent != null)
                System.out.println("Move was " + moveFromParent.getSticks() + " sticks from pile " + moveFromParent.getPile());
            Pile.printPiles(this.piles);
        }

        populateChildren();
    }

    /*
       Populates the children of a node, checking if its already computed, or if it's a duplicate of an already created child
       Parameters:
       Returns: void
    */
    private void populateChildren() {
        if (computedStrengths.containsKey(this.hashCode()) && depthFromCurrent >= 1) {
            strength = computedStrengths.get(this.hashCode());
            if (Main.debugMode) System.out.println("Precomputed Piles to be strength " + strength);
            return;
        }

        List<Move> availableMoves = Move.getAvailableMoves(piles, computer);
        for (Move move : availableMoves) {
            Pile[] pilesAfterMove = move.getPilesAfterMove(piles); // Check what piles are after

            boolean arePilesDuplicates = false;
            for (ParanoidMinimaxTree child : subTrees) {
                if (Pile.equalPiles(child.getPiles(), pilesAfterMove)) {
                    arePilesDuplicates = true;
                    break;
                }
            }

            if (!arePilesDuplicates) {
                // Only create a child if some variation of the piles does not already exist
                subTrees.add(new ParanoidMinimaxTree(pilesAfterMove, move, getNextPlayer(), computerID, treeID, depthFromCurrent + 1, computer));
            }
        }
        getStrength();
    }

    /*
       Browses the strength of the trees children so that it knows which one is best for whichever player is active
       Parameters:
       Returns: Move, the move to get to the next best scenario
    */
    public Move getMove() {
        populateChildren(); // Repopulate so that it populates the children within 2 depth
        if (subTrees.isEmpty())
            return Move.getAvailableMoves(piles, computer).get(0); // Do losing move if lost
        int bestStrength = 0;
        Move bestMove = new Move(-1, -1, new Player(-1)); // Here so it's initialized
        for (ParanoidMinimaxTree child : subTrees) {
            int childStrength = child.getStrength();
            if (isBetterStrength(bestStrength, childStrength)) {
                bestStrength = childStrength; // Filter for the best move for the active player
                bestMove = child.moveFromParent;
            }
        }
        System.out.println("Computer moves towards best strength of " + bestStrength);
        System.out.println("Added " + computedStrengths.size() + " solutions to computed strengths");
        return bestMove;
    }

    /*
       Gets the strength of a node based on if it's a leaf node or its children strength
       Parameters:
       Returns: int, the strength of the node
    */
    public int getStrength() {
        if (strength != 0) return strength;
        if (subTrees.isEmpty()) {
            // Having no children means that this move doesn't have any other valid moves after, and is losing
            return strengthModifier(); // -1 if computer lost, 1 if other lost
        }
        int bestStrength = 0;
        for (ParanoidMinimaxTree child : subTrees) {
            int childStrength = child.getStrength();
            if (isBetterStrength(bestStrength, childStrength))
                bestStrength = childStrength; // Filter for the best move for the active player
        }
        if (Main.debugMode) {
            System.out.println("Sent up " + incrementStrength(bestStrength) + " for the following piles");
            Pile.printPiles(piles);
        }
        strength = incrementStrength(bestStrength);
        computedStrengths.put(this.hashCode(), strength);
        return incrementStrength(bestStrength);
    }

    /*
       Increments the strength when going up the tree in a way that retains it positive or negative skew
       Parameters: strength, the strength to increment
       Returns: int, the new strength to pass to the parent
    */
    private int incrementStrength(int strength) {
        if (strength < 0) return (strength - 1);
        if (strength > 0) return (strength + 1);
        return 0;
    }

    /*
       Checks if a move is better for the active player
       Parameters: currentBestStrength, the strength to compare to,
            newStrength, the strength to see if is better than the current
       Returns: boolean, is the new strength better for the active player
    */
    private boolean isBetterStrength(int currentBestStrength, int newStrength) {
        if (Main.debugMode) {
            System.out.println("\nCurrent strength: " + currentBestStrength);
            System.out.println("New strength: " + newStrength);
            System.out.println("Active player is " + activePlayer + " and computer player id is " + computerID);
        }
        if (currentBestStrength == 0) return true; // First child
        if (activePlayer == computerID) {
            if (newStrength > 0 && currentBestStrength < 0) return true; // New kills the opponent, old lost to it
            if (newStrength > 0 && currentBestStrength > 0 && newStrength < currentBestStrength)
                return true; // Both kill opponent (positive), new kills quicker (smaller number)
            if (newStrength < 0 && currentBestStrength < 0 && newStrength < currentBestStrength)
                return true; // Both are losing (negative), new delays longer (larger number)
            if (Main.debugMode) System.out.println("AI active, not better");
            // Computer has choice, wants to win or delay loss
            // Wants low positive or high negative (in that order)
            return false;
        }
        if (newStrength < 0 && currentBestStrength > 0) return true; // New kills the computer, old lost to it
        if (newStrength < 0 && currentBestStrength < 0 && newStrength > currentBestStrength)
            return true; // Both kill computer (negative), new does it quicker (smaller number)
        if (newStrength > 0 && currentBestStrength > 0 && newStrength > currentBestStrength)
            return true; // Both are losing (positive), new delays longer (larger number)
        if (Main.debugMode) System.out.println("Opponent active, not better");
        // Opponents choice, they want computer to lose
        // Wants low negative or high positive (in that order)
        return false;
    }

    /*
       Adds a positive or negative strength value for leaf nodes, depending on if the Computer lost or anybody else
       Parameters:
       Returns: int, the strength for the leaf node
    */
    private int strengthModifier() {
        if (activePlayer == computerID) return 1; // Empty piles given to computer, opponent before them lost
        return -1; // Computer lost on the round before
    }

    /*
       Cycles to the next player when creating children nodes
       Parameters:
       Returns: int, the ID of the next player
    */
    private int getNextPlayer() {
        if (activePlayer < Player.players.length - 1) {
            return (activePlayer + 1);
        }
        return 0;
    }

    /*
       Gets the piles associated with this node
       Parameters:
       Returns: Pile[], the array of piles associated with this node
    */
    public Pile[] getPiles() {
        return piles;
    }

    /*
       Checks if a different tree object is equal based on the piles, active player, and computer ID
       Parameters: piles, the pile array to hash
       Returns: int, the hash from the piles
    */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != ParanoidMinimaxTree.class) return false; // Object is type tree
        ParanoidMinimaxTree otherTree = (ParanoidMinimaxTree) obj;
        if (!Pile.equalPiles(this.piles, otherTree.piles)) return false; // Unequal piles
        if (this.activePlayer != otherTree.activePlayer) return false; // Different persons turn
        if (this.computerID != otherTree.computerID)
            return false; // Computer is a different player (for multi computer games)
        return true;
    }

    // TODO implement hashcode way of doing equals method above

    /*
       Hashes the current tree based on the piles, active player, and computer player ID
       Parameters:
       Returns: int, the hash of the current tree
    */
    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(Pile.pilesAsSortedInt(piles)), activePlayer, computerID); // sort so that 1, 1, 2 and 2, 1, 1 are the same
        // TODO further optimize the asSortedInt method to remove any empty piles
    }
}
