package V1_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

public class FollowingGhost extends Ghost{
	
	boolean run = false;
	
	Vector start;
	
	public FollowingGhost(int x, int y, int scale, String name) {
		super(x, y, scale, name);
		speed = 5;
		
		start = new Vector(x, y);
	}
	
	int duration = 100/5;
	int runCounter = 0;
	public void move(CostMap cm) {
		
		if(counter == 0) {
			switch(cm.bestDir(pos, run)) {
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
			
			if(runCounter == 0) {
				run = false;
			}
			else {
				runCounter--;
			}
			
			counter = speed;
		}
		else {
			counter--;
		}
	}
}
