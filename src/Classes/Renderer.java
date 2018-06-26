package Classes;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

import java.nio.charset.Charset;


public class Renderer {
    private static Terminal terminal;

    public static TerminalSize terminalSizes;

    //public static Coordinate windowMax;

    public Renderer() {
        terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);
        terminalSizes = terminal.getTerminalSize();
        // windowMax = new Coordinate(terminal.getTerminalSize().getColumns(), terminal.getTerminalSize().getRows());
    }

    public void render(Crow crow, Map map) {
        terminal.clearScreen();
        //for (Drawable g : objects) {
        terminal.applyBackgroundColor(crow.getCoordinate().getColor());
        terminal.moveCursor(crow.getCoordinate().getX(), crow.getCoordinate().getY());
        terminal.putCharacter(' ');


        renderMap(map);
        String score = "" + crow.getScore();
        for(int i = 0; i < score.length(); i++){
            terminal.moveCursor(5+i,0);
            terminal.putCharacter(score.charAt(i));
        }
        //}
    }

    public void renderMap(Map map) {
        for (int i = 0; i < map.getWalls().size(); i++) {
            for (Coordinate coor : map.getWalls().get(i).getWallSegment()) {
                terminal.applyBackgroundColor(coor.getColor());
                terminal.moveCursor(coor.getX(), coor.getY());
                terminal.putCharacter(' ');
            }

        }
        for (int i = 0; i < map.getFloorAndRoof().size(); i++) {
            for (Coordinate coor : map.getFloorAndRoof().get(i).getWallSegment()) {
                terminal.applyBackgroundColor(coor.getColor());
                terminal.moveCursor(coor.getX(), coor.getY());
                terminal.putCharacter(' ');
            }
        }
    }

    public Terminal getTerminal() {
        return this.terminal;
    }

    public void renderDeathScreen() {
        terminal.applyBackgroundColor(13);
        String gameOver = "Game Over";
        for (int i = 0; i <gameOver.length() ; i++) {
            terminal.moveCursor(30+i,20);
            terminal.putCharacter(gameOver.charAt(i));
        }
    }
}


