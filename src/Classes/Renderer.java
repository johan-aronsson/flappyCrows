package Classes;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import java.nio.charset.Charset;
import java.util.List;


public class Renderer {
    private static Terminal terminal;
    public static TerminalSize terminalSizes;

    public Renderer() {
        terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);
        terminalSizes = terminal.getTerminalSize();
    }

    public void renderGame(Crow crow, Map map) {
        terminal.clearScreen();
        renderCrow(crow);
        renderMap(map);
        renderScore(crow);
    }

    private void renderCrow(Crow crow) {
        terminal.applyBackgroundColor(crow.getCoordinate().getColor());
        terminal.moveCursor(crow.getCoordinate().getX(), crow.getCoordinate().getY());
        terminal.putCharacter(' ');

        for(int i = 0; i<crow.getFigure().size();i++){
            Coordinate current = crow.getFigure().get(i);
            terminal.applyBackgroundColor(current.getColor());
            terminal.moveCursor(current.getX(),current.getY());
            terminal.putCharacter(' ');
        }
    }

    private void renderMap(Map map) {
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

    private void renderScore(Crow crow) {
        String score = "" + crow.getScore();
        for(int i = 0; i < score.length(); i++){
            terminal.moveCursor(5+i,0);
            terminal.putCharacter(score.charAt(i));
        }
    }

    public void renderMenu(Menu menu){
        String start = "Start Game";
        String highScore = "Show HighScore";
        String quit = "Quit Game";
        boolean startDone = false,scoreDone=false,quitDone=false;
        int startCounter = 0,scoreCounter=0,quitCounter=0;
        for(Coordinate c: menu.getCoordinates()){
            terminal.applyBackgroundColor(1);
            if(menu.getHighLightedRow() == c.getY()){
                terminal.applyBackgroundColor((2));
            }
            terminal.moveCursor(c.getX(),c.getY());
            if(c.getY()== (Renderer.terminalSizes.getRows()/2)-8 && !startDone){
                terminal.putCharacter(start.charAt(startCounter++));
                if(startCounter == start.length()){
                    startCounter = 0;
                    startDone = true;
                }
            }else if(c.getY() == (Renderer.terminalSizes.getRows()/2)-6 && !scoreDone){
                terminal.putCharacter(highScore.charAt(scoreCounter++));
                if(scoreCounter == highScore.length()){
                    scoreCounter = 0;
                    scoreDone=true;
                }

            }else if(c.getY() == (Renderer.terminalSizes.getRows()/2)-4 && !quitDone){
                terminal.putCharacter(quit.charAt(quitCounter++));
                if(quitCounter == quit.length()){
                    quitCounter = 0;
                    quitDone=true;
                }
            }else{
                terminal.putCharacter(' ');
            }
        }
    }

    public Terminal getTerminal() {
        return this.terminal;
    }

    public void renderDeathScreen() {
        terminal.applyBackgroundColor(1);
        String gameOver = "Game Over";
        for (int i = 0; i <gameOver.length() ; i++) {
            terminal.moveCursor(terminalSizes.getColumns()/2+i-5,terminalSizes.getRows()/2-1);
            terminal.putCharacter(gameOver.charAt(i));
        }
    }

    public void renderHighScore(List<Integer> highscore) {
        for(int i = highscore.size()-1; i >=0;i--) {
            String score = highscore.get(i).toString();
            for (int j = 0; j < score.length(); j++) {
                terminal.moveCursor(Renderer.terminalSizes.getColumns() / 2 + j, Renderer.terminalSizes.getRows() / 2 + 2-i);
                terminal.putCharacter(score.charAt(j));
            }
        }
    }
}


