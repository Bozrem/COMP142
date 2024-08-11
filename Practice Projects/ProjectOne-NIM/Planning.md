# NIM Game
The goal of this project is to create a game of [Nim](https://en.wikipedia.org/wiki/Nim) in the command line interface.\
Nim has two players, and our version has an initial 3 piles.\
The goal is to take positive amounts of sticks from single piles in a way that forces your opponent to take the last stick

## Design
I would like to integrate object-oriented design into the program, but the instructions say to only submit the Nim.java.\
However, being that this is a practice project for me, I may as well do it in the best way I can.

### Pile object
Each pile is its own object that has its own count variable with checking and taking functions.\
### Game Object
By designing the Game as its own object, we can redesign the main function to start or replay games however we may want.\
Each game will have an initialization function that takes pile counts, and will play the game from there.
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

## Resources
Stack Overflow - [For each char in String](https://stackoverflow.com/questions/2451650/how-do-i-apply-the-for-each-loop-to-every-character-in-a-string)\
ChatGPT - [Regular Expression to Extract Digits](https://chatgpt.com/share/21305ee5-d894-4b43-96ab-2ecbcb3b4bce)\
Stack Overflow - [Limit to Y/N using ReGex](https://stackoverflow.com/questions/38879288/how-to-restrict-a-user-to-enter-only-single-character-i-e-y-and-n-using-a-re)