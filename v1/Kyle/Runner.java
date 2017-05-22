package Kyle;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class Runner extends JPanel implements ActionListener, KeyListener{

	int w = 500, h = 500;
	
	String m= "*****-"
			+ "     -"
			+ "     -"
			+ "     -"
			+ "     ";
	
	Map map = new Map(m, 30);
	
	public Runner(String s) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Runner r = new Runner();
		frame.addKeyListener(r);
		frame.add(r);
		frame.setSize(map.w, map.h);
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
		
	int frameRate = 10;
	int counter = frameRate;
	public void iterate() {		
		if(counter == 0) {
			
			
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
			
			
			start = false;
		}
		
		map.draw(g);
		
		iterate();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
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
