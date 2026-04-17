import java.io.File;

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
        App.main(new String[0]);
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
