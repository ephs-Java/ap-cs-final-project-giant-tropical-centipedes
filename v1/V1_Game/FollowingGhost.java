package V1_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

public class FollowingGhost extends Ghost{
	
	public FollowingGhost(int x, int y, int scale, String name) {
		super(x, y, scale, name);
		speed = 5;
	}
	
	public void move(CostMap cm) {
		if(counter == 0) {
			switch(cm.bestDir(pos)) {
			case "up":
				pos.y -= size;
				break;
			case "down":
				pos.y += size;
				break;
			case "left":
				pos.x -= size;
				break;
			case "right":
				pos.x += size;
				break;
			}
			
			counter = speed;
		}
		else {
			counter--;
		}
	}
}
