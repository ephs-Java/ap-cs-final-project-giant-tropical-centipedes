package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class Runner extends JPanel implements ActionListener, KeyListener{

	int w = 500, h = 500;
	
	public Runner(String s) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Runner r = new Runner();
		frame.addKeyListener(r);
		frame.add(r);
		frame.setSize(w, h);
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
	
	//******************************\\
	// \/\/\/ Game Code Here \/\/\/ \\
	//******************************\\
	
	int scale = 10;
	Character c = new Character(20, 20, scale);
	
	ArrayList<Wall> walls = new ArrayList<Wall>();
	
	ArrayList<Ghost> gs = new ArrayList<Ghost>();
	
	int frameRate = 10;
	int counter = frameRate;
	public void iterate() {		
		if(counter == 0) {
			c.move(walls);
			for(Ghost g : gs) {
				g.move(walls, gs, c);
			}
			counter = frameRate;
		}
		else {
			counter--;
		}
	}
	
	boolean start = true;
	
	@Override
	public void paintComponent(Graphics g) {
		if(start) {
			walls.add(new Wall(100, 100, scale));
			walls.add(new Wall(120, 100, scale));
			walls.add(new Wall(100, 110, scale));
			walls.add(new Wall(120, 110, scale));
			walls.add(new Wall(100, 120, scale));
			walls.add(new Wall(110, 120, scale));
			walls.add(new Wall(120, 120, scale));
			
			gs.add(new Ghost(300, 300, scale));
			gs.add(new Ghost(150, 300, scale));
			gs.add(new Ghost(300, 150, scale));
			start = false;
		}

		c.draw(g);
		
		for(Wall w : walls) {
			w.draw(g);
		}
		
		for(Ghost ghost : gs) {
			ghost.draw(g);
		}
		
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
