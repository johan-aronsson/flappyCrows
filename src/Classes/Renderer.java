package Classes;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class Renderer {
    private Terminal terminal;
    //public static Coordinate windowMax;

    public Renderer() {
        terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);
       // windowMax = new Coordinate(terminal.getTerminalSize().getColumns(), terminal.getTerminalSize().getRows());
    }

    public void renderCrow(Crow crow) {
        terminal.clearScreen();
        //for (Drawable g : objects) {
        terminal.applyBackgroundColor(crow.getCoordinate().getColor());
        terminal.moveCursor(crow.getCoordinate().getX(), crow.getCoordinate().getY());
        terminal.putCharacter(' ');
        //}
    }
    public void renderMap(Map map){
        for (int i = 0; i <map.getWalls().size() ; i++) {
            for (Coordinate coor: map.getWalls().get(i).getWallSegment()){
                terminal.applyBackgroundColor(coor.getColor());
                terminal.moveCursor(coor.getX(), coor.getY());
                terminal.putCharacter(' ');
            }

        }

    }
    public Terminal getTerminal() {
        return this.terminal;
    }
}

