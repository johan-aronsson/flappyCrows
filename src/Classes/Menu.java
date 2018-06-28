package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private List<Coordinate> coordinates = new ArrayList<>();
    private int highLightedRow;
    Coordinate start;

    public Menu() {
        start = new Coordinate(Renderer.terminalSizes.getColumns() / 2 - 15, Renderer.terminalSizes.getRows() / 2 - 10);
        int counter = 0;

        for (int x = 0; x < 30; x++) {
            for(int y = 0; y < 20; y++){
                coordinates.add(new Coordinate(start.getX()+x,start.getY()+y));
            }
        }
        highLightedRow = start.getY()+2;
    }

    public void goUp() {
        highLightedRow -=2;
        if(highLightedRow ==start.getY()){
            highLightedRow = start.getY()+6;
        }
    }

    public void goDown() {
        highLightedRow +=2;
        if(highLightedRow ==start.getY()+8){
            highLightedRow = start.getY() + 2;
        }
    }

    public String currentChoice() {
        if(highLightedRow == start.getY()+2) {
            return "Start Game";
        }else if(highLightedRow == start.getY()+4){
            return "HighScore";
        }else if(highLightedRow == start.getY()+6){
            return "Quit Game";
        }else {
            return "";
        }
    }

    public List<Coordinate> getCoordinates(){
        return coordinates;
    }
    public int getHighLightedRow(){
        return highLightedRow;
    }
}
