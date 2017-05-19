package V1_Walls;

import java.awt.Graphics;

public interface Collider {
	public boolean collide(Player p);
	
	public void draw(Graphics g);
}
