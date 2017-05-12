package Following;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Character {

    public int x; // x position 
    public int y; // y position
    
    
    
    private int size;
    
    int speed = 1;
    
    boolean up = false, down = false, left = false, right = false;
	
	public Character(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
    int counter = speed;
    public void move(ArrayList<Wall> walls) {
    	if(counter == 0) {
    		int px = x, py = y;
    	
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
    		
    		for(Wall w : walls) {
    			if(w.x == x && w.y == y) {
    				x = px;
    				y = py;
    			}
    		}
    		
    		counter = speed;
    	}
    	else {
    		counter --;
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
		switch(e.getKeyCode()) {
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

	
}
