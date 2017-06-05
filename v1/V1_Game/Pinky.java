package V1_Game;

import java.util.ArrayList;

public class Pinky extends FollowingGhost {

	int width = 0;
	int height = 0;

	boolean run = false;
	boolean up, down, left, right;

	Vector start;
	Vector corner;

	int stopDuration = 100 / 5;
	int runCounter = 0;

	int subSpeed = 20;
	int subCounter = subSpeed;

	boolean scatter = false;

	public Pinky(int x, int y, int scale, String name) {
		super(x, y, scale, name);
	}
	

	//@Override
	/*public void move(Vector target, ArrayList<Wall> walls, Character c) {
		Vector newVector = new Vector(target.x,target.y);
		
		if(up) {
			target.x = c.x;
			target.y = c.y - 4;
		} else if (down) {
			target.x = c.x;
			target.y = c.y + 4;
		} else if (right) {
			target.x = c.x + 4;
			pos.y = c.y;
		} else if (left) {
			target.x = c.x - 4;
			target.y = c.y;
		}
		
		//Vector newPos = new Vector(pos.x, pos.y);
		
		super.move(newVector, walls, c);
		
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
		
		if(up) {
			pos.x = target.x;
			pos.y = target.x - 4;
		} else if (down) {
			pos.x = target.x;
			pos.y = target.y + 4;
		} else if (right) {
			pos.x = target.x + 4;
			pos.y = target.y;
		} else if (left) {
			pos.x = target.x - 4;
			pos.y = target.y;
		}
		
		Vector newPos = new Vector(pos.x, pos.y);
		
		super.move(newPos, walls);
	}

	}

	
