# Patronus Maze Solver
Harry Potter must summon a Patronus that can tell him if the maze he is in is solvable, and the directions he must take to get to the cup.
## Recursive Design
### Can Solve (Boolean)
* At the top, check if the one it tried to move to is either non-existing, a hedge, or already visited
* Draw out the map with Patronus
* Check for the base case of finding the cup, return true if found
* Set the current square as visited (1)
* For each direction, if the recursive call of that square is true, return true;
* If none of those returned true, set the box back to unvisited (pick up breadcrumb)
* Return false (backtrack)
### Directional Solve (String)
* At the top, check if the one it tried to move to is either non-existing, a hedge, or already visited
* Draw out the map with Patronus
* Check for the base case of finding the cup, return 'C'
* Set the current square as visited (1)
* For each direction, if the recursive call of that square ends with a C, return that path with the direction from this at the start;
* If none of those returned, set the box back to unvisited (pick up breadcrumb)
* Return 'F' (backtrack)

## Codes
### Box States
* -1: Hedge
* 0: Unvisited Space
* 1: Visited Space
* 2: Harry (start)
* 3: Cup (finish)

### String Statuses
* N: Go North
* S: Go South
* E: Go East
* W: Go West
* C: Arrived at Cup
* F: Failed

## Notes
* By setting Box states to 1 when Patronus is exploring, it prevents it from making any loops (Challenge Problem)
* I had lots of fun creating the I/O

## Resources
Phillip Kirlin - [Project Resources](https://github.com/pkirlin/cs142-f22-proj4)