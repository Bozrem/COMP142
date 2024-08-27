import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Pile {
    private int count;

    /*
       Initializes a pile object
       Parameters: size, the amount of sticks in the new pile
    */
    Pile(int size) {
        this.count = size;
    }

    /*
       Gets the amount of sticks in a pile
       Parameters:
       Returns: int, sticks in the pile
    */
    public int getCount() {
        return count;
    }

    /*
       Take some amount of sticks from the pile
       Parameters: sticks, the amount of sticks to take
       Returns: void
    */
    public void takeSticks(int sticks) {
        if (count >= sticks) {
            count -= sticks;
        }
    }

    /*
       Creates a pile array from an integer array of stick counts
       Parameters: pileSizes, an int array of the stick counts
       Returns: Pile[], new array of the new Pile objects
    */
    public static Pile[] fromIntArray(int[] pileSizes) {
        Pile[] piles = new Pile[pileSizes.length];
        for (int i = 0; i < pileSizes.length; i++) {
            piles[i] = new Pile(pileSizes[i]);
        }
        return piles;
    }

    /*
       Prints an array of piles for the user
       Parameters: piles, the piles to print
       Returns: void
    */
    public static void printPiles(Pile[] piles) {
        for (int i = 0; i < piles.length; i++) {
            System.out.print(i + ":  ");
            for (int e = 0; e < piles[i].getCount(); e++) {
                System.out.print("| ");
            }
            System.out.println(); // Make new line after each pile
        }
    }

    /*
       Creates an integer array of pile counts from a user input
       Parameters:
       Returns: int[] array of pile sizes
    */
    public static Pile[] fromUserInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the pile sizes you would like to play with, separated by commas:");
        String input = scan.nextLine();
        // Split on any non-digit, then convert to int
        return fromIntArray(Arrays.stream(input.split("\\D+")).mapToInt(Integer::parseInt).toArray());
    } // TODO refactor with returning Pile[]

    /*
       Creates a string to represent an array of piles, in the form 3, 4, 5,
       Parameters: piles, the piles to turn into an array
       Returns: String, the string version of the piles
    */
    public static String pilesToString(Pile[] piles) {
        StringBuilder pileString = new StringBuilder();
        for (Pile pile : piles) {
            pileString.append(pile.getCount())
                    .append(", ");
        }
        return pileString.toString();
    }

    /*
       Deep clones an array of piles so that they aren't mutable
       Parameters: originalPiles, a pile array to clone
       Returns: Pile[] cloned pile array
    */
    public static Pile[] deepClone(Pile[] originalPiles) {
        Pile[] clone = new Pile[originalPiles.length];
        for (int i = 0; i < originalPiles.length; i++) {
            clone[i] = new Pile(originalPiles[i].getCount()); // Assuming Pile has a constructor that takes the count
        }
        return clone;
    }

    /*
       Checks if two arrays of pile objects are equal by their hash codes (so that 1, 2, 3, and 3, 2, 1 are equal)
       Parameters: pileOne, first pile array to compare against pileTwo, the second pile array
       Returns: boolean, are piles equal
    */
    public static boolean equalPiles(Pile[] pileOne, Pile[] pileTwo) {
        return hashCodePiles(pileOne) == hashCodePiles(pileTwo);
    }

    /*
       Gets the hash of an array of piles, by first sorting them then using Arrays.hash
       Parameters: piles, the pile array to hash
       Returns: int, the hash from the piles
    */
    public static int hashCodePiles(Pile[] piles) {
        return Arrays.hashCode(pilesAsSortedInt(piles));
    }

    /*
       Sorts a pile array by count
       Parameters: piles, the pile array to sort
       Returns: int[], sorted pile counts
    */
    public static int[] pilesAsSortedInt(Pile[] piles) {
        int[] asInt = new int[piles.length];
        for (int i = 0; i < piles.length; i++) {
            asInt[i] = piles[i].getCount();
        }
        Arrays.sort(asInt);
        return asInt;
    }

    /*
       Checks if an array of Pile objects has any sticks
       Parameters: piles, the piles to check if empty
       Returns: boolean, do the piles have sticks
    */
    public static boolean pilesHaveSticks(Pile[] piles) {
        for (Pile pile : piles) {
            if (pile.getCount() != 0) return true;
        }
        return false;
    }

    public static int pilesTotal(Pile[] piles){
        int count = 0;
        for (Pile pile : piles) count += pile.getCount();
        return count;
    }
}
