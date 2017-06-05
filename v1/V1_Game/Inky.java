package V1_Game;

import java.util.ArrayList;


public class Inky extends FollowingGhost{

	public Inky(int x, int y, int scale, String name) {
		super(x, y, scale, name);
		// TODO Auto-generated constructor stub
	}
	
	/*@Override
	public void move(Vector target, ArrayList<Wall> walls, Character c) {
		
		Vector newVector = new Vector(target.x, target.y);
		double x;
		double y;
		
		if(up) {
			x = (double) target.x;
			y = (double) target.y - 2;
		} else if (down) {
			x = (double) target.x();
			y = (double) target.y + 2;
		} else if (right) {
			x = (double) target.x + 2;
			y = (double) target.y();
		} else if (left) {
			x = (double) target.x - 2;
			y = (double) target.y();
		}
		
		pos.x = Math.sqrt((Math.pow(pos.x - c.x, 2)) + (Math.pow(pos.y - c.y, 2)));
		
		//super.move(newPos, walls);
	}*/
	
	@Override
	public void move(Vector target, ArrayList<Wall> walls) {
		
		Vector newVector = new Vector(target.x, target.y); 
		
		Vector t = new Vector();
		if (!scatter) {
			t = new Vector(target.x, target.y);
		} else {
			t = new Vector(corner.x, corner.y);
		}
		
		if (up) {
			pos.x = target.x;
			pos.y = target.y - 2;
		} else if (down) {
			pos.x = target.x;
			pos.y = target.y + 2;
		} else if (right) {
			pos.x = target.x + 2;
			pos.y = target.y;
		} else if (left) {
			pos.x = target.y - 2;
			pos.y = target.y;
		}
			
		
		
		Vector newPos = new Vector(pos.x, pos.y);
		super.move(newPos, walls);
		
	}
	
	

}
