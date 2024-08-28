import java.util.Scanner;

public class NumGuesser {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int randomNum = (int)((Math.random() * 100) + 1);

        int count = 1;
        int guess = -1; // So that it cannot be correct initially

        while (guess != randomNum){
            System.out.print("Guess " + count + ": ");
            guess = scan.nextInt();
            count++;
            System.out.println(higherOrLower(guess, randomNum));
        }
        System.out.println("The number was " + randomNum + "! You guessed it in " + count + " guesses!");
    }

    /*
        Returns a string telling the user to go higher or lower based on their guess and the real number
        Parameters: guess, the users number guess,
                    real, the randomly generated number
        Returns: String, the string hint to the user
     */
    public static String higherOrLower(int guess, int real){
        if (guess > real) return "Lower!";
        if (guess < real) return "Higher!";
        return "";
    }
}