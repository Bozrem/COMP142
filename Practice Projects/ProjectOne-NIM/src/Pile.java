import java.util.Arrays;
import java.util.Scanner;

public class Pile {
    private int count;

    Pile(int size){
        this.count = size;
    }

    /*
    Getter function for count
     */
    public int getCount() {
        return count;
    }

    /*
    Tries to remove a number of sticks from the pile. Returns false if not possible
     */
    public void takeSticks(int sticks){
        if (count >= sticks){
            count -= sticks;
        }
    }

    /*
    Converts an integer array with pile sizes to Pile[]
     */
    public static Pile[] fromIntArray(int[] pileSizes){
        Pile[] piles = new Pile[pileSizes.length];
        for (int i = 0; i < pileSizes.length; i++){
            piles[i] = new Pile(pileSizes[i]);
        }
        return piles;
    }



    public static boolean areEmpty(Pile[] piles){
        for (Pile pile : piles){
            if (pile.getCount() != 0) return false;
        }
        return true;
    }

    public static void printPiles(Pile[] piles){
        for (int i = 0; i < piles.length; i++){
            System.out.print(i + ":  ");
            for (int e = 0; e < piles[i].getCount(); e++){
                System.out.print("| ");
            }
            System.out.println(); // Make new line after each pile
        }
    }

    /*
    Provides the user interface to set up the game, returning the parsed input as the pile sizes
     */
    public static int[] fromUserInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the pile sizes you would like to play with, separated by commas:");
        String input = scan.nextLine();
        // Split on any non-digit, then convert to int
        return Arrays.stream(input.split("\\D+")).mapToInt(Integer::parseInt).toArray();
    }

    public static String pilesToString(Pile[] piles){
        String pileString = "";
        for (Pile pile : piles){
            pileString += (pile.getCount() + ", ");
        }
        return pileString;
    }

    public static Pile[] deepClonePiles(Pile[] originalPiles) {
        Pile[] clone = new Pile[originalPiles.length];
        for (int i = 0; i < originalPiles.length; i++) {
            clone[i] = new Pile(originalPiles[i].getCount()); // Assuming Pile has a constructor that takes the count
        }
        return clone;
    }
}
