package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WallSegment {

    private List<Coordinate> wallSegment = new ArrayList<>();
    //private boolean goingDown;


    public List<Coordinate> getWallSegment() {
        return wallSegment;
    }

    public WallSegment (int x, int y) {
        Coordinate coord = new Coordinate(x, y);
        coord.setColor(99);
        wallSegment.add(coord);
    }

    public WallSegment(int x) {
        int randomNum = randomNum(20);
        for (int y = 0; y < 30; y++) {
            if (!(y>randomNum && y<randomNum+7)) {
                Coordinate coord = new Coordinate(x, y);
                coord.setColor(99);
                wallSegment.add(coord);
            }

        }
    }

    public void moveSegment(int max) {
        for(int i = 0; i<wallSegment.size(); i++)
            if(wallSegment.get(i).getY() != 0 && wallSegment.get(i).getY() != 40) {
                //if (wallSegment.get(i).getX() == 0) {
                  //  wallSegment.get(i).move((max + 4), 0);
                //} else {
                    wallSegment.get(i).move(-1, 0);
                    /*int y;
                    if(wallSegment.get(0).getY() == 0){
                        goingDown = false;
                        y = -1;
                    }else if(wallSegment.get(0).getY() == -3){
                        goingDown = true;
                        y = 1;
                    }else if(goingDown){
                        y = 1;
                    }else{
                        y = -1;
                    }
                    wallSegment.get(i).move(0, y);*/
                //}
            }
    }

    public int randomNum (int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);

    }
}
