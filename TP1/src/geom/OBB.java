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
	
	OBB(float width, float height, float angle) throws Exception{
		super();
		if(width == 0 || height == 0) throw new Exception("width or height is null");
		else {
			this.width = width;
			this.height = height;
			this.angle = angle;
		}		
	}
	
	OBB(Position p, float width, float height, float angle) throws Exception{
		super(p);
		if(width == 0 || height == 0) throw new Exception("width or height is null");
		else {
			this.width = width;
			this.height = height;
			this.angle = angle;
		}	
	}	
}
