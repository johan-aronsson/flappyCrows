import Classes.GameEngine;

public class Main {
    private static boolean keepPlaying = true;
    private static GameEngine engine;
    public static void main(String[] arg) {
        initializeGame();
        while(keepPlaying){
            keepPlaying = engine.tick();
        }
    }

    private static void initializeGame() {
        engine = new GameEngine();
    }
}
