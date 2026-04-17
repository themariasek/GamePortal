import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import Game.GameWriteable;

import java.util.HashMap;

public class NumberGuessGame implements GameWriteable {

    int guesses;
    int numToGuess;
    static Scanner sc = new Scanner(System.in);
    ArrayList<Integer> prevGuesses = new ArrayList<>();

    // Histogram to track number of tries per game
    private HashMap<Integer, Integer> histogram = new HashMap<>();

    public NumberGuessGame(int low, int high) {
        guesses = 0;
        System.out.println("I'm thinking of a number + " + low + " to " + high);
        Random rand = new Random();
        numToGuess = rand.nextInt(high - low + 1) + low;
    }
    public NumberGuessGame() {
        guesses = 0;
        prevGuesses.clear();
        numToGuess = new Random().nextInt(100) + 1;
    }
    @Override
    public void play()  {
        System.out.println("I'm thinking of a number from 1 to 100.");
        int guess;
        do {
            guess = getGuess();
            guesses++;
            System.out.println("You guessed " + guess);
            if (prevGuesses.contains(guess)) {
                System.out.println("You already guessed that number! Try again.");
                guesses--;
            }
            prevGuesses.add(guess);
            if (guess < numToGuess) {
                System.out.println("Higher!");
            } else if (guess > numToGuess) {
                System.out.println("Lower!");
            } else {
                System.out.println("Correct! You guessed the number in " + guesses + " guesses.");
            }
        } while (guess != numToGuess);
        System.out.println("Done playing!");
        // Update histogram after each game
        recordGameResult();
    }

    int getGuess() {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            System.out.println("Give me an integer!");
            return getGuess();
        }
    }

    int getNumGuesses() {
        return guesses;
    }

    // Record the number of guesses in the histogram
    private void recordGameResult() {
        histogram.put(guesses, histogram.getOrDefault(guesses, 0) + 1);
    }

    // Get the histogram for statistics
    public HashMap<Integer, Integer> getHistogram() {
        return histogram;
    }

    @Override
    public String getGameName() {
        return "Number Guess Game";
    }

    @Override
    public String getScore() {
        return Integer.toString(guesses);
    }

    @Override
    public boolean isHighScore(String score, String currentHighScore) {
        if (currentHighScore == null) return true;
        return Integer.parseInt(score) < Integer.parseInt(currentHighScore);
    }

}
