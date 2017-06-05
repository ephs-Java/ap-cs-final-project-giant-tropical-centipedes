package V1_Game;

import java.util.ArrayList;

public class Clyde extends FollowingGhost{

	public Clyde(int x, int y, int scale, String name) {
		super(x, y, scale, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move(Vector target, ArrayList<Wall> walls) {
		Vector newVector = new Vector(target.x, target.y);
		
		if(target.x - pos.x >=8) {
			pos.x = target.x;
			pos.y = target.y;
		} else {
			//pos.x = 
		}
		
		
		//super.move(newPos, walls);
	}

}
