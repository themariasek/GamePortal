import java.io.File;

import BuzzFeedQuizc.Quiz;
import Game.Game;

public class BuzzFeedQuizGame implements Game {
    @Override
    public String getGameName() {
        return "BuzzFeed Quiz";
    }

    @Override
    public void play() {
        try {
            Quiz.main(new String[0]);
        } catch (Exception e) {
            System.out.println("Could not start BuzzFeed Quiz: " + e.getMessage());
        }
    }

    @Override
    public String getScore() {
        return "N/A";
    }

    @Override
    public void writeHighScore(File f) {
        //
    }
}
