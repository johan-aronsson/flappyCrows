package Classes;

import com.googlecode.lanterna.input.Key;

public class GameLogic {
    private Key key;

    public boolean tick(Crow crow, Map map) {
        if (key != null) {
            crow.updateLocation(true);
        } else {
            crow.updateLocation(false);
        }
        key = null;

        map.updateLocation(crow);
        return checkForCollision(crow, map);
    }

    private boolean checkForCollision(Crow crowCoordinate, Map map) {
        for (WallSegment wall : map.getWalls()) {
            for (Coordinate coor : wall.getWallSegment()) {
                if (crowCoordinate.getCoordinate().getX() == coor.getX() &&
                        crowCoordinate.getCoordinate().getY() == coor.getY()) {
                    return false;
                }

            }

        }
        if (crowCoordinate.getCoordinate().getY() < 1 || crowCoordinate.getCoordinate().getY() > 30) {
            return false;
        }
        return true;

    }

    public void setKey(Key key) {
        this.key = key;
    }
}
