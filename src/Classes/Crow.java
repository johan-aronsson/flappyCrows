package Classes;


public class Crow extends Drawable{

    Coordinate coordinate;


    public void updateLocation (Boolean keyPressed) {
        if (keyPressed) {
            coordinate.setY(coordinate.getY()+1);
        }
    }

    public Crow () {
        coordinate = new Coordinate(15, 45, 5);
    }

    public Coordinate getCoordinate () {
        return coordinate;
    }

}
