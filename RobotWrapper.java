import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.MouseListener;

public class RobotWrapper {
    private final Robot robot;
    Coord actual = new Coord();
    public RobotWrapper() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void delay(int del){
        robot.delay(del);
    }

    public void move(Coord end){
        robot.mouseMove((int)end.x, (int)end.y);
        robot.delay(2);
    }

    public void smoothMove(Coord start, Coord end, GeometricTools geometricTools){
        actual.x = start.x;
        actual.y = start.y;

        int startX = (int)start.x;
        int startY = (int)start.y;
        int endX = (int)end.x;
        int endY = (int)end.y;
        int actualX=(int)start.x;
        int actualY;
        for (int i=0; i<Math.abs(start.x - end.x); i++){
            if (startX>endX){
                actualX--;
            }
            else{
                actualX++;
            }
            actualY = (int)geometricTools.countY(start, end, actualX);
            move(new Coord(actualX, actualY));
        }
    }

    public void pressTheButton(char c){
        if (c == 'L'){
            pressLeft();
        }

        if(c == 'R'){
            pressRight();
        }
    }

    public void pressLeft(){
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(150);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(100);
    }

    public void pressRight(){
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(150);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.delay(100);
    }

    public void moveToStart(Coord start, GeometricTools geometricTools){
        // move(start);
        smoothMove(new Coord(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y), start, geometricTools);
        pressLeft();
    }
}