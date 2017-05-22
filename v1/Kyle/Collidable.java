package Kyle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Collidable {
	int x, y;
	
	ArrayList<Collidable> otherColliders = new ArrayList<Collidable>();
	
	String type;
	
	int size;
	
	boolean visable = true;
	
	public Collidable(int x, int y, int scale, String type) {
		this.x = x;
		this.y = y;
		
		size = scale;
		
		this.type = type;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x, y, size, size);
	}
	
	public void display(Graphics g) {
		if(visable) {
			draw(g);
		}
	}
}
