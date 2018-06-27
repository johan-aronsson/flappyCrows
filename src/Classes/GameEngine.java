package Classes;

import com.googlecode.lanterna.input.Key;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Renderer renderer;
    private GameLogic gameLogic;
    private Map map;
    private Crow crow;
    private SoundEngine soundEngine;
    private boolean playerAlive = true;
    private boolean gameOn = true;
    private String filepath = "C:/Users/Administrator/Documents/Java/flappyCrows/Resource/";
    private int oldScore = 0;
    private List<Integer> highScore = new ArrayList<>();

    public GameEngine() {
        renderer = new Renderer();
        gameLogic = new GameLogic();
        crow = new Crow();
        map = new Map();
        soundEngine = new SoundEngine();
        soundEngine.play(filepath + "8-bit-music.mp3", true);
    }

    public void tick() {
        if (gameOn) {
            while (playerAlive) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    System.out.println("Main thread sleep error");
                }

                checkInput();
                playerAlive = gameLogic.tick(crow, map);
                renderer.render(crow, map);

                checkScoreChanged();
            }
            renderer.renderDeathScreen();
            soundEngine.stopAll();
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                System.out.println("Main thread sleep error");
            }
            soundEngine.play(filepath + "sfx_hit.wav");
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println("Main thread sleep error");
            }
            soundEngine.play(filepath + "sfx_die.wav");
            gameOn = false;
        }
    }

    private void checkScoreChanged() {
        if (crow.getScore() > oldScore) {
            soundEngine.play(filepath + "sfx_point.wav");
            oldScore = crow.getScore();
        }
    }

    private void checkInput() {
        Key key;
        key = renderer.getTerminal().readInput();
        if (key != null) {
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                System.out.println("Thread issue");
            }
            soundEngine.play(filepath + "sfx_wing.wav");
            gameLogic.setKey(key);
        }
    }
}
