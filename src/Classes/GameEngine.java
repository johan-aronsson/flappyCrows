package Classes;

import com.googlecode.lanterna.input.Key;

public class GameEngine {
    private Renderer renderer;
    private GameLogic gameLogic;
    private Map map;
    private Crow crow;
    private SoundEngine soundEngine;
    private boolean playerAlive = true;
    private boolean gameOn = true;
    String filepath = "C:/Users/Administrator/Documents/Java/flappyCrows/Resource/";
    public GameEngine(){
        renderer = new Renderer();
        gameLogic = new GameLogic();
        crow = new Crow();
        map = new Map();
        soundEngine = new SoundEngine();
        soundEngine.play(filepath +"8-bit-music.mp3");
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
            gameOn =false;
        }
    }

    private void checkInput() {
        Key key;
        key = renderer.getTerminal().readInput();
        if( key != null){
            try{
                Thread.sleep(5);
            }catch(Exception e){
                System.out.println("Thread issue");
            }
            soundEngine.play(filepath + "sfx_wing.wav");
            gameLogic.setKey(key);
        }
    }
}
