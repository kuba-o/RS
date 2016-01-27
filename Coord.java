public class Coord {
    String name;
    double x;
    double y;
    int delayTime;
    char mouseButton;

    public Coord(){}
    
    public Coord(double _x, double _y){
        this.x = _x;
        this.y = _y;
    }
    public Coord(String loc, int xCoordinate, int yCoordinate, int delTime, char mouseButtonPressed){
        this.name = loc;
        this.x = xCoordinate;
        this.y = yCoordinate;
        this.delayTime = delTime;
        this.mouseButton = mouseButtonPressed;
    }
}