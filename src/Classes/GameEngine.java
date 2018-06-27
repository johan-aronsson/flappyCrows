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
    private String filepath = "Resource/";//"C:/Users/Administrator/Documents/Java/flappyCrows/Resource/";
    private int oldScore = 0;
    private List<Integer> highScore = new ArrayList<>();

    public GameEngine() {
        renderer = new Renderer();
        gameLogic = new GameLogic();
        crow = new Crow();
        map = new Map();
        soundEngine = new SoundEngine();
        soundEngine.play(filepath + "8-bit-music.mp3", true);
        if(Files.exists(Paths.get(filepath+"/score.txt"))){
            try {
                Scanner sc = new Scanner(Paths.get(filepath + "/score.txt"));
                while(sc.hasNext()){
                    highScore.add(sc.nextInt());

                }
            }catch(Exception e){
                System.out.println("IO EXCEPTION!");
            }

            for(int i : highScore){
                System.out.println(i);
            }

        }
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
            soundEngine.play( filepath + "sfx_die.wav");

            checkHighScore();
            gameOn = false;
        }
    }

    private void checkHighScore() {
        if(highScore.size() > 0){
            for(int i = 0; i< highScore.size();i++){
                if(crow.getScore() >= i){
                    highScore.add(i, crow.getScore());
                    i = highScore.size();
                }
            }
        }else{
            highScore.add(crow.getScore());
        }
        StringBuilder highScoreString = new StringBuilder();
        for(int i : highScore){
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
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println("Thread issue");
            }
            soundEngine.play(filepath + "sfx_wing.wav");
            gameLogic.setKey(key);
        }
    }
}
