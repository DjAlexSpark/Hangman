package io.github.djalexspark.viselitsa;

public class GameWithHints extends Game {

    public GameWithHints() {
        super();
    }

    @Override
    public void start() {
        setShowHints(true);
        super.start();
    }
}