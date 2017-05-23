package Nidhi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import V1_Game.Vector;

public class Powerup {
	BufferedImage cherry;
	public Vector pos = new Vector();
	int size;
	int scale;
	
	public Powerup(int x, int y, int scale) {
		pos = new Vector(x,y);
		size = scale/2;
		this.scale = scale;
		try {
			cherry = ImageIO.read(new File("cherries.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(cherry, pos.x(),pos.y(), null);	
	}
}
