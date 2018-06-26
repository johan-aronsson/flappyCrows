package Classes;


public class Crow extends Drawable{

    private Coordinate coordinate;
    private int countUpdateLocation = 0;

    public void updateLocation (Boolean keyPressed) {
        if (keyPressed) {
            coordinate.move(0, -1);
            countUpdateLocation = 0;
        } else if (countUpdateLocation > 5) {
            coordinate.move(0, 1);
            countUpdateLocation = 0;
        }
        countUpdateLocation++;
    }

    public Crow () {
        coordinate = new Coordinate(15, 20, 4);
    }

    public Coordinate getCoordinate () {
        return coordinate;
    }
}
