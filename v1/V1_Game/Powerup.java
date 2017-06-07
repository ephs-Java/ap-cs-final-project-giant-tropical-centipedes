package V1_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Powerup {
	BufferedImage cherry;
	public Vector pos = new Vector();
	int size;
	int scale;
	
	//constructor for cherry powerup
	public Powerup(int x, int y, int scale) {
		pos = new Vector(x,y);
		size = scale/2;
		this.scale = scale;
		try {
			cherry = ImageIO.read(Runner.class.getResource("/cherries.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//draw powerups method
	public void draw(Graphics g) {
		g.drawImage(cherry, pos.x(),pos.y(), null);	
	}
}
