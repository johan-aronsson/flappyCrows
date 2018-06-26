package Classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Map {

    private List<WallSegment> walls = new ArrayList<>();
    private int updateCounter = 0;

    public Map() {
        WallSegment ws = new WallSegment(4);
        walls.add(ws);
    }

    public List<WallSegment> getWalls() {
        return walls;
    }

    public void updateLocation() {
        if(updateCounter > 10) {
            for (WallSegment ws : walls) {
                ws.moveSegment();
            }
            WallSegment temp = walls.get(0);
            walls.remove(0);
            walls.add(temp);
            updateCounter = 0;
        }
        updateCounter++;
    }



}
