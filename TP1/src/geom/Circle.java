package geom;

public class Circle extends Shape {
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
}
