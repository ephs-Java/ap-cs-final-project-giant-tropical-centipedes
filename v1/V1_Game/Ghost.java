package V1_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ghost {
	Vector pos = new Vector();

	int size;

	int speed = 1;

	String path = "rdddddddlluuuuuuur";
	String startPath = path;
	
	BufferedImage image;

	int[][] costMap;
	
	public Ghost(int x, int y, int scale, String name) {
		pos = new Vector(x, y);

		size = scale;
		
		try {
			image = ImageIO.read(Runner.class.getResource("/" + name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initCostMap(int w, int h, int scale) {
		costMap = new int[h/scale][w/scale]; //int[col][row]
	}
	
	public void updateCostMap(Vector target) {
		
	}
	
	public void setPath(String s) {
		path = s;
		startPath = path;
	}


	int counter = speed;
	
	public void move() {
		if(counter == 0) {
			if(path.length() != 0) {
				String first = path.charAt(0) + "";

				switch(first) {
				case "u":
					pos.y -= size;
					break;
				case "d":
					pos.y += size;
					break;
				case "l":
					pos.x -= size;
					break;
				case "r":
					pos.x += size;
					break;
				}

				path = path.substring(1);
			}
			else {
				path = startPath;
			}
			
			counter = speed;
		}
		else {
			counter--;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(image, pos.x(), pos.y(), null);
	}
}
