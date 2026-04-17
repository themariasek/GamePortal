import java.io.File;
import java.util.concurrent.CountDownLatch;

import Game.Game;
import cards.App;

public class CardsGame implements Game {

    @Override
    public String getGameName() {
        return "Cards";
    }

    @Override
    public void play() {
        System.out.println("Launching Cards game window...");
        CountDownLatch latch = new CountDownLatch(1);
        
        Thread cardsThread = new Thread(() -> {
            App.main(new String[0]);
            latch.countDown();
        });
        cardsThread.start();
        
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("Cards game interrupted.");
        }
    }

    @Override
    public String getScore() {
        return "N/A";
    }

    @Override
    public void writeHighScore(File f) {
        //n/a
    }
}
