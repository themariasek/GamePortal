package BuzzFeedQuizc;

/*
 * Irene Feng Nov 2022
 * This is the class where we create the Quiz and run it. It has the main method.  
 */
import java.util.Scanner;

public class Quiz {
        static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) throws Exception {
                FileSave fileSave = new FileSave();
                FileSave lastSave = new FileSave("LastResult.csv");
                FileSave answerSave = new FileSave("AnswerPopularity.csv");

                String last = lastSave.getResults().get("mostRecentCategory");
                if (last != null) {
                        System.out.println("Last time you got: " + last);
                }

                do {
                // Create Categories
                Category walter = new Category("Walter White", "You're quiet, stubborn, and like feeling in control.\n"
                                +
                                "You think before you act, and you'd rather do things your own way than follow someone else.");
                Category jesse = new Category("Jesse Pinkman", "You're loyal, emotional, and a little impulsive.\n" +
                                "You feel things strongly and care a lot about others, even when your own life feels confusing.");
                Category gus = new Category("Gustavo Fring",
                                "You're calm, private, and careful about what you reveal to others.\n" +
                                                "You like plans, stability, and being the one who discreetly knows what's going on.");
                Category hank = new Category("Hank Schrader", "You're loud, direct, and don't like overthinking.\n" +
                                "You enjoy being around people, staying busy, and facing problems directly.");

                // Create Questions
                Question q1 = new Question(1, 
                                "What would be your ideal way of getting from one place to another, if time travelled and distance were all standardized?");
                // Attach Answers to Questions
                q1.possibleAnswers[0] = new Answer("Car", hank);
                q1.possibleAnswers[1] = new Answer("Walking", walter);
                q1.possibleAnswers[2] = new Answer("Bicycle", jesse);
                q1.possibleAnswers[3] = new Answer("Train or Bus", gus);

                Question q2 = new Question(2, 
                                "In which of these cities would you want to spend most of your time in and/or live in, if you could?");
                q2.possibleAnswers[0] = new Answer("Los Angeles, California", walter);
                q2.possibleAnswers[1] = new Answer("New York City, New York", hank);
                q2.possibleAnswers[2] = new Answer("Miami, Florida", gus);
                q2.possibleAnswers[3] = new Answer("Las Vegas, Nevada", jesse);

                Question q3 = new Question(3, 
                                "If you could only use one device for the rest of your life, which would you choose?");
                q3.possibleAnswers[0] = new Answer(
                                "Phone (portable, convenient, easy 5G internet connectivity, access to good camera, health data, flashlight, mobile payments, etc.)",
                                jesse);
                q3.possibleAnswers[1] = new Answer(
                                "Tablet (decently-sized touch screen, decently portable; a “middle ground” between a phone & laptop)",
                                gus);
                q3.possibleAnswers[2] = new Answer(
                                "Laptop (keyboard, multitasking, software & storage power, more connectivity to external drives, printers, etc., and a little portable)",
                                walter);
                q3.possibleAnswers[3] = new Answer(
                                "Desktop (superior performance & storage, easy multitasking, handles heavy work, massive screen, reliable over-time, not portable at all)",
                                hank);

                Question q4 = new Question(4, 
                                "Which natural light source do you most resonate with? This can mean whatever you want it to mean, just pick what feels right.");
                q4.possibleAnswers[0] = new Answer("The twinkling speckles of starlight", gus);
                q4.possibleAnswers[1] = new Answer("The silver luminescence of moonlight", walter);
                q4.possibleAnswers[2] = new Answer("The bright, golden glow of sunlight", hank);
                q4.possibleAnswers[3] = new Answer("The soft, multicolored bands of twilight", jesse);

                Question q5 = new Question(5, 
                                "What kind of weather appeals to you the most?");
                q5.possibleAnswers[0] = new Answer(
                                "A sunny, warm day with blue skies and no/minimal clouds, perfect for going outside",
                                hank);
                q5.possibleAnswers[1] = new Answer(
                                "The sky is fully covered by clouds, and the wind fluctuates from a light to strong breeze",
                                gus);
                q5.possibleAnswers[2] = new Answer(
                                "There is a slight drizzle, not enough to need an umbrella, and the droplets make your skin feel cool",
                                jesse);
                q5.possibleAnswers[3] = new Answer(
                                "It is heavily raining, and you can stay at home and listen to the sound of droplets falling, (or go outside, if you like doing that)",
                                walter);

                Question q6 = new Question(6, "What sort of environment would you best focus on your work in?");
                q6.possibleAnswers[0] = new Answer(
                                "A park where you're surrounded by nature, the feel of wind, and occasional joggers and dog-walkers",
                                jesse);
                q6.possibleAnswers[1] = new Answer(
                                "A café you frequent where there is music and the sounds of light chatter", hank);
                q6.possibleAnswers[2] = new Answer("A quiet library, filled with others doing the same thing as you",
                                walter);
                q6.possibleAnswers[3] = new Answer("Your own house or dorm where you can be alone with your thoughts",
                                gus);

                Question q7 = new Question(7, 
                                "Imagine your Saturday. During which of these times of day do you feel the best during?");
                q7.possibleAnswers[0] = new Answer(
                                "The early morning, right after you wake up; a great start to the day!", hank);
                q7.possibleAnswers[1] = new Answer("The afternoon; maybe youll go outside or something?", gus);
                q7.possibleAnswers[2] = new Answer(
                                "The evening; right after the sun sets and the sky is still (a darker) blue", jesse);
                q7.possibleAnswers[3] = new Answer(
                                "The late night, when everything is quiet but there's still activity outside", walter);

                Question q8 = new Question(8, "What type of social situation appeals to you most?");
                q8.possibleAnswers[0] = new Answer("A deep one-on-one conversation with a close friend", walter);
                q8.possibleAnswers[1] = new Answer("A small, cozy hangout with a few of your friends", jesse);
                q8.possibleAnswers[2] = new Answer(
                                "A large excursion with almost everyone in your larger group of friends", hank);
                q8.possibleAnswers[3] = new Answer("A networking event where you can make new connections", gus);

                // For each question, ask, read input, store answer.
                gameIntro();
                Question[] qList = { q1, q2, q3, q4, q5, q6, q7, q8 };
                for (Question q : qList) {
                        Category c = q.ask(sc, answerSave);
                        c.points++;
                }
                // Get most common category from the questions asked
                // Return Category
                Category[] cList = { walter, hank, jesse, gus };
                // these need to be in the same order or the points will be incorrect!
                int index = getMostPopularCatIndex(cList);

                // tie method
                if (isTie(cList, index)) {
                        System.out.println("\nIt's a tie! Answer this question to see your result...\n");

                        int maxPoints = cList[index].points;
                        Category[] tiedCharacters = new Category[cList.length];
                        int tiedCount = 0;

                        for (int i = 0; i < cList.length; i++) {
                                if (cList[i].points == maxPoints) {
                                        tiedCharacters[tiedCount] = cList[i];
                                        tiedCount++;
                                }
                        }

                        Category c = askTiebreaker(tiedCharacters, tiedCount, sc);
                        c.points++;
                        index = getMostPopularCatIndex(cList);
                }

                System.out.println("If you were a lead character from Breaking Bad, you would be " + cList[index].label
                                + ". ");
                System.out.println(cList[index].description);
                fileSave.incrementCategoryCount(cList[index].label);
                lastSave.updateResult("mostRecentCategory", cList[index].label);
                } while (gameConclusion());

        }
        public static boolean gameConclusion() {
                System.out.println("Play again? Enter '1' to play again, or any other number to quit.");
                int play = sc.nextInt();
                sc.nextLine();
                return play == 1;
        }

        public static void gameIntro() {
                // requires 1 to keep going
                System.out.println("Which lead “Breaking Bad” character are you?");
                System.out.println("You get to choose numbers 1-4 for every question. Enter '1' to play!");
                int play = sc.nextInt();
                if (play != 1) {
                        System.out.println("Unidentifiable input. Please enter '1' to play");
                        gameIntro();
                }
        }

        // returns the index that is the max
        // the tie breaker is the first Category that has the count is the "max" :/
        public static int getMostPopularCatIndex(Category[] counts) {
                int maxCount = 0;
                int maxIndex = 0;
                for (int i = 0; i < counts.length; i++) {
                        if (counts[i].points > maxCount) {
                                maxCount = counts[i].points;
                                maxIndex = i;
                        }
                }
                return maxIndex;
        }

        // tie checker
        public static boolean isTie(Category[] counts, int maxIndex) {
                int maxCount = counts[maxIndex].points;
                int tieCount = 0;
                for (int i = 0; i < counts.length; i++) {
                        if (counts[i].points == maxCount) {
                                tieCount++;
                        }
                }
                return tieCount > 1;
        }

        // this asks the tiebreaker so that only the tied characters show up
        public static Category askTiebreaker(Category[] tiedCharacters, int tiedCount, Scanner sc) {
                System.out.println("Someone you're working with completely messes up something important. What do you do?");

                String[] answers = new String[tiedCount];
                for (int i = 0; i < tiedCount; i++) {
                        if (tiedCharacters[i].label.equals("Walter White")) {
                                answers[i] = "Say it's fine, feel annoyed inside, and correct it on your own later";
                        } else if (tiedCharacters[i].label.equals("Hank Schrader")) {
                                answers[i] = "Take control, complain a bit, and try to fix it yourself";
                        } else if (tiedCharacters[i].label.equals("Jesse Pinkman")) {
                                answers[i] = "Feel bad for them, stress out a little, and help even if it wasn't your fault";
                        } else if (tiedCharacters[i].label.equals("Gustavo Fring")) {
                                answers[i] = "Stay calm, make a new plan, and quickly get everyone back on track";
                        }
                }

                for (int i = 0; i < tiedCount; i++) {
                        System.out.println("[" + (i + 1) + "]: " + answers[i]);
                }

                while (true) {
                        if (!sc.hasNextInt()) {
                                sc.next();
                                System.out.println("That's not an answer, silly. Please enter a number between 1 and " + tiedCount + ".");
                                continue;
                        }

                        int choice = sc.nextInt();
                        if (choice >= 1 && choice <= tiedCount) {
                                return tiedCharacters[choice - 1];
                        }

                        System.out.println("That's not an answer, silly. Please enter a number between 1 and " + tiedCount + ".");
                }
        }

}