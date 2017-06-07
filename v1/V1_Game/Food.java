package V1_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Food {
	Vector pos = new Vector();
	
	int size;
	int scale;
	
	BufferedImage image;
	
	public Food(int x, int y, int scale) {
		pos = new Vector(x, y);
		
		try {
			image = ImageIO.read(Runner.class.getResource("/Food.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		size = scale/4;
		this.scale = scale;
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, pos.x(), pos.y(), null);
	}
}
