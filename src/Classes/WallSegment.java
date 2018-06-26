package Classes;

import java.util.ArrayList;
import java.util.List;

public class WallSegment {

    private List<Coordinate> wallSegment = new ArrayList<>();

    public List<Coordinate> getWallSegment() {
        return wallSegment;
    }

    public WallSegment(int x) {
        for (int y = 0; y < 45; y++) {
            Coordinate coord = new Coordinate(x, y);
            coord.setColor(99);
            wallSegment.add(coord);


        }

    }
}
