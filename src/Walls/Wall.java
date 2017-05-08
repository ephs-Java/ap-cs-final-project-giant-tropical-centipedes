package Walls;

import java.awt.Color;
import java.awt.Graphics;

public class Wall implements Collider{
	int x, y; //Corner Point
	int size = 50;
	
	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean collide(Player p) {
		boolean lr = p.x >= x && p.x < x + size;
		boolean tb = p.y >= y && p.y < y + size;
		
		return lr && tb;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x, y, size, size);
		g.setColor(Color.blue);
		g.fillRect(x, y, size, size);
	}
}
