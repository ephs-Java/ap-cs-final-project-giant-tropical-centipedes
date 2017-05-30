package V1_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import Nidhi.Powerup;

public class Runner extends JPanel implements ActionListener, KeyListener {

	int w = 500, h = 500;

	Map map = new Map(new File("map.txt"), 25);

	boolean admin = false;

	boolean killable = true;

	String password = "goof";
	String entered = "";
	
	BufferedImage heart;

	public Runner(String s) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Runner r = new Runner();
		frame.addKeyListener(r);
		frame.add(r);
		frame.setSize(map.width, map.height);
		frame.setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	private static int delay = 5;
	private Timer timer;

	public Runner() {
		timer = new Timer(delay, this);
		timer.start();
	}

	public static void main(String[] args) {
		Runner r = new Runner("");
	}

	// ******************************\\
	// \/\/\/ Game Code Here \/\/\/ \\
	// ******************************\\

	Character c = new Character(map.start.x(), map.start.y(), map.scale);

	int frameRate = 10;
	int counter = frameRate;

	public void ghosts() {
		map.ghosts.get(0).setPath("rrrrrrrrrdddddrrrrruuuuurrrrrrrrdddddlllllllluuuuullllldddddllllllllluuuuu");
		map.ghosts.get(1).setPath("llllllllldddddllllluuuuulllllllldddddrrrrrrrruuuuurrrrrdddddrrrrrrrrruuuuu");
		map.ghosts.get(2).setPath("rrrrrrrrddrrrrddllllddrrrrrruuuuuulllllldddlllrrrdddlllllllluuurrrllluuu");
		map.ghosts.get(3).setPath("llllllllddllllddrrrrddlllllluuuuuurrrrrrdddrrrllldddrrrrrrrruuulllrrruuu");

	}

	public void ghost2() {
		map.ghosts.get(0).setPath("dddddrrruurrrruuulllllll");
		map.ghosts.get(1).setPath("dddlllluuurrrrrrrdddddllluurrruuulll");
		map.ghosts.get(2).setPath("uullluuuullddllddrrddrrdddrrruuu");
		map.ghosts.get(3).setPath("ruuuuulllddrrrrrrrdddrruuulldddlllll");
	}

	int startFood;
	
	CostMap cm = new CostMap(map);
	
	int secondaryCounter = 30;
	int subCounter = secondaryCounter;
	
	public void iterate() {
		if (counter == 0) {
			if (!c.dead) {
				c.move(map.walls);
			}

			if (!c.dead) {
				for (Ghost g : map.ghosts) {
					//cm.makeMap(new Vector(c.x, c.y));
					g.move();
				}
				
				for(FollowingGhost fg : map.followers) {
					fg.move(cm);
				}
			}
			
			if(c.food == startFood) {
				c.dead = true;
			}

			if (killable) {
				c.death(map.ghosts, map.followers);
			}

			c.eat(map.food);
			c.eatCherry(map.powerup, map.ghosts, map.followers);

			c.boundary(map.width, map.height);

			counter = frameRate;
			
			
		} else {
			counter--;
		}
		
		if(subCounter == 0) {
			cm.makeMap(new Vector(c.x, c.y));
			
			subCounter = secondaryCounter;
		}
		else {
			subCounter--;
		}
	}

	boolean start = true;

	@Override
	public void paintComponent(Graphics g) {
		if (start) {
			ghost2();
			
			cm.makeMap(new Vector(c.x, c.y));
			
			
			startFood = map.food.size();
			start = false;
			
			try {
				heart = ImageIO.read(new File("Heart.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		c.draw(g);

		for (Food f : map.food) {
			f.draw(g);
		}

		for (Wall w : map.walls) {
			w.draw(g);
		}
		
		for (Powerup p : map.powerup) {
			p.draw(g);
		}

		for (Ghost ghost : map.ghosts) {
			ghost.draw(g);
		}
		
		for (FollowingGhost fg : map.followers) {
			fg.draw(g);
		}

		g.drawString("Score: " + c.food, 25, 30);
		
		
		for(int i = 1; i <= c.lives; i++) {
			g.drawImage(heart, map.width - 30 * i, 15, null);
		}
		
		if (!c.dead) {
			if (!admin) {
				
			} else {
				g.setColor(Color.red);
				g.drawString("Admin Active", map.width / 2 - 40, 30);
			}
		}
		else {
			g.setColor(Color.black);
			g.drawString("Press \"R\" to restart", map.width/2- 60, 30);
		}
		
		g.setColor(Color.blue);
		//cm.draw(g);

		iterate();
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		c.press(e);

		boolean wasd = (e.getKeyChar() == 'w' || e.getKeyChar() == 'a' || e.getKeyChar() == 's'
				|| e.getKeyChar() == 'd');

		if (!admin && e.getKeyCode() != KeyEvent.VK_ENTER && !wasd) {
			entered += e.getKeyChar() + "";
		}

		switch (e.getKeyCode()) {
		case KeyEvent.VK_R:
			if (c.dead) {
				c.reset();
				map.reset(map.scale);
				ghost2();
			}
			break;
		case KeyEvent.VK_DELETE:
			admin = false;
			break;
		case KeyEvent.VK_ENTER:
			if (entered.equalsIgnoreCase(password)) {
				admin = true;
			}

			break;
		case KeyEvent.VK_C:
			entered = "";
			break;
		}

		if (admin) {
			switch (e.getKeyChar()) {
			case 'l':
				c.lives -= 1;

				if (c.lives == 0) {
					c.dead = true;
				}
				break;
			case 'i':
				killable = !killable;
				break;
			case '=':
				for(Ghost g : map.ghosts) {
					g.speed--;
					if(g.speed < 1) g.speed = 1;
				}
				break;
			case '-':
				for(Ghost g : map.ghosts) {
					g.speed++;
				}
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		c.release(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		repaint();
	}

}
