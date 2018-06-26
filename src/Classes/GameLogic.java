package Classes;

import com.googlecode.lanterna.input.Key;

public class GameLogic {
    private Key key;
    public void tick(Crow crow, Map map) {
        if(key != null) {
            crow.updateLocation(true);
        }else{
            crow.updateLocation(false);
        }
        key = null;

        map.updateLocation();
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
