package Classes;

public class GameEngine {
    private Renderer renderer;
    private GameLogic gameLogic;
    //private Drawable[] drawables;
    private Map map;
    private Crow crow;
    public GameEngine(){
        renderer = new Renderer();
        initializeDrawables();
    }

    private void initializeDrawables() {
        gameLogic = new GameLogic();
        crow = new Crow();
        map = new Map();
    }

    public boolean tick() {
        gameLogic.tick();
        renderer.renderCrow(crow);
        renderer.renderMap(map);
        return true;
    }
}
