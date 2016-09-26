package geom;

import java.util.Iterator;
import java.util.List;

/**
 * Abstract class for the representation of 2D shapes.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 */
public abstract class Shape {
	
	protected Position p;
	
	/** The leftmost point. */
	protected float left;
	/** The topmost point. */
	protected float top;
	/** The rightmost point. */
	protected float right;
	/** The bottommost point. */
	protected float bottom;
	
	
	/** Shape constructor. */
	public Shape(){
		this.p = new Position();
	}
	
	public Shape(float x, float y){
		this.p = new Position(x,y);
	}
	
	public Shape(Position p){
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
