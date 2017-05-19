package V1_Walls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import V1_Game.Wall;

public class Player {
	public int x, y;
	
	public int size = 50;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(KeyEvent e, ArrayList<Wall> walls) {
		int px = x;
		int py = y;
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				y -= size;
				break;
			case KeyEvent.VK_S:
				y += size;
				break;
			case KeyEvent.VK_A:
				x -= size;
				break;
			case KeyEvent.VK_D:
				x += size;
				break;
		}
		
		for(Wall w : walls) {
			if(w.collide(this)) {
				x = px;
				y = py;
			}
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, size, size);
		g.setColor(Color.black);
		g.drawOval(x, y, size, size);
	}
}
