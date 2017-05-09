package Ghost;

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
	
	Map map = new Map(w, h);
	
	ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	
	int frameRate = 10;
	int counter = frameRate;
	public void iterate() {		
		if(counter == 0) {
			c.move(map);
			for(Ghost gs : ghosts) {
				gs.move(map, c);
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
			ghosts.add(new Ghost(100, 100, scale));
			ghosts.add(new Ghost(150, 150, scale));
			ghosts.add(new Ghost(200, 150, scale));
			start = false;
		}

		c.draw(g);
		
		for(Wall w : map) {
			w.draw(g);
		}
		
		for(Ghost gs : ghosts) {
			gs.draw(g);
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
