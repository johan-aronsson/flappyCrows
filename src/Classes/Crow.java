package Classes;


public class Crow extends Drawable{

    private Coordinate coordinate;
    private int countUpdateLocation = 0;
    private int acceleration = 1;
    private int highScore = 0;

    public void updateLocation (Boolean keyPressed) {
        if (keyPressed) {
            coordinate.move(0, -1);
            countUpdateLocation = 0;
            acceleration = 1;
        } else if (countUpdateLocation > 3) {
            coordinate.move(0, acceleration++);
            countUpdateLocation = 0;
        }
        countUpdateLocation++;
    }

    public Crow () {
        coordinate = new Coordinate(15, 10, 4);
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
}
