package V1_Following;

import java.awt.Color;
import java.awt.Graphics;

import V1_Walls.Collider;
import V1_Walls.Player;

public class Wall implements Collider{
	int x, y; //Corner Point
	int size;
	
	public Wall(int x, int y, int size) {
		this.x = x;
		this.y = y;
		
		this.size = size;
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
