package BuzzFeedQuizc;
/* Irene Feng 10/12/2022
A question class with Answers.
*/ 
import java.util.Scanner;

public class Question {
    // Fields
    String label;
    public int id;
    Answer[] possibleAnswers = new Answer[4];

    Question(int id, String label) {
        this.id = id;
        this.label = label;
    }

    // ask a question, and return the category that corresponds to the answer
    Category ask(Scanner sc, FileSave answerSave) throws Exception {
        System.out.println(this.label);
        for (int i = 0; i < this.possibleAnswers.length; i++) {
            String choice = Integer.toString(i + 1);
            System.out.println("[" + choice + "]:" + this.possibleAnswers[i].label);
        }

        while (true) {
            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("That's not an answer, silly. Please enter a number between 1 and 4.");
                continue;
            }

            int ans = sc.nextInt();
            if (ans >= 1 && ans <= this.possibleAnswers.length && this.possibleAnswers[ans - 1] != null) {
                String key = "Q" + this.id + "_A" + ans;
                answerSave.incrementCategoryCount(key);
                return this.possibleAnswers[ans - 1].cat;
            }

            System.out.println("That's not an answer, silly. Please enter a number between 1 and 4.");
        }
    }

}
