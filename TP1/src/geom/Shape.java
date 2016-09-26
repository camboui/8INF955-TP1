package geom;

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
	
	
}
