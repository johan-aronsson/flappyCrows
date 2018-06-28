package Classes;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<WallSegment> walls = new ArrayList<>();
    private List<WallSegment> floorAndRoof = new ArrayList<>();
    private int updateCounter = 0;
    private int speed = 0;

    public Map() {
        createRoofFloor(0);
        for (int i = 1; i <= 100; i++) {
            if (i % 10 == 0) {
                WallSegment ws = new WallSegment(i);
                walls.add(ws);
            }
            createRoofFloor(i);
        }
    }

    private void createRoofFloor(int x){
        WallSegment ws = new WallSegment(x, 0);
        floorAndRoof.add(ws);
        WallSegment ws2 = new WallSegment(x, 30);
        floorAndRoof.add(ws2);
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

            if (walls.get(0).getWallSegment().get(4).getX() <= 0) {
                walls.get(0).getWallSegment().clear();
                walls.remove(0);
                walls.add(new WallSegment(max));
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
