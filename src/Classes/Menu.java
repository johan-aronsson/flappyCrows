package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private List<Coordinate> coordinates = new ArrayList<>();

    public Menu() {
        Coordinate start = new Coordinate(Renderer.terminalSizes.getColumns() / 2 - 15, Renderer.terminalSizes.getRows() / 2 - 10);
        int counter = 0;
        for (int x = 0; x < 30; x++) {
            for(int y = 0; y < 20; y++){
                coordinates.add(new Coordinate(start.getX()+x,start.getY()+y));
            }
        }
    }

    public void goUp() {
    }

    public void goDown() {
    }

    public String currentChoice() {
        return "Start Game";
    }

    public List<Coordinate> getCoordinates(){
        return coordinates;
    }
}
