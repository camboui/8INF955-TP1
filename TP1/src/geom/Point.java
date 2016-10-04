package geom;

/**
 * A point shape.
 * 
 * @author BRANGER Mathias, CROUZET Matthieu, MACE Quentin.
 *
 */
public class Point extends Shape {

	/** Point constructor with a default position of (0,0) . */
	public Point() {
		super();
	}

	/**
	 * Point constructor.
	 * 
	 * @param x
	 *            The wanted position for the x axis.
	 * 
	 * @param y
	 *            The wanted position for the y axis.
	 */
	public Point(float x, float y) {
		super(x, y);
	}

	/**
	 * Point constructor.
	 * 
	 * @param position
	 *            The wanted position of the Shape.
	 * 
	 * @see geom.Position
	 */
	public Point(Position position) {
		this.position = position;
	}

	public Point(Point point) {
		this.position = point.getPosition();
	}

	@Override
	public boolean isCollideTo(Shape shape) {
		boolean isCollide = false;
		if (shape instanceof Point) {
			Point p = (Point) shape;
			isCollide = this.equals(p);
		} else if (shape instanceof Circle) {
			Circle c = (Circle) shape;
			isCollide = this.isInside(c);
		} else if (shape instanceof AABB) {
			AABB aabb = (AABB) shape;
			isCollide = this.isInside(aabb);
		} else if (shape instanceof OBB) {
			OBB obb = (OBB) shape;
			isCollide = this.isInside(obb);
		} else if (shape instanceof KDOP) {
			// TODO point kdop
		}
		// TODO
		return isCollide;
	}

	@Override
	public void moveTo(Position position) {
		this.position = position;
	}
	
	/**
	 * 
	 * @param p
	 * @return true if p is at the same position
	 */
	public boolean equals(Point p){
		return this.getPosition().equals(p.getPosition());
	}
	
	/**
	 * 
	 * @param point
	 * @return true if the point is in the circle
	 */
	public boolean isInside(Circle c){
		return ((this.getPosition().getX() - c.getPosition().getX() * this.getPosition().getX()
				- c.getPosition().getX() + this.getPosition().getY()
				- c.getPosition().getY() * this.getPosition().getY()
				- c.getPosition().getY()) < c.getRadius() * c.getRadius());
	}
	
	/**
	 * 
	 * @param point
	 * @return true if the point is in the circle
	 */
	public boolean isInside(OBB abcd){
		/*
		 * A------------B
		 * |			|
		 * |			|
		 * |			|
		 * D------------C
		 * 
		 * P is the current point
		 */
		Point A = new Point(abcd.getPosition());
		Point B = new Point( 
						(float)(abcd.getPosition().getX()+abcd.getHeight() * Math.cos(abcd.getAngle())),
						(float)(abcd.getPosition().getY()+abcd.getHeight() * Math.sin(abcd.getAngle()))
						);
		Point C = new Point(
						(float)(abcd.getPosition().getX()+abcd.getDiagLength() * Math.cos(abcd.getAngle()+Math.PI/2.0)),
						(float)(abcd.getPosition().getY()+abcd.getDiagLength() * Math.sin(abcd.getAngle()+Math.PI/2.0))
						);
		Point D = new Point( 
						(float)(abcd.getPosition().getX()+abcd.getWidth() * Math.cos(abcd.getAngle())),
						(float)(abcd.getPosition().getY()+abcd.getWidth() * Math.sin(abcd.getAngle()))
						);
		/* Triangles areas */
		float areaAPD = (float) (this.distance(A)*this.distance(D)/2.0);
		float areaDPC = (float) (this.distance(D)*this.distance(C)/2.0);
		float areaCPB = (float) (this.distance(C)*this.distance(B)/2.0);
		float areaBPA = (float) (this.distance(B)*this.distance(A)/2.0);
		float sum = areaAPD + areaDPC + areaCPB + areaBPA;
		/* Rectangle area */
		float areaABCD = abcd.getArea(); 
		
		/* if the sum of triangles areas is equal to the rectangle area then the point is inside the rectangle*/
		return sum == areaABCD;
	}
	
	public float distance(Point p){
		return (float) Math.sqrt(
				(this.position.getX()-p.getPosition().getX())*(this.position.getX()-p.getPosition().getX())
				+(this.position.getY()-p.getPosition().getY())*(this.position.getY()-p.getPosition().getY())
				);
	}
}
