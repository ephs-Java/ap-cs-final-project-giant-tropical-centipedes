package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ghost {
	int x, y;
	int size;

	int speed = 2;

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
	public void move(ArrayList<Wall> walls, ArrayList<Ghost> g, Character c) {
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
			
			upDiff.sub(Math.random() * 20);
			downDiff.sub(Math.random() * 20);
			leftDiff.sub(Math.random() * 20);
			rightDiff.sub(Math.random() * 20);

			
			if(wallCollide(down, walls) || ghostCollide(down, g)) {
				downDiff.setMag(10000);
			}

			if(wallCollide(left, walls) || ghostCollide(left, g)) {
				leftDiff.setMag(10000);
			}

			if(wallCollide(right, walls) || ghostCollide(right, g)) {
				rightDiff.setMag(10000);
			}

			String dir = "none";
			Vector smallest = new Vector(1000, 1000);
			if(!wallCollide(up, walls) && !ghostCollide(down, g)) {
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
	
	private boolean ghostCollide(Vector pos, ArrayList<Ghost> g) {
		for(Ghost gs : g) {
			if(gs.x == pos.x && gs.y == pos.y) return true;
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
