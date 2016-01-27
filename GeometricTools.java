import java.util.ArrayList;

public class GeometricTools {
	int numberOfCoords;
	double y;
	double slope;
	double perpendicularSlope;
	double theConstant;
	double countedY;
	double countedX;
	ArrayList<Double> points = new ArrayList<Double>();

	public int calculateTotalLength(Coord start, Coord end){
		return (int)Math.sqrt(Math.pow(start.x-(int)end.x,2.0)+Math.pow(start.y-(int)end.y,2.0));
	}

	public void calculateRandomPointsBetween(Coord start, Coord end, ArrayList<Coord> detour){
		points.clear();
		detour.clear();
		numberOfCoords = (int) Math.ceil(Math.random() * 4)+1;
		double range = Math.abs(end.x - start.x)+1;
		for (int i=0; i<numberOfCoords; i++){
			points.add(Math.ceil(Math.random()*(int)range)+getMin(start.x, end.x));
		}

		java.util.Collections.sort(points);

		if (start.x > end.x){
			java.util.Collections.reverse(points);
		}

		detour.add(start);
		for (int i = 0; i<numberOfCoords; i++){
		    y = start.y+((end.y-start.y)/(end.x-start.x))*(points.get(i)-start.x);
		    detour.add(new Coord(points.get(i), y));
		}
		detour.add(end);
	}

	public double getMin(double value1, double value2){
		if (value1 < value2){
			return value1;
		}
		else{
			return value2;
		}
	}

	public double countY(Coord start, Coord end, double x){
		double b = (end.y*start.x-start.y*end.x)/(start.x-end.x);
		double a = (start.y - b)/start.x;
		double yc = a*x+b;
		return yc;
	}

	public void calculateSlopes(Coord start, Coord end){
		slope = (end.y - start.y)/(end.x - start.x);
		perpendicularSlope = 1/slope*(-1);
	}

	public void getShitDone(Coord start, Coord end, Coord actual, ArrayList<Coord> trajectory){
		double randomValue = Math.random()*10;
		double yb_ya = Math.pow(end.y-start.y,2);
		double xa_xb = Math.pow(start.x-end.x,2);
		double xd_xc = Math.pow(countedX-actual.x,2);
		countedX=actual.x + Math.sqrt((Math.pow(randomValue,2)*yb_ya*xa_xb)/(100000*(xa_xb+yb_ya))+(Math.pow(yb_ya,2)*xa_xb)/(xa_xb*(xa_xb+yb_ya)));
		countedY=actual.y + ((start.x-end.x)*(countedX-actual.x))/(end.y-start.y);
		trajectory.add(new Coord(countedX, countedY));
	}
}