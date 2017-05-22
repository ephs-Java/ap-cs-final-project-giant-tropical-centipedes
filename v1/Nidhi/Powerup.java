package Nidhi;

import java.awt.Color;
import java.awt.Graphics;

import V1_Game.Vector;

public class Powerup {
	public Vector pos = new Vector();
	int size;
	int scale;
	
	public Powerup(int x, int y, int scale) {
		pos = new Vector(x,y);
		size = scale/2;
		this.scale = scale;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		int px = pos.x() + (scale - size) / 2;
		int py = pos.y() + (scale - size) / 2;
		g.fillOval(px, py, size, size);
		g.setColor(Color.black);
		g.drawOval(px, py, size, size);
	}
}
