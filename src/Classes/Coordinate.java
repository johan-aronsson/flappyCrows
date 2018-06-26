package Classes;

public class Coordinate {

    private int x;
    private int y;
    private int color;




    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void move(int x, int y){
        this.x += x;
        this.y += y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Coordinate(int x, int y, int color) {
        setX(x);
        setY(y);
        setColor(color);

    }

}
