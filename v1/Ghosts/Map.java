package Ghosts;

import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	
				
	public Vector start;
	
	int scale;
	
	int width, height;
	
	public ArrayList<Wall> walls = new ArrayList<Wall>();
	
	public ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	
	public Map(String map, int scale) {
		int mx = 0, my = 0;
		
		start = new Vector();
		this.scale = scale;
		int x = 0; int y = 0;
		for(int i = 0; i < map.length(); i++) {
			if(map.charAt(i) == '*') {
				walls.add(new Wall(x * scale, y * scale, scale));
			}
			
			if(map.charAt(i) == '0') {
				start = new Vector(x * scale, y * scale);
			}
			
			if(map.charAt(i) == 'x') {
				ghosts.add(new Ghost(x * scale, y * scale, scale));
			}
			
			if(map.charAt(i) == '-') {
				x = 0;
				y++;
			}
			else {
				x++;
			}
			
			
			mx = x > mx ? x : mx;
			my = y > my ? y : my;
		}
		
		width = mx * scale;
		height = my * scale + 23;
		
	}
}
