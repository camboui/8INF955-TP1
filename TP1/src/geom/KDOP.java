package geom;

import java.util.Iterator;
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
		float moveX, moveY;
		moveX = p.getX() - this.p.getX();
		moveY = p.getY() - this.p.getY();
		this.p = p;
		for (Iterator<Position> iterator = this.points.iterator(); iterator.hasNext();) {
			Position position = (Position) iterator.next();
			position.setX(position.getX()+moveX);
			position.setY(position.getY()+moveY);
		}
	}
}
