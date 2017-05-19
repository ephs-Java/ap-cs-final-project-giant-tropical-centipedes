package Following;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {
	Vector pos, vel;

	private int size;

	int speed = 0;

	boolean up = false, down = false, left = false, right = false;

	public Player(int x, int y, int scale) {
		pos = new Vector(x, y);
		vel = new Vector(0, 0);

		size = scale;
	}

	int counter = speed;
	public void move() {
		if(counter == 0) {
			if(up) {
				vel.y = -10;
			}
			else if(down) {
				vel.y = 10;
			}
			else {
				vel.y = 0;
			}

			if(left) {
				vel.x = -10;
			}
			else if(right) {
				vel.x = 10;
			}
			else {
				vel.x = 0;
			}
			
			counter = speed;
		}
		else {
			counter--;
		}
	}

	public void update(ArrayList<Wall> walls) {
		boolean collide = false;
		for(Wall w : walls) {
			if(pos.x + vel.x == w.x && pos.y + vel.y == w.y) {
				collide = true;
			}
		}
		if(!collide) {
			pos.add(vel);
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int)pos.x, (int)pos.y, size, size);
		g.setColor(Color.black);
		g.drawOval((int)pos.x, (int)pos.y, size, size);
	}

	public void press(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		}

	}

	public void release(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		}

	}
}
