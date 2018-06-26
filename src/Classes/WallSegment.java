package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WallSegment {

    private List<Coordinate> wallSegment = new ArrayList<>();

    public List<Coordinate> getWallSegment() {
        return wallSegment;
    }

    public WallSegment(int x) {
        int randomNum = randomNum(40);
        for (int y = 0; y < 45; y++) {
            if (!(y>randomNum && y<randomNum+5)) {
                Coordinate coord = new Coordinate(x, y);
                coord.setColor(99);
                wallSegment.add(coord);
            }

        }

    }

    public void moveSegment(int max) {
        for(int i = 0; i<wallSegment.size(); i++)
            if(wallSegment.get(i).getX() == 0){
                wallSegment.get(i).move(max+4, 0);
            }else {
                wallSegment.get(i).move(-1, 0);
            }
    }

    public int randomNum (int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);

    }
}
