package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Food {
	Vector pos = new Vector();
	
	int size;
	int scale;
	
	public Food(int x, int y, int scale) {
		pos = new Vector(x, y);
		
		size = scale/4;
		this.scale = scale;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		int px = pos.x() + (scale - size) / 2;
		int py = pos.y() + (scale - size) / 2;
		g.fillOval(px, py, size, size);
		g.setColor(Color.black);
		g.drawOval(px, py, size, size);
	}
}
