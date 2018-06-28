package Classes;

import com.googlecode.lanterna.input.Key;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameEngine {
    private Renderer renderer;
    private GameLogic gameLogic;
    private Map map;
    private Crow crow;
    private SoundEngine soundEngine;
    private boolean playerAlive = true;
    private String filepath = "Resource/";
    private int oldScore = 0;
    private List<Integer> highScore = new ArrayList<>();
    private Menu menu;
    private boolean showMenu = true;
    private boolean showHighScore;

    public GameEngine() {
        renderer = new Renderer();
        gameLogic = new GameLogic();
        soundEngine = new SoundEngine();
        menu = new Menu();
        soundEngine.play(filepath + "8-bit-music.mp3", true);
        loadHighScore();
    }

    private void loadHighScore() {
        if(Files.exists(Paths.get(filepath+"/score.txt"))){
            try {
                Scanner sc = new Scanner(Paths.get(filepath + "/score.txt"));
                while(sc.hasNextInt()){
                    highScore.add(sc.nextInt());
                }
            }catch(Exception e){
                System.out.println("IO EXCEPTION!");
            }
        }
    }

    public void tick() {
        doMenuLoop();
        doGameLoop();
        playDeathSounds();
        checkHighScore();
        showMenu = true;
    }

    private void doMenuLoop() {
        while (showMenu) {
            delay(50);
            renderer.renderMenu(menu);
            if (showHighScore) {
                renderer.renderHighScore(highScore);
            }
            if (!playerAlive) {
                renderer.renderDeathScreen();
            }
            handleMenuInput();
        }
    }

    private void handleMenuInput() {
        Key key;
        key = renderer.getTerminal().readInput();
        if (key != null) {
            delay(4);
            if (key.getKind() == Key.Kind.ArrowUp) {
                menu.goUp();

            } else if (key.getKind() == Key.Kind.ArrowDown) {
                menu.goDown();

            } else if (key.getKind() == Key.Kind.Enter) {
                switch (menu.currentChoice()) {
                    case "Start Game":
                        playerAlive = true;
                        showMenu = false;
                        showHighScore = false;
                        crow = new Crow();
                        map = new Map();
                        break;
                    case "HighScore":
                        showHighScore = true;
                        break;
                    case "Quit Game":
                        System.exit(0);
                        break;
                }
            }
        }
    }

    private void doGameLoop() {
        while (playerAlive) {
            delay(50);
            checkPlayerInput();
            playerAlive = gameLogic.tick(crow, map);
            renderer.renderGame(crow, map);
            checkScoreChanged();
        }
    }

    private void checkPlayerInput() {
        Key key;
        key = renderer.getTerminal().readInput();
        if (key != null) {
            delay(4);
            soundEngine.play(filepath + "sfx_wing.wav");
            gameLogic.setKey(key);
        }
    }

    private void checkScoreChanged() {
        if (crow.getScore() > oldScore) {
            soundEngine.play(filepath + "sfx_point.wav");
            oldScore = crow.getScore();
        }
    }

    private void playDeathSounds() {
        soundEngine.stopAll();
        delay(5);
        soundEngine.play(filepath + "sfx_hit.wav");
        delay(10);
        soundEngine.play(filepath + "sfx_die.wav");
    }

    private void checkHighScore() {
        boolean highscoreAdded = false;
        for(int i = 0; i< highScore.size();i++){
            if(crow.getScore() > highScore.get(i)){
                highScore.add(i, crow.getScore());
                highscoreAdded = true;
                if(highScore.size()==4) {
                    highScore.remove(highScore.size() - 1);
                }
                break;
            }
        }
        if(highScore.size() <3 && !highscoreAdded) {
            highScore.add(crow.getScore());
            highscoreAdded = true;
        }
        if(highscoreAdded) {
            saveHighScore();
        }
    }

    private void saveHighScore() {
        StringBuilder highScoreString = new StringBuilder();
        for(int i = 0; i<highScore.size();i++){
            highScoreString = highScoreString.append(highScore.get(i) +"\n");
        }
        String scoreString = highScoreString.toString();
        try {
            Files.write(Paths.get(filepath + "score.txt"), scoreString.getBytes(), StandardOpenOption.CREATE);
        }catch(Exception e){
            System.out.println("File write exception");
        }
    }

    private void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            System.out.println("thread sleep error");
        }
    }
}
