package geom;

public class Point extends Shape {
	Point(){
		super();
	}
	
	Point(float x, float y){
		super(x,y);
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
