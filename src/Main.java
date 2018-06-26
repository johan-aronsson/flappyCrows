import Classes.GameEngine;

public class Main {
    private static boolean keepPlaying = true;
    private static GameEngine engine;
    public static void main(String[] arg) {
        initializeGame();
        doGameLoop();
    }

    private static void doGameLoop() {
        while (keepPlaying) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                System.out.println("Main thread sleep error");
            }
            keepPlaying = engine.tick();
        }
    }

    private static void initializeGame() {
        engine = new GameEngine();
    }
}
