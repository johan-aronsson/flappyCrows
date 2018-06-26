package Classes;

public class Crow extends Drawable{

    Coordinate crowCoordinate = new Coordinate();


    public void updateLocation () {
        if (keyPress) {
            crowCoordinate.setY(crowCoordinate.getY()+1);
        }
    }

}
