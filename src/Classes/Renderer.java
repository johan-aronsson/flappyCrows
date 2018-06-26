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
        /*for (Drawable g : objects) {
            terminal.applyBackgroundColor(0);
            //terminal.moveCursor(g.getX(), g.getY());
            //terminal.putCharacter(g.getSymbol());
        }*/
    }
    public void renderMap(Map map){

    }
}