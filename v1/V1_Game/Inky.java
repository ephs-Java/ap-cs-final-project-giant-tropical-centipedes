package V1_Game;

import java.util.ArrayList;


public class Inky extends FollowingGhost{

	public Inky(int x, int y, int scale, String name) {
		super(x, y, scale, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move(Vector target, ArrayList<Wall> walls) {
		
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
		
		//pos.x = Math.sqrt(((followers.pos.x) - (target.x))^2 + ((followers.pos.y) - (target.y))^2);
		
		//super.move(newPos, walls);
	}
	
	

}
