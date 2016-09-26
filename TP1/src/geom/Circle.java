package geom;

/**
 * A circle shape
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class Circle extends Shape {
	/** The radius of the circle. */
	float radius;
	
	
	Circle(){
		super();
		this.radius = 10;
	}
	
	Circle(float radius) throws Exception{
		super();
		if(radius == 0) throw new Exception("radius is null");
		else {
			this.radius = radius;
		}
	}
	
	Circle(Position p, float radius){
		super(p);
		this.radius = radius;
	}

	@Override
	boolean isCollideTo(Shape s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void moveTo(Position p) {
		this.p = p;
	}
}
