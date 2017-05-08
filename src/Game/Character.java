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


public class Character {

    public int x; // x position 
    public int y; // y position
    private int size;
    
    boolean up = false, down = false, left = false, right = false;
	
	public Character(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
    
    public void move() {
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
    }
    
    public void draw(Graphics g) {
    	g.setColor(Color.yellow);
    	g.fillOval(x, y, size, size);
    	g.setColor(Color.black);
    	g.drawOval(x, y, size, size);
   }
    
 

	public void press(KeyEvent e) {
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

	public void release(KeyEvent e) {
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

}
