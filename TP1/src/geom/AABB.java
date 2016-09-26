package geom;

public class AABB extends OBB {
	AABB(){
		super();
	}
	
	AABB(Position p){
		super(p);
	}
	
	AABB(float width, float height) throws IllegalArgumentException{
		super(width, height, 0);
	}
	
	AABB(Position p, float width, float height) throws IllegalArgumentException{
		super(p,width, height, 0);
	}
}
