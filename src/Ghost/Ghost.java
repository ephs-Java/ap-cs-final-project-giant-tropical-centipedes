package Ghost;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ghost {
	int x, y;
	int size;

	int speed = 5;

	public Ghost(int x, int y, int scale) {
		this.x = x;
		this.y = y;
		size = scale;
	}

	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, size, size);
		g.setColor(Color.black);
		g.drawRect(x, y, size, size);
	}

	int counter = speed;
	public void move(ArrayList<Wall> walls, Character c) {
		if(counter == 0) {
			Vector up = new Vector(x, y - size);
			Vector down = new Vector(x, y + size);
			Vector left = new Vector(x - size, y);
			Vector right = new Vector(x + size, y);

			Vector v = new Vector(c.x, c.y);

			Vector upDiff = Vector.sub(up, v);
			Vector downDiff = Vector.sub(down, v);
			Vector leftDiff = Vector.sub(left, v);
			Vector rightDiff = Vector.sub(right, v);

			
			if(wallCollide(down, walls)) {
				downDiff.setMag(10000);
			}

			if(wallCollide(left, walls)) {
				leftDiff.setMag(10000);
			}

			if(wallCollide(right, walls)) {
				rightDiff.setMag(10000);
			}

			String dir = "none";
			Vector smallest = new Vector(1000, 1000);
			if(!wallCollide(up, walls)) {
				dir = "up";
				smallest = new Vector(upDiff.x, upDiff.y);
			}

			if(downDiff.mag() < smallest.mag()) {
				dir = "down";
				smallest = new Vector(downDiff.x, downDiff.y);
			}

			if(leftDiff.mag() < smallest.mag()) {
				dir = "left";
				smallest = new Vector(leftDiff.x, leftDiff.y);
			}

			if(rightDiff.mag() < smallest.mag()) {
				dir = "right";
			}

			switch (dir) {
				case "up":
					y -= size;
					break;
				case "down":
					y += size;
					break;
				case "left":
					x -= size;
					break;
				case "right":
					x += size;
					break;

			}
			counter = speed;
		}
		else {
			counter --;
		}

	}

	private boolean wallCollide(Vector pos, ArrayList<Wall> walls) {
		for(Wall w : walls) {
			if(w.x == pos.x && w.y == pos.y) return true;
		}

		return false;
	}

	public String[] playerDir(Character c) {
		int diffX = x - c.x;
		int diffY = y - c.y;

		String[] arr = new String[2];
		if(diffX > 0) {
			arr[0] = "r";
		}
		else if(diffX < 0) {
			arr[0] = "l";
		}
		else {
			arr[0] = "center";
		}

		if(diffY > 0) {
			arr[1] = "u";
		}
		else if(diffY < 0) {
			arr[1] = "d";
		}
		else {
			arr[1] = "center";
		}

		return arr;
	}
}
