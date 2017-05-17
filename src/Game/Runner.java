package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class Runner extends JPanel implements ActionListener, KeyListener {

	int w = 500, h = 500;

	String m ="++++++++++*****************-" 
			+ "++++++++++*0      x       *-" 
			+ "++++++++++*       *       *-"
			+ "++++++++++* ***   *   *** *-" 
			+ "++++++++++*   *   *   *   *-" 
			+ "++++++++++*   *   *   *   *-"
			+ "++++++++++* ***   *   *** *-" 
			+ "++++++++++*       *       *-" 
			+ "++++++++++*               *-"
			+ "++++++++++*****************-";

	Map map = new Map(m, 10);

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

	public void iterate() {
		if (counter == 0) {
			if (!c.dead) {
				c.move(map.walls);
			}

			if (!c.dead) {
				for (Ghost g : map.ghosts) {
					g.move();
				}
			}

			c.death(map.ghosts);

			c.eat(map.food);

			counter = frameRate;
		} else {
			counter--;
		}
	}

	boolean start = true;

	@Override
	public void paintComponent(Graphics g) {
		if (start) {

			start = false;
		}

		c.draw(g);

		for (Food f : map.food) {
			f.draw(g);
		}

		for (Wall w : map.walls) {
			w.draw(g);
		}

		for (Ghost ghost : map.ghosts) {
			ghost.draw(g);
		}

		g.drawString("Score: " + c.food, 0, 12);
		g.drawString("Lives: " + c.lives, 0, 27);

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
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_R:
			if(c.dead) {
				c.reset();
				map.reset(m, map.scale);
			}
			break;
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
