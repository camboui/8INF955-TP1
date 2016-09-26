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
		if(points.isEmpty()) throw new Exception("Empty list of points");
		else if (points.get(0) != p) throw new Exception("First element of the list is different than the position p");
		else {
			this.points = points;
		}
	}

	@Override
	boolean isCollideTo(Shape s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void moveTo(Position p) {
		
	}
}
