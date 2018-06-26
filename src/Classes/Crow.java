package Classes;


public class Crow extends Drawable{

    Coordinate coordinate;


    public void updateLocation (Boolean keyPressed) {
        if (keyPressed) {
            coordinate.move(0, -1);
        }
    }

    public Crow () {
        coordinate = new Coordinate(15, 20, 4);
    }

    public Coordinate getCoordinate () {
        return coordinate;
    }

}
