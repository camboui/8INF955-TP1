package geom;

public class Point extends Shape {
	Point(){
		super();
	}
	
	Point(float x, float y){
		super(x,y);
	}

	@Override
	boolean isCollapsedTo(Shape s) {
		// TODO Auto-generated method stub
		return false;
	}
}
