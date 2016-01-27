import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.MouseInfo;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.InputEvent;
import java.util.*;

public class Bot{
	public static void main(String[] args) {
		ArrayList<Coord> pointList = new ArrayList<Coord>();
		ArrayList<Coord> detour = new ArrayList<Coord>();
		ArrayList<Coord> trajectory = new ArrayList<Coord>();
		RobotWrapper robot = new RobotWrapper();
		CoordsReader coordsReader = new CoordsReader();
		LinesCounter linesCounter = new LinesCounter();
		String fileName = "coords.txt";
		GeometricTools geometricTools = new GeometricTools();

		int numberOfLines = linesCounter.calculateLines(fileName);

		coordsReader.readCoords(pointList, fileName, numberOfLines);
		while(true){
			robot.moveToStart(pointList.get(0), geometricTools);
			for (int k = 0; k<pointList.size()-1; k++){
				robot.pressTheButton(pointList.get(k).mouseButton);
				geometricTools.calculateRandomPointsBetween(pointList.get(k), pointList.get(k+1),detour);
				geometricTools.calculateSlopes(pointList.get(k), pointList.get(k+1));
				double xCoord;
				double yCoord;
				trajectory.clear();
				trajectory.add(new Coord(detour.get(0).x, detour.get(0).y));
				for (int i =1; i<detour.size()-1; i++){
					geometricTools.getShitDone(detour.get(i), detour.get(detour.size()-1), detour.get(i+1), trajectory);
				}
				trajectory.add(new Coord(detour.get(detour.size()-1).x, detour.get(detour.size()-1).y));

				robot.delay(pointList.get(k).delayTime);

				for (int i=0; i<trajectory.size()-1 ;i++){
					robot.smoothMove(trajectory.get(i), trajectory.get(i+1), geometricTools);
				}
				robot.move(trajectory.get(trajectory.size()-1));
			}
			robot.pressTheButton(pointList.get(pointList.size()-1).mouseButton);
			robot.delay(pointList.get(pointList.size()-1).delayTime);
		}
	}
}