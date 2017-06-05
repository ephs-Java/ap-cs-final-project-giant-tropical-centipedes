package V1_Game;

import java.util.ArrayList;

public class Pinky extends FollowingGhost {

	public Pinky(int x, int y, int scale, String name) {
		super(x, y, scale, name);
	}

	public void move(Vector target, ArrayList<Wall> walls, int dir) {

		Vector newVector = new Vector(target.x, target.y);

		Vector t = new Vector();
		if (!scatter) {
			t = new Vector(target.x, target.y);
		} else {
			t = new Vector(corner.x, corner.y);
		}

		if (dir == 1) { // Up
			newVector = new Vector(t.x, t.y - size * 4);
		} else if (dir == 2) { // Down
			newVector = new Vector(t.x, t.y + size * 4);
		} else if (dir == 3) { // Left
			newVector = new Vector(t.x - size * 4, t.y);
		} else if (dir == 4) { // Right
			newVector = new Vector(t.x + size * 4, t.y);
		}

		super.move(newVector, walls);
	}

}
