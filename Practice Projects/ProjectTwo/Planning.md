# Gumdrop Gatherer
Gumdrop Gatherer is a Candy Crush style game in which the user selects sets of colored dots adjacent to other dots to collect them for points.\
## Requirements
1. Settable size (Rows and Columns)
2. Settable points to play to
3. Draw a board with filled circles to represent Gumdrops
4. User should be able to click a Gumdrop, which (if there are more than 2 connected of that color) will remove all connected drops of that color
5. When gumdrops disappear, others should fall with gravity to fill in any gaps
6. Open, top areas of the board should be filled in by new gumballs
7. Scoring depends on selection size (seen below)
   1. Less than 5 drops scores 10 points per drop
   2. Greater than or equal to 5 and less than 10 scores 50 points per drop
   3. Greater than or equal to 10 drops scores 100 points per drop
8. When the threshold for points is hit, the game ends

## Challenge Requirements
1. Add different items with unique functionality
2. Add diagonal functionality to gumball selection

## Design

### Board
The project details suggest using an int[][] 2d array to store the current board.\
For easier use, one could also create a separate Board class for easier use of functions and to store this array.\
GUI is most easily handled by creating a simple canvas object and having functions in Board to initialize it and modify it easily.

### User Input
The program can simply use a Scanner to get Rows, Columns, and point goals from the player.\
An additional challenge could be added with the amount of colors to make the gumballs, decreasing the chance of neighbors

### Getting and Deleting Neighbors
The most difficult part of the game here would be the design to get and delete nearby gumdrops.\
An effective way of tackling this problem could be to implement two main additional components:
1. Recursive Finding
    * To make finding easier, you could design the finder to do the following
      * Add selected Gumdrop to a HashSet\<Coordinate> (to avoid duplicates)
      * If a neighbor (including diagonals for the challenge problem) is the color (static variable) we are looking for, run the same recursive function for it.
2. Coordinate Class
    * A Coordinate class would simply make it easier for the recursive function to return both an X and Y position (similar to a tuple)

### Colors
The java.awt.Color import could be useful in storing and accessing the various colors needed for Gumdrops as well comparison when finding Gumdrops

### Gravity
* Start at the bottom row
* For each column:
  * Search upwards until an empty box is found
  * Move all above gumballs down 1 space
  * Fill in the top spot with a random color from the colors variable

## Classes

### Main
Variables (static):
* SimpleCanvas canvas
* List<Color> colors
* Board board
* int points
* int pointGoal

Method Outline:
* public static void main(String[] args)
  * Main function to begin the game, gets the board and starts a game loop for it
* private static Board initializeBoard()
  * Gets the information from the user and returns the Board object created by it
* public void onClick(int xPixel, int yPixel)
  * Converts to a Coordinate
  * Calls the board.findNeighbor() to fill selectedGumballs
  * if selectedGumballs size > 1
      * use board.getPointsFromMove() to add to points
      * ues board.deleteSelected to deleted selected moves
      * update the canvas
  * otherwise, ask the user to select a different ball
* public static void updateCanvas()
  * Updates/creates the canvas based on the board and canvas in this class. Uses board.getNextGravity as it does it

### Board
Variables:
* int[][] board
* Color selectedColor
* HashSet\<Coordinate> selectedGumballs

Method Outline:
* Board(int rows, int columns, List<Color> colors)
  * Initializes the board and canvas with the selected specifications (using the SimpleCanvas class provided)
* private void findNeighbor(Coordinate)
  * Adds this coordinate to selectedGumballs
  * Search for neighbors to recursively run this function on
* public int getPointsFromMove()
  * Logic to handle selectedGumballs size into points
* public int deleteSelected()
  * Goes through each part of board and deletes any whose coordinates are found in selectedGumballs
* public void getNextGravity()
  * Updates the board
  * Detailed algorithm in [Gravity](#gravity) section of [Design](#design)

### Coordinate
Variables:
* int x
* int y

Method Outline:
* Coordinate(int x, int y)
  * Initialize a new coordinate
* public static Coordinate FromPixelPos(int xPixel, int yPixel)
  * Would translate the pixels from the getMouseClickX and Y functions of SimpleCanvas into a new 