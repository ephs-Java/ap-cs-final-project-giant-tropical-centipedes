package Kyle;

import java.awt.Color;
import java.awt.Graphics;

public class Wall {
	
	public Vector pos;
	
	int size;

	public Wall(int x, int y, int scale) {
		pos = new Vector(x, y);
		
		size = scale;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);;
		g.drawRect(pos.x(), pos.y(), size, size);
		g.setColor(Color.BLUE);
		g.fillRect(pos.x(), pos.y(), size, size);
	}
}
