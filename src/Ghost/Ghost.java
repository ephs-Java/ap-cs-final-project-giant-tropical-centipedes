package Ghost;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ghost {
	int x, y;
	int size;
	
	int speed = 10;
	
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
	public void move(Map walls, Character c) {
		if(counter == 0) {
			boolean u = walls.up(x, y);
			boolean d = walls.down(x, y);
			boolean l = walls.left(x, y);
			boolean r = walls.right(x, y);
			
			int mod = 100;
		
			int um = playerDir(c)[1] == "u" ? mod : 0;
			int dm = playerDir(c)[1] == "d" ? mod : 0;
			int lm = playerDir(c)[1] == "l" ? mod : 0;
			int rm = playerDir(c)[1] == "r" ? mod : 0;
			
			int up = u ? (int)(Math.random() * 100) + um : 0;
			int down = d ? (int)(Math.random() * 100) + dm : 0;
			int left = l ? (int)(Math.random() * 100) + lm: 0;
			int right = r ? (int)(Math.random() * 100) + rm: 0;
		
		
			String largest = up != 0 ? "up" : "none";
			int large = up;
			if(down > up) {
				largest = "down";
				large = down;
			}
			if(left > large) {
				largest = "left";
				large = left;
			}
			if(right > large) {
				largest = "right";
				large = right;
			}
		
			switch (largest) {
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
		
		counter--;
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
