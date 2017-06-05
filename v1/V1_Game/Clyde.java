package V1_Game;

import java.util.ArrayList;

public class Clyde extends FollowingGhost{

	public Clyde(int x, int y, int scale, String name) {
		super(x, y, scale, name);
	}
	
	@Override
	public void move(Vector target, ArrayList<Wall> walls) {
				
		Vector t = new Vector();
		if (!scatter) {
			t = new Vector(target.x, target.y);
		} else {
			t = new Vector(corner.x, corner.y);
		}
		
		Vector diff = new Vector(target.x, target.y);
		
		diff.sub(pos);
		
		double dist = diff.mag();
		
		if(dist < 8.0) {
			super.move(start, walls);
		} else {
			super.move(t, walls);
		}
	}

}
