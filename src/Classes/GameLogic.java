package Classes;

import com.googlecode.lanterna.input.Key;

public class GameLogic {
    Key key;
    public void tick(Crow crow, Map map) {
        crow.updateLocation();
    }

    public void setKey(Key key) {
        this.key = key;
    }


}
