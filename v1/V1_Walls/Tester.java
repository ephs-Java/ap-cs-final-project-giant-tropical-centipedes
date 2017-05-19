package V1_Walls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

import V1_Game.Wall;

public class Tester extends JPanel implements ActionListener, KeyListener{

	int w, h;
	
	public Tester(String s) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Tester r = new Tester();
		frame.addKeyListener(r);
		frame.add(r);
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		w = frame.getWidth();
		h = frame.getHeight();
	}
	
	
	private static final long serialVersionUID = 1L;
	private static int delay = 5;
	private Timer timer;
	
	public Tester() {
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public static void main(String[] args) {
		
		Tester r = new Tester("");
	}
	
	//******************************\\
	// \/\/\/ Game Code Here \/\/\/ \\
	//******************************\\
	
	Player p = new Player(50, 50);
	
	ArrayList<Wall> walls = new ArrayList<Wall>();
	
	boolean start = true;
	
	@Override
	public void paintComponent(Graphics g) {
		if(start) {
			walls.add(new Wall(100, 100, 50));
			walls.add(new Wall(100, 150, 50));
			walls.add(new Wall(100, 200, 50));
			walls.add(new Wall(150, 200, 50));
			walls.add(new Wall(200, 200, 50));
			walls.add(new Wall(200, 150, 50));
			walls.add(new Wall(200, 100, 50));
			start = false;
		}
		p.draw(g);
		for(Wall w : walls) {
			w.draw(g);
		}
		
		g.setColor(Color.black);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		p.move(e, walls);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		repaint();
	}
	
}
