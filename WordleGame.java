import java.io.*;
import java.util.*;
public class WordleGame {


    public static void main(String[] args) throws Exception{
    
    
    System.out.println(" ---------- Wordle Game ----------- ");
    System.out.println();
    System.out.println("-> You have to guess the hidden 5 letter word in 6 tries");
    System.out.println("-> White characters: aren't in the target word");
    System.out.println("-> Yellow characters: is in the word but in the wrong spot");
    System.out.println("-> Green characters: is in the word and in the correct spot");

    String filename = "words.txt";
    File file = new File (filename);
    // Scanner for word file
    Scanner reader = new Scanner(file);
    // Scanner for user input
    Scanner input = new Scanner(System.in);

    int lines = lineCounter(filename);
    // List of all words
    String[] word = new String[lines];
    int tries = 0;

    for(int i=0; i<lines; i++) {
        word[i] = reader.nextLine().toLowerCase();
    }
    // Word is being selected randomly from word list
    int randomNum =(int)(Math.random() * lines);
    String correctWord = word[randomNum];
    String guess = "";
    while (!guess.equals(correctWord) && tries !=6) {
        System.out.println();
        System.out.println("Make your guess :");
        guess = input.next();
        System.out.println();
        if (guess.length() != 5) {
            System.out.println("Word lenght must be 5");
        }
        else {
            tries++;
            for(int i=0; i<5; i++) {
                if (guess.charAt(i) == correctWord.charAt(i)) {
                    printGreen(guess.charAt(i));
                }
                else if (guess.charAt(i) != correctWord.charAt(i)) {
                    int counter = 0;
                    for(int j=0; j<5; j++) {
                        if (counter != 0) 
                        break;
                        if (guess.charAt(i) == correctWord.charAt(j)) {
                            printYellow(guess.charAt(i));
                            counter++;
                        }
                        if (counter != 0) 
                        break;
                    }
                    if (counter == 0) {
                        System.out.print(guess.charAt(i));
                    }
                }
            }
            if(guess.equals(correctWord)) {
                System.out.println();
                System.out.println();
                System.out.println("Your Guess is Correct!");
            }
            else if (!guess.equals(correctWord) && tries == 6) {
                System.out.println();
                System.out.println("---- You're out of guesses ----");
                System.out.print("Word is ---> " + correctWord);
            }
        }
    }
    
 
    
    }
    // Count number of words from text file to create array (at line 16)

    public static int lineCounter(String filename) throws Exception{
        int lines = 0;
        File file = new File ("words.txt");
        Scanner reader = new Scanner(file);
        String words = "";
        while (reader.hasNextLine()) {
            reader.nextLine();
            lines++;
        }
        return lines;
    }
    
    // Methods for colored display 

    public static final String GREEN = "\033[0;32m";   // GREEN

    public static final String YELLOW = "\033[0;33m";  // YELLOW

    public static final String STANDART = "\u001B[0m"; // BASE COLOR

    
    public static void printGreen (char x) {
        System.out.print(GREEN + x + STANDART);
    }

    public static void printYellow (char x) {
        System.out.print(YELLOW + x + STANDART);
    }

}