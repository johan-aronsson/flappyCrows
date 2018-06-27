package Classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Map {

    private List<WallSegment> walls = new ArrayList<>();
    private List<WallSegment> floorAndRoof = new ArrayList<>();

    private int updateCounter = 0;
    private int speed = 0;

    public Map() {
        for (int i = 0; i <= 100; i++) {
            if (i % 10 == 0) {
                WallSegment ws = new WallSegment(i);
                walls.add(ws);
            }

            WallSegment ws = new WallSegment(i, 0);
            floorAndRoof.add(ws);
            WallSegment ws2 = new WallSegment(i, 30);
            floorAndRoof.add(ws2);

        }
    }

    public void updateLocation(Crow crow) {
        if (updateCounter > 10) {
            int max = walls.get(walls.size() - 1).getWallSegment().get(0).getX();
            for (int i = 0; i < walls.size(); i++) {
                if(crow.getCoordinate().getX() == walls.get(i).getWallSegment().get(1).getX()){
                    crow.addScore(1);
                    if (crow.getScore()%3 == 0 && speed<8) {
                        speed++;
                    }
                }
                walls.get(i).moveSegment(max);

            }

            if (walls.get(0).getWallSegment().get(0).getX() > 4) {
                WallSegment temp = walls.get(0);
                walls.remove(0);
                walls.add(temp);

            }

            updateCounter = speed;
        }
        updateCounter++;

    }

    public List<WallSegment> getWalls() {
        return walls;
    }
    public List<WallSegment> getFloorAndRoof() {
        return floorAndRoof;
    }



}
