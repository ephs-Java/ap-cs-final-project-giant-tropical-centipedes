package V1_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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

	//constructor for character
	public Character(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;

		start = new Vector(x, y);
		
		//set images for pacman in up, down, left, and right directions
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
	
	public int getDir() {
		if(up) {
			return 1;
		}
		
		if(down) {
			return 2;
		}
		
		if(left) {
			return 3;
		}
		
		if(right) {
			return 4;
		}
		
		return -1;
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
	}

	//set keys pressed and released with directions
	public void press(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			right = true;
			left = false;
			down = false;
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			left = false;
			down = false;
			up = false;
			break;
		case KeyEvent.VK_A:
			right = false;
			left = true;
			down = false;
			up = false;
			break;
		case KeyEvent.VK_LEFT:
			right = false;
			left = true;
			down = false;
			up = false;
			break;
		case KeyEvent.VK_S:
			right = false;
			left = false;
			down = true;
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			right = false;
			left = false;
			down = true;
			up = false;
			break;
		case KeyEvent.VK_W:
			right = false;
			left = false;
			down = false;
			up = true;
			break;
		case KeyEvent.VK_UP:
			right = false;
			left = false;
			down = false;
			up = true;
			break;
		}

	}

	public void release(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;	
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		}

	}
	
	//character eats food
	public void eat(ArrayList<Food> food) {
		for(int i = food.size() - 1; i >= 0; i--) {
			if(food.get(i).pos.x == x && food.get(i).pos.y == y) {
				this.food++;
				food.remove(i);
			}
		}	
	}
	
	//character eats cherry and ghosts stop
	public void eatCherry(ArrayList<Powerup> powerup, ArrayList<Ghost> ghosts, ArrayList<FollowingGhost> fgs, ArrayList<Pinky> pinky, ArrayList<Inky> inky, ArrayList<Clyde> clyde) {
		for(int i = powerup.size() - 1; i >= 0; i--) {
			if((powerup.get(i).pos.x == x) && (powerup.get(i).pos.y == y)) {
				this.powerup++;
				powerup.remove(i);
				
				for(Ghost ghost: ghosts) {
					ghost.counter = 200/ghost.speed;
				}
				
				for(FollowingGhost ghost : fgs) {
					ghost.counter = 200/ghost.speed;
				}
				
				for(Pinky p : pinky) {
					p.counter = 200/p.speed;
				}
				
				for(Inky ink : inky) {
					ink.counter = 200/ink.speed;
				}
				
				for(Clyde c : clyde) {
					c.counter = 200/c.speed;
				}
			}
		}
	}

	
	public void reset() {
		lives = 3;
		food = 0;
		
		dead = false;
	}

	//when a ghost eats pacman, subtract one life and return to original starting position
	public void death(ArrayList<Ghost> g, ArrayList<FollowingGhost> fg, ArrayList<Pinky> pinky, ArrayList<Inky> inky, ArrayList<Clyde> clyde) {
		boolean restart = false;
		
		for (Ghost gs : g) {
			if (gs.pos.x == x && gs.pos.y == y) {
				restart = true;
			}
		}
		
		for (FollowingGhost gs : fg) {
			if (gs.pos.x == x && gs.pos.y == y) {
				restart = true;
			}
		}
		
		for(Pinky p : pinky) {
			if(p.pos.x == x && p.pos.y == y) {
				restart = true;
			}
		}
		
		for(Inky i : inky) {
			if(i.pos.x == x && i.pos.y == y) {
				restart = true;
			}
		}
		for(Clyde cl : clyde) {
			if(cl.pos.x == x && cl.pos.y == y) {
				restart = true;
			}
		}
		
		if(restart) {
			lives--;
			
			for(FollowingGhost fgs : fg) {
				fgs.restart();
			}
			
			for(Inky i : inky) {
				i.restart();
			}
			
			for(Pinky p : pinky) {
				p.restart();
			}
			
			for(Clyde c : clyde) {
				c.restart();
			}
			
			x = start.x();
			y = start.y();
			
			if(lives == 0) {
				dead = true;
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
			y = h - size;
		} else if(y > h) {
			y = 0;
		}
	}
}
