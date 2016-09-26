package geom;

import java.util.List;

public class KDOP extends Shape {
	List<Position> points;
	
	KDOP(List<Position> points) throws Exception{
		super();
		if(points.isEmpty()) throw new Exception("Empty list of points");
		else {			
			this.points = points;
		}
	}
	
	KDOP(List<Position> points, Position p) throws Exception{
		super(p);
		if(points.isEmpty()) throw new Exception("Enpty list of points");
		else {
			this.points = points;
		}
	}
}
