# NeoNim Game
The goal of this project is to create an advanced game of [Nim](https://en.wikipedia.org/wiki/Nim) in the command line interface.\
Nim has two players, and our version has an initial 3 piles.\
The goal is to take positive amounts of sticks from single piles in a way that forces your opponent to take the last stick\
It's called NeoNim because it extends the original intent of the game both in players and sticks (and because it sounds like neovim)

## Design
I would like to integrate object-oriented design into the program, but the instructions say to only submit the Nim.java.\
However, being that this is a practice project for me, I may as well do it in the best way I can.

### Pile object
Each pile is its own object that has its own count variable with checking and taking functions.\
### Game Object
By designing the Game as its own object, we can redesign the main function to start or replay games however we may want.\
Each game will have an initialization function that takes pile counts, and will play the game from there.
### Player Object
To provide a simple interface that the Game object can print information to the player and get moves
### Computer Object (Extending Player)
Extending the player object allows the Computer object to fit nicely into the Player[] array while enabling more complex behavior for moves.

### Computer Strategy
One of the challenge problems with this project is to create a computer to play against. 
While I did find a source online of the perfect way to play Nim, I wanted the strategy to be my own design.\
Here are the various methods I considered:
1. Brute force - Simulate the games future for a given move
   * Pros: I don't have to think hard about how to do this well
   * Cons: Slow, bulky code, scales poorly
2. Neural Network
   * Pros: Fancy, adaptive to scenarios
   * Cons: Difficult to build and train
3. Find a winning pattern
   * Pros: It's a mathematical game that can be probably solved with math
   * Cons: Not easy math to figure out
4. Set of rules to make decision
   * Pros: Relatively easy to code
   * Cons: Could be exploited if a gap in knowledge is found
5. Tree
   * Pros: Easy recursion, rules eliminate branches
   * Cons: Still could be inefficient at scale

Here is what I'm doing for my computer player.
#### Level 1: Stupid
All it does it take the next available stick.
#### Level 2: Tree
Applies this if pile numbers are within a computable threshold.\
If its outside computable threshold, it applies Level 1 until its within range. Read more about my tree design in TreeStrategy.md
#### Level 3: Minimax Tree
I initially didn't consider a Minimax-like algorithm because it breaks down at more than 2 players.
However, after I wrote the level 2 tree, I did some research with ChatGPT that suggested a paranoid Minimax Algorithm.\
You can read more about how I implemented this in Minimax.md

## Questions
#### What difficulties did you have to overcome?
I wanted to be able to parse the user input in a way that the user can put a bunch of extra stuff and it would pick out the important characters. Here were the steps I took to overcome it.
1. Split the String by commas to get the pile numbers as an int[]
   * Failed due to user being able to put things like extra spaces
2. Use a for each loop to look at the input characters individually
   * Failed due to multi-digit numbers not parsing correctly
3. Split by commas first, then use Regex to remove any non-digit from remaining Strings
   * Found to be overcomplicated after ChatGPT helped me with RegEx
   * Also failed because I was using Scanner.next() instead of Scanner.nextLine()
4. Use RegEx to split on any non-digit, then cast that String[] to int[]

I also wanted to make sure that any pile that the user requested both existed and had a non-zero count of sticks
1. Integrated a check into the while loop for empty piles
   * Has an out-of-bounds exception when user enters something like -1
2. Add an extra if statement at the bottom, using && to make it not run last check
   * Bad solution, inefficient and still fails
3. Try-Catch statement with print statements to tell user what they did wrong

I had a couple of quite difficult to find bugs when making the Minimax algorithm:
1. Mutable piles
   * While I was using the .clone() method on the piles, I found that it wasn't working for whatever reason
   * Trying to get the next moves would mutate the original piles object
   * To solve this, I made a deepClone method in Pile
2. Backward strengths
   * Due to doing the checks and strength assignments to empty piles instead of 1 stick remaining, I originally had the strength setting backward
   * What made this bug particularly frustrating was that due to Minimax's choosing method, it hid this and made me think it worked
   * I solved this by making a TreeViewer class that allowed me to go down the tree nodes and get important information about them
3. Not Creating Children
   * When I implemented the ComputedSolutions optimization, I did not account for the situation where the correct path is never populated.
   * This resulted when a path was followed that had no children or some missing due to not needing to populate them, and would do the default take next available stick
   * I found this bug with the same TreeViewer, and solved it by forcing it to populate the children of the current situation.

## Resources
Stack Overflow - [For each char in String](https://stackoverflow.com/questions/2451650/how-do-i-apply-the-for-each-loop-to-every-character-in-a-string)\
ChatGPT - [Regular Expression to Extract Digits](https://chatgpt.com/share/21305ee5-d894-4b43-96ab-2ecbcb3b4bce)\
Stack Overflow - [Limit to Y/N using ReGex](https://stackoverflow.com/questions/38879288/how-to-restrict-a-user-to-enter-only-single-character-i-e-y-and-n-using-a-re)