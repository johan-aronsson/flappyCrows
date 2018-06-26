package Classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Map {

    private List<WallSegment> walls = new ArrayList<>();
    private int updateCounter = 0;

    public Map() {
        for (int i = 0; i < 100; i++) {
            if (i%3==0) {
                WallSegment ws = new WallSegment(i);
                walls.add(ws);
            }

        }
    }

    public List<WallSegment> getWalls() {
        return walls;
    }

    public void updateLocation() {
        if(updateCounter > 10) {
            int max = walls.get(walls.size()-1).getWallSegment().get(0).getX();
            for (int i = 0; i < walls.size();i++) {
                walls.get(i).moveSegment(max);
            }

            if(walls.get(0).getWallSegment().get(0).getX() > 4) {
                WallSegment temp = walls.get(0);
                walls.remove(0);
                walls.add(temp);
                updateCounter = 0;
            }
        }
        updateCounter++;
    }
}
