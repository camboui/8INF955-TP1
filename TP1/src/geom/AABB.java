package geom;

public class AABB extends OBB {
	AABB(){
		super();
	}
	
	AABB(Position p){
		super(p);
	}
	
	AABB(float width, float height) throws Exception{
		super(width, height, 0);
	}
	
	AABB(Position p, float width, float height) throws Exception{
		super(p,width, height, 0);
	}
}
