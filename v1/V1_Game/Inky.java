package V1_Game;

import java.util.ArrayList;


public class Inky extends FollowingGhost{

	public Inky(int x, int y, int scale, String name) {
		super(x, y, scale, name);
		// TODO Auto-generated constructor stub
	}
	
	public void move(Vector target, Vector blinky, ArrayList<Wall> walls, int dir) {
		
		Vector newVector = new Vector(target.x, target.y); 
		
		Vector t = new Vector();
		if (!scatter) {
			t = new Vector(target.x, target.y);
		} else {
			t = new Vector(corner.x, corner.y);
		}
		
		if (dir == 1) { //Up
			newVector = new Vector(target.x, target.y - size * 2);
		} else if (dir == 2) { //Down
			newVector = new Vector(target.x, target.y + size * 2);
		} else if (dir == 3) { //Left
			newVector = new Vector(target.x - size * 2, target.y);
		} else if (dir == 4) { //Right
			newVector = new Vector(target.x + size * 2, target.y);
		}
		
		Vector v = new Vector(target.x, target.y);
		
		v.sub(blinky);
		
		v.mult(2);
		
		v.x -= (v.x % size);
		v.y -= (v.x % size);
			
		super.move(v, walls);
		
	}
	
	

}
