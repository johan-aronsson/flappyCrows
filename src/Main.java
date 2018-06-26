import Classes.GameEngine;

public class Main {
    private static boolean keepPlaying = true;
    private static GameEngine engine;
    public static void main(String[] arg) {
        initializeGame();
        while(keepPlaying){
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                System.out.println(e);
            }
            keepPlaying = engine.tick();
        }
    }

    private static void initializeGame() {
        engine = new GameEngine();
    }
}
