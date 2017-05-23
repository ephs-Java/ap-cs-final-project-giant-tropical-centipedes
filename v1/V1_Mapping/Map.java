package V1_Mapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	
				
	public Vector start;
	
	int scale;
	
	int width, height;
	
	public ArrayList<Wall> walls = new ArrayList<Wall>();
	
	
	public Map(String map, int scale) {
		makeMap(map, scale);
		
	}
	
	public Map(File file, int scale) {
		Scanner s;
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			s = null;
			e.printStackTrace();
		}
		
		String map = "";
		
		while(s.hasNext()) {
			map += s.next();
			map += "-";
		}
		
		makeMap(map, scale);
	}
	
	public void makeMap(String map, int scale) {
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
		
		width = (mx - 1) * scale;
		height = my * scale + 23;
	}
}
