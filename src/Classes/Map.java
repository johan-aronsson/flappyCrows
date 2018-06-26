package Classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Map {

    List<WallSegment> walls = new ArrayList<>();

    public Map (){
        WallSegment ws = new WallSegment(4);
        walls.add(ws);
    }
    public List<WallSegment> getWalls (){
        return walls;
    }
}
