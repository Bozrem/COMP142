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
    Setter function for count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /*
    Converts an integer array with pile sizes to Pile[]
     */
    static Pile[] fromIntArray(int[] pileSizes){
        Pile[] piles = new Pile[pileSizes.length];
        for (int i = 0; i < pileSizes.length; i++){
            piles[i] = new Pile(pileSizes[i]);
        }
        return piles;
    }

    /*
    Tries to remove a number of sticks from the pile. Returns false if not possible
     */
    public boolean takeSticks(int sticks){
        if (count >= sticks){
            count -= sticks;
            return true;
        }
        return false;
    }
}
