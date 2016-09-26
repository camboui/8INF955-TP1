package geom;

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
	/** The lowermost point. */
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
	
	
}
