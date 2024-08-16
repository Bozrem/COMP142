public class Pile {
    private int count;
    private static Pile[] piles;

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
    Converts an integer array with pile sizes to Pile[]
     */
    static void fromIntArray(int[] pileSizes){
        piles = new Pile[pileSizes.length];
        for (int i = 0; i < pileSizes.length; i++){
            piles[i] = new Pile(pileSizes[i]);
        }
    }

    /*
    Tries to remove a number of sticks from the pile. Returns false if not possible
     */
    public void takeSticks(int sticks){
        if (count >= sticks){
            count -= sticks;
        }
    }

    public static boolean areEmpty(){
        for (Pile pile : piles){
            if (pile.getCount() != 0) return false;
        }
        return true;
    }

    public static Pile[] getPiles(){
        return piles;
    }

    public static void printPiles(){
        for (int i = 0; i < piles.length; i++){
            System.out.print(i + ":  ");
            for (int e = 0; e < piles[i].getCount(); e++){
                System.out.print("| ");
            }
            System.out.println(); // Make new line after each pile
        }
    }
}
