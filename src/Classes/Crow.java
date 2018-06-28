package Classes;


import java.util.ArrayList;
import java.util.List;

public class Crow {

    private Coordinate coordinate;
    private int countUpdateLocation = 0;
    private int acceleration = 1;
    private int highScore = 0;
    private List<Coordinate> figure = new ArrayList<>();

    public Crow () {
        coordinate = new Coordinate(15, 10, 2);
        initializeFigure();
    }

    private void initializeFigure() {
        figure.add(new Coordinate(coordinate.getX()-1,coordinate.getY(), 4));
        figure.add(new Coordinate(coordinate.getX()+1,coordinate.getY(), 4));
        figure.add(new Coordinate(coordinate.getX(),coordinate.getY()-1, 4));
        figure.add(new Coordinate(coordinate.getX(),coordinate.getY()+1, 4));
    }

    public void updateLocation (Boolean keyPressed) {
        if (keyPressed) {
            goUp();
        } else if (countUpdateLocation > 3) {
            fallDown();
        }
        countUpdateLocation++;
    }

    private void goUp() {
        coordinate.move(0, -1);
        for(int i = 0; i<figure.size();i++){
            figure.get(i).move(0,-1);
        }
        figure.get(0).setColor(4);
        figure.get(1).setColor(4);
        countUpdateLocation = 0;
        acceleration = 1;
    }

    private void fallDown() {
        for(int i = 0;i<figure.size();i++){
            figure.get(i).move(0,acceleration);
        }
        figure.get(0).setColor(0);
        figure.get(1).setColor(0);
        coordinate.move(0, acceleration++);

        countUpdateLocation = 0;
    }

    public Coordinate getCoordinate () {
        return coordinate;
    }
    public int getScore(){
        return highScore;
    }
    public void addScore(int score){
        highScore += score;
    }
    public List<Coordinate> getFigure(){
        return figure;
    }
}
