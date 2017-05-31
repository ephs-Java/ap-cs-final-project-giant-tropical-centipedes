package V1_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Nidhi.Powerup;

public class Character {

	public int x; // x position
	public int y; // y position

	public Vector start = new Vector();

	private int size;

	int speed = 2;

	boolean up = false, down = false, left = false, right = false;

	int lives = 3;

	boolean dead = false;
	
	int food = 0;
	
	BufferedImage example;
	
	BufferedImage u, d, l, r;

	public Character(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;

		start = new Vector(x, y);
		
		try {
			example = ImageIO.read(new File("PacMan.png"));
			u = ImageIO.read(new File("PacManUp.png"));
			d = ImageIO.read(new File("PacManDown.png"));
			l = ImageIO.read(new File("PacManLeft.png"));
			r = ImageIO.read(new File("PacManRight.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	int counter = speed;
	private int powerup;

	public void move(ArrayList<Wall> walls) {
		if (counter == 0) {
			int px = x, py = y;

			if (up) {
				y -= size;
			}

			else if (down) {
				y += size;
			}

			else if (left) {
				x -= size;
			}

			else if (right) {
				x += size;
			}

			for (Wall w : walls) {
				if (w.x == x && w.y == y) {
					x = px;
					y = py;
				}
			}

			counter = speed;
		} else {
			counter--;
		}
	}

	public void draw(Graphics g) {
		
		if(up) {
			example = u;
		}
		
		if(down) {
			example = d;
		}
		
		if(left) {
			example = l;
		}
		
		if(right) {
			example = r;
		}
		
		g.drawImage(example, x, y, null);
		//g.setColor(Color.yellow);
		//g.fillOval(x, y, size, size);
		//g.setColor(Color.black);
		//g.drawOval(x, y, size, size);
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
		switch (e.getKeyCode()) {
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
	
	public void eat(ArrayList<Food> food) {
		for(int i = food.size() - 1; i >= 0; i--) {
			if(food.get(i).pos.x == x && food.get(i).pos.y == y) {
				this.food++;
				food.remove(i);
			}
		}	
	}
	
	public void eatCherry(ArrayList<Powerup> powerup, ArrayList<Ghost> ghosts, ArrayList<FollowingGhost> fgs) {
		for(int i = powerup.size() - 1; i >= 0; i--) {
			if((powerup.get(i).pos.x == x) && (powerup.get(i).pos.y == y)) {
				this.powerup++;
				powerup.remove(i);
				
				for(Ghost ghost: ghosts) {
					ghost.counter = 100;
				}
				
				for(FollowingGhost ghost : fgs) {
					ghost.counter = 100;
				}
			}
		}
	}

	
	public void reset() {
		lives = 3;
		food = 0;
		
		dead = false;
	}

	public void death(ArrayList<Ghost> g, ArrayList<FollowingGhost> fg) {
		for (Ghost gs : g) {
			if (gs.pos.x == x && gs.pos.y == y) {
				lives -= 1;
				x = (int) start.x;
				y = (int) start.y;

				if (lives == 0) {
					dead = true;
				}
				
				for(FollowingGhost fgs : fg) {
					fgs.pos = new Vector(fgs.start.x, fgs.start.y);
				}
			}
		}
		
		for (FollowingGhost gs : fg) {
			if (gs.pos.x == x && gs.pos.y == y) {
				lives -= 1;
				x = (int) start.x;
				y = (int) start.y;
				
				gs.pos = new Vector(gs.start.x, gs.start.y);

				if (lives == 0) {
					dead = true;
				}
			}
		}
	}
	
	public void boundary(int w, int h) {
		if(x < 0) {
			x = w - size;
		} else if(x >= w) {
			x = 0;
		}
		
		if(y < 0) {
			y = h;
		} else if(y > h) {
			y = 0;
		}
	}
}
