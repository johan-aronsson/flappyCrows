package Classes;

import com.googlecode.lanterna.input.Key;

public class GameEngine {
    private Renderer renderer;
    private GameLogic gameLogic;
    private Map map;
    private Crow crow;
    private SoundEngine soundEngine;
    public GameEngine(){
        renderer = new Renderer();
        gameLogic = new GameLogic();
        crow = new Crow();
        map = new Map();
        soundEngine = new SoundEngine();
    }

    public boolean tick() {
        checkInput();
        gameLogic.tick(crow,map);
        renderer.renderCrow(crow);
        renderer.renderMap(map);
        return true;
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
            gameLogic.setKey(key);
        }
    }
}
