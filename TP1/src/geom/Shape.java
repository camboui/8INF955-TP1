package geom;

import java.util.Iterator;
import java.util.List;

public abstract class Shape {
	Position p;
	
	Shape(){
		this.p = new Position();
	}
	
	Shape(float x, float y){
		this.p = new Position(x,y);
	}
	
	Shape(Position p){
		this.p = p;
	}
	
	abstract boolean isCollapsedTo(Shape s);
	
	boolean isCollapsed(List<Shape> shapes){
		boolean isCollapsed = false;
		for (Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext();) {
			Shape shape = (Shape) iterator.next();
			isCollapsed |= this.isCollapsedTo(shape);
		}
		return isCollapsed;
	}
	
}
