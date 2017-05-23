package V1_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Nidhi.Powerup;


public class Map {

	public Vector start;

	public int scale;

	int width, height;

	public ArrayList<Wall> walls = new ArrayList<Wall>();

	public ArrayList<Ghost> ghosts = new ArrayList<Ghost>();

	public ArrayList<Food> food = new ArrayList<Food>();
	
	public ArrayList<Powerup> powerup = new ArrayList<Powerup>();

	public Map(String map, int scale) {
		makeMap(map, scale);

	}

	public void makeMap(String map, int scale) {
		int mx = 0, my = 0;

		start = new Vector();
		this.scale = scale;
		int x = 0;
		int y = 0;
		
		int counter = 0;
		
		for (int i = 0; i < map.length(); i++) {
			if (map.charAt(i) == '*') {
				walls.add(new Wall(x * scale, y * scale, scale));
			}

			if (map.charAt(i) == '0') {
				start = new Vector(x * scale, y * scale);
			}

			if (map.charAt(i) == 'x') {
				String name;
				
				counter++;
				switch (counter) {
				case 1:
					name = "RedGhost.png";
					break;
				case 2:
					name = "BlueGhost.png";
					break;
				case 3:
					name = "GreenGhost.png";
					break;
				case 4:
					name = "OrangeGhost.png";
					break;
				default:
					name = "RedGhost.png";
					break;
				}
				
				ghosts.add(new Ghost(x * scale, y * scale, scale, name));
				food.add(new Food(x * scale, y * scale, scale));
			}
			
			if(map.charAt(i) == '?') {
				powerup.add(new Powerup(x * scale, y * scale, scale));
			}

			if (map.charAt(i) == ' ') {
				food.add(new Food(x * scale, y * scale, scale));
			}

			if (map.charAt(i) == '-') {
				x = 0;
				y++;
			} else {
				x++;
			}

			mx = x > mx ? x : mx;
			my = y > my ? y : my;
		}

		width = mx * scale;
		height = my * scale + 23;

	}
	
	public Map(File file, int scale2) {
		Scanner s;
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			s = null;
			e.printStackTrace();
		}
		
		String map = "";
		
		while(s.hasNextLine()) {
			String next = s.nextLine();
			map += next;
			map += "-";
		}
		
		for(int i = 0; i < map.length(); i++) {
			if(map.charAt(i) == '-') {
				System.out.println();
			}
			else {
				System.out.print(map.charAt(i));
			}
		}
		
		makeMap(map, scale);
	}

	public void reset(String map, int scale) {
		ghosts.clear();
		food.clear();
		powerup.clear();
		
		
		this.scale = scale;
		int x = 0; int y = 0;
		
		int counter = 0;
		for(int i = 0; i < map.length(); i++) {
			if(map.charAt(i) == 'x') {
				String name;
				
				counter++;
				switch (counter) {
				case 1:
					name = "RedGhost.png";
					break;
				case 2:
					name = "BlueGhost.png";
					break;
				case 3:
					name = "GreenGhost.png";
					break;
				case 4:
					name = "OrangeGhost.png";
					break;
				default:
					name = "RedGhost.png";
					break;
				}
				
				ghosts.add(new Ghost(x * scale, y * scale, scale, name));
				food.add(new Food(x * scale, y * scale, scale));
			}
			
			if(map.charAt(i) == '?') {
				powerup.add(new Powerup(x * scale, y* scale, scale));
			}
			
			if(map.charAt(i) == ' ') {
				food.add(new Food(x * scale, y * scale, scale));
			}
			
			if(map.charAt(i) == '-') {
				x = 0;
				y++;
			}
			else {
				x++;
			}
		}
	}
}
