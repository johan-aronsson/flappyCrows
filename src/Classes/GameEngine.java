package Classes;

import com.googlecode.lanterna.input.Key;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.spec.ECField;
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
    private boolean gameOn = true;
    private String filepath = "Resource/";
    private int oldScore = 0;
    private List<Integer> highScore = new ArrayList<>();
    private Menu menu;
    private boolean inMenu = true;
    private boolean showHighScore;

    public GameEngine() {
        renderer = new Renderer();
        gameLogic = new GameLogic();
        crow = new Crow();
        map = new Map();
        soundEngine = new SoundEngine();
        menu = new Menu();
        soundEngine.play(filepath + "8-bit-music.mp3", true);
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
        if (gameOn) {
            while(inMenu){
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    System.out.println("Main thread sleep error");
                }
                renderer.render(menu);
                if(showHighScore){
                    renderer.renderHighScore(highScore);
                }
                if(!playerAlive){
                    renderer.renderDeathScreen();
                }
                Key key;
                key = renderer.getTerminal().readInput();
                if(key != null){
                    try {
                        Thread.sleep(4);
                    } catch (Exception e) {
                        System.out.println("Thread issue");
                    }
                    if(key.getKind() == Key.Kind.ArrowUp){
                        menu.goUp();

                    }else if(key.getKind() == Key.Kind.ArrowDown){
                        menu.goDown();

                    }else if(key.getKind() == Key.Kind.Enter){
                        switch(menu.currentChoice()){
                            case "Start Game":
                                playerAlive = true;
                                inMenu = false;
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
            soundEngine.play( filepath + "sfx_die.wav");

            checkHighScore();
            inMenu = true;
        }
    }

    private void checkHighScore() {
        boolean highscoreAdded = false;
        if(highScore.size() > 0){
            for(int i = 0; i< highScore.size();i++){
                if(crow.getScore() > highScore.get(i)){
                    highScore.add(i, crow.getScore());
                    //i = highScore.size();
                    highscoreAdded = true;
                    if(highScore.size()==4) {
                        highScore.remove(highScore.size() - 1);
                    }
                    break;
                }
            }
            if(highScore.size() <3 && !highscoreAdded){
                highScore.add(crow.getScore());
            }
        }else{
            highScore.add(crow.getScore());
        }
        StringBuilder highScoreString = new StringBuilder();
        for(int i = 0; i<3;i++){
            highScoreString = highScoreString.append(i +"\n");
        }
        String scoreString = highScoreString.toString();
        try {
            Files.write(Paths.get(filepath + "score.txt"), scoreString.getBytes(), StandardOpenOption.CREATE);
        }catch(Exception e){
            System.out.println("File write exception");
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
                Thread.sleep(4);
            } catch (Exception e) {
                System.out.println("Thread issue");
            }
            soundEngine.play(filepath + "sfx_wing.wav");
            gameLogic.setKey(key);
        }
    }
}
