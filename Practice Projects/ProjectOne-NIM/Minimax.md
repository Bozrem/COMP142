# Paranoid Minimax Strategy
After writing the level 2 tree, I did more investigating into how I could improve it, as it once in a while made simple mistakes.\
In my research with ChatGPT (in [Resources](#resources)), it proposed a paranoid version, as I've outlined below.

## Strategy
The foundation of the strategy relies on viewing all opponents as allied, and a weighting system of outcomes.\
Both foundations have their own sections below.
1. Build the tree of all scenarios
2. When a leaf node is hit, mark it as +1 if an opponent lost, and -1 if the AI lost.
3. As it traverses up the tree, it should be applying Minimax, while incrementing the values for each layer up it goes
4. At the top, the AI wants to either choose the lowest positive number (soonest win) or largest negative number (furthest loss)

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