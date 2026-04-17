import java.io.File;

import BuzzFeedQuizc.Quiz;
import Game.GameWriteable;

public class BuzzFeedQuizGame implements GameWriteable {
    private int latestScore = 0;
    private boolean wroteDuringPlay = false;
    private boolean suppressNextPortalWrite = false;

    @Override
    public String getGameName() {
        return "BuzzFeed Quiz";
    }

    @Override
    public void play() {
        wroteDuringPlay = false;
        suppressNextPortalWrite = false;
        Quiz.setRoundCompleteHook(() -> {
            latestScore = Quiz.getLatestScore();
            wroteDuringPlay = true;
            writeHighScoreInternal(new File("Highscore.csv"));
        });
        try {
            Quiz.main(new String[0]);
            latestScore = Quiz.getLatestScore();
        } catch (Exception e) {
            System.out.println("Could not start BuzzFeed Quiz: " + e.getMessage());
            latestScore = 0;
        } finally {
            Quiz.setRoundCompleteHook(null);
            suppressNextPortalWrite = wroteDuringPlay;
        }
    }

    @Override
    public String getScore() {
        return Integer.toString(latestScore);
    }

    @Override
    public boolean isHighScore(String score, String currentHighScore) {
        if (currentHighScore == null) {
            return true;
        }

        try {
            int newScore = Integer.parseInt(score.trim());
            int oldScore = Integer.parseInt(currentHighScore.trim());
            return newScore > oldScore;
        } catch (NumberFormatException ex) {
            return true;
        }
    }

    @Override
    public void writeHighScore(File f) {
        if (suppressNextPortalWrite) {
            suppressNextPortalWrite = false;
            return;
        }
        writeHighScoreInternal(f);
    }

    private void writeHighScoreInternal(File f) {
        GameWriteable.super.writeHighScore(f);
    }
}
