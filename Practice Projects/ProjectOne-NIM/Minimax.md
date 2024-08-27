# Paranoid Minimax Strategy
After writing the level 2 tree, I did more investigating into how I could improve it, as it once in a while made simple mistakes.\
In my research with ChatGPT (in [Resources](#resources)), it proposed a paranoid version, as I've outlined below.

## Minimax Fundamentals
Minimax is an algorithm for turn based strategy games like Chess. 
It seeks to branch out all paths from a scenario, and chooses the best one while assuming that the opponent plays optimally\
If a scenario is shown to make the Computer lose in a turn, the Computer would avoid that scenario while the opponent would pick it.

## Strategy
The foundation of the strategy relies on viewing all opponents as allied, and a weighting system of outcomes.\
Both foundations have their own sections below.
1. Build the tree of all scenarios
2. When a leaf node is hit, mark it as +1 if an opponent lost, and -1 if the AI lost.
3. As it traverses up the tree, it should be applying Minimax, while incrementing the values for each layer up it goes
4. At the top, the AI wants to either choose the lowest positive number (soonest win) or largest negative number (furthest loss)

## Optimizations
Being that we need to look at every situation, the tree method originally expanded pretty poorly. 
In a game with piles as simple as 3, 4, 5, it would need to compute some 700,000 situations.\
To remedy this, I've implemented two major optimizations:
1. Duplicate checking
   * When creating children for a node, it originally made all possible children.
   * Since Nim doesn't care about the order of piles, we can add a duplicate condition as follows
     * If the sorted version of two piles is the same, they are equal.
   * In this scenario, 1, 2, 3, and 3, 2, 1 are equal, because both are 1, 2, 3 when sorted.
2. Save Computed Scenarios
   * Once a scenario is made, we can use a hash to store that specific instance based on player and pile data
   * When checking to create children in other trees, the current trees hash can be searched in the HashTable
   * When a scenario is already in the hashtable, it is already computed and have its strength set accordingly
     * I needed to solve a bug with this where it would follow a path that had no children because it already had its strength set
     * I solved this by forcing situations to populate if they are the children of the root (current) situation

## Paranoid
Paranoid means the AI is viewing all other opponents as allied against it. 
This is to implement the Minimax idea that we follow the worst case possible from the opponents, even though in reality they likely aren't allied.\
Because it views all opponents the same, it will likely think each turn that it is doomed, but that is where the weighting strategy comes in.

## Weighting
Weighting losses as they propagate allows the AI to pick the best decision even if every one seems like a loss.
By increasing the value of situations as they propagate, we enable the AI to pick the longest out loss when all situations are losses, and the closest win if there is one.\
The weighting works as follows
1. At the leaf nodes, situation values are assigned as 1's, with a negative sign when the AI lost, and a positive if it won.
2. As we propagate, each node does the following
   * If it's the AI's turn
     * Its value becomes either the soonest win (lowest positive value) or the furthest loss (highest negative value) if that isn't available
   * If it's the Opponent's turn
     * Its value becomes either the soonest AI loss (lowest negative value) or the furthest loss for themselves (highest positive value)
3. Increment the value by one to reflect another turn
4. This process continues until the real decision, in which the AI applies the same choice strategy.

#### Example 1
Node is an AI turn. It is presented with the following values of its children situations
* 5, 6, -1, -5, -3, 7

The AI wants to win (positive value) as soon as possible (lowest amount of turns), so it would choose the 5.\
#### Example 2
Node is an AI turn. It is presented with the following values for its children situations
* -1, -3, -5, -9

The AI has no winning situation here, so it would choose the one where it survives the longest before a loss (-9) in the hopes that the opponent makes an error.


## Resources
ChatGPT - [Improving Nim AI Strategy](https://chatgpt.com/share/519f1a42-325d-44d5-a241-4539b3e14f5d)\
YouTube - Sebastian Lague - [Algorithms Explained – minimax and alpha-beta pruning](https://youtu.be/l-hh51ncgDI?si=xrGWiMcuzKrK9qz3)

## Limitations
While a paranoid method to allow Minimax can be quite powerful, it does have its limitations.
I've outlined below what I see as the biggest ones here, and some possible solutions to consider
### Mutual Interests
Being that the algorithm plays considering all other players as enemies, it cannot see situations in which alliance may be beneficial.
Take the following NeoNim game as an example:
* The piles are 3, 4, 0
  * It is the Algorithms turn, with two other players to follow.
  * In its current paranoid state, the computer thinks it loses in 7 moves, and takes a stick from Pile 1 to delay its loss.
* The piles are 2, 4, 0
  * It is the first players turn
  * A human sees the advantage of mutual interest
    * If the first player takes the 4, it leaves two sticks, guaranteeing that loss for a different player is within 2 moves
    * 
### Multiplayer Bugs
Similar to Mutual interests, it doesn't see that it can force a hand with multiple opponents.\
When given 2, 4 and two other players to follow, instead of picking the 4 and guaranteeing safety, 
it thinks it is losing in 7 moves and takes a stick from the first pile, instead giving opponents a way to team up and win