package geom;

public class OBB extends Shape {
	float width, height, angle;
	
	OBB(){
		super();
		this.width = 10;
		this.height = 10;
		this.angle = 0;
	}
	
	OBB(Position p){
		super(p);
	}
	
	OBB(float width, float height, float angle) throws IllegalArgumentException{
		super();
		if(width <= 0) throw new IllegalArgumentException("width is negative or null");
		else if(height <= 0) throw new IllegalArgumentException("height is negative or null");
		else {
			this.width = width;
			this.height = height;
			this.angle = angle;
		}		
	}
	
	OBB(Position p, float width, float height, float angle) throws IllegalArgumentException{
		super(p);
		if(width <= 0) throw new IllegalArgumentException("width is negative or null");
		else if(height <= 0) throw new IllegalArgumentException("height is negative or null");
		else {
			this.width = width;
			this.height = height;
			this.angle = angle;
		}	
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
