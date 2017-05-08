package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Character extends JPanel implements ActionListener, KeyListener{
	int w, h;
	
	public Character(String s) {
		JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int i = 0;
        Character pm = new Character();
        frame.addKeyListener(pm);
        frame.add(pm);
        frame.setSize(500, 500);
        frame.setVisible(true);
        
        w = frame.getWidth();
        h = frame.getHeight();
	}
	
	private static final long serialVersionUID = 1L;
	private static int delay = 5;
	protected Timer timer;
	
	public Character() {
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public static void main(String[] args) {
		Character c = new Character("");
	}
	
	//*****************************\\
	// \/\/\/ Game Code Here \/\/\/
	//*****************************\\
	
    private int x = 100; // x position 
    private int y = 100; // y position
    private int size = 15;
    //private int xSpeed;
    //private int ySpeed;
    
    boolean up = false, down = false, left = false, right = false;
	
    int frameRate = 10;
    int counter = frameRate;
    public void iterate() {
    	if(counter == 0) {
    		if(up) {
    			y-=size;
    		}
    	
    		else if(down) {
    			y+=size;
    		}
    	
    		else if(left) {
    			x-=size;
    		}
    	
    		else if(right) {
    			x+=size;
    		}
    		counter = frameRate;
    	}
    	else {
    		counter --;
    	}
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	g.setColor(Color.yellow);
    	g.fillOval(x, y, size, size);
    	g.setColor(Color.black);
    	g.drawOval(x, y, size, size);
    	
    	iterate();
    }
    
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_UP:
				up = true;
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			case KeyEvent.VK_UP:
				up = false;
				break;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		repaint();
	}

}
