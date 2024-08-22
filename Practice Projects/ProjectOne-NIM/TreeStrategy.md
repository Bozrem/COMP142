# Nim Computer Tree Strategy
This document outlines my design of using a tree for a computer to solve Nim.

## Tree Object
Initializing a tree object takes in:
1. The piles that it will simulate
2. The player whose move it would be with these piles

It will initialize all the possible situations that would come out of this.\
Each Tree object has method getLosses() which returns an int array of the losses it sees for all paths down for each player
* Examples:
  * If a tree object was 1, 1 with player 1 active in a 3 player game
    * getLosses would return [0, 1, 0] because any moves player 1 does results in 2 losing

The magic comes from tree object passing up the losses it sees below itself.
That way, when a decision needs to be made at the top, it can choose the one with the lowest loss percentage for itself

## Outline
1. Check if current piles are within computable threshold (See [Threshold](#Threshold))
2. Begin building a recursive tree, initializing each child tree and getting losses from it.
3. Select the path with the lowest percentage of losses for itself

## Threshold
Strategy to determine if Piles are computable

## Resources
GeeksForGeeks - [Sets in Java](https://www.geeksforgeeks.org/set-in-java/)\
GeeksForGeeks - [Interators in Java](https://www.geeksforgeeks.org/iterators-in-java/)