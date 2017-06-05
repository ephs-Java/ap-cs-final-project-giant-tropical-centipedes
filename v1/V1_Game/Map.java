package V1_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Map {

	public Vector start;

	public int scale;

	int width, height;

	//create an arraylist of walls, ghosts, following ghosts, food, and powerups
	
	public ArrayList<Wall> walls = new ArrayList<Wall>();

	public ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	
	ArrayList<FollowingGhost> followers = new ArrayList<FollowingGhost>();

	public ArrayList<Food> food = new ArrayList<Food>();
	
	public ArrayList<Powerup> powerup = new ArrayList<Powerup>();
	
	public ArrayList<Pinky> pinky = new ArrayList<Pinky>();
	
	public ArrayList<Inky> inky = new ArrayList<Inky>();
	
	public ArrayList<Clyde> clyde = new ArrayList<Clyde>();
	
	
	//start with an empty map
	String map = "";

	// map constructor
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
				//add a wall wherever there is * 
				walls.add(new Wall(x * scale, y * scale, scale));
				this.map += '*';
			}
			
			//character starts where there is 0
			else if (map.charAt(i) == '0') {
				start = new Vector(x * scale, y * scale);
				this.map += '0';
			}
			
			//add ghost wherever there is x
			else if (map.charAt(i) == 'x') {
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
				
				//ghosts.add(new Ghost(x * scale, y * scale, scale, name));
				food.add(new Food(x * scale, y * scale, scale));
				
				this.map += "x";
			}
			
			//add a cherry powerup wherever there is ?
			else if(map.charAt(i) == '?') {
				powerup.add(new Powerup(x * scale, y * scale, scale));
				
				this.map += "?";
			}

			//add food wherever there is a blank space
			else if (map.charAt(i) == ' ') {
				food.add(new Food(x * scale, y * scale, scale));
				this.map += " ";
			}
			//when you reach a - start continue constructing the map on the next line
			else if(map.charAt(i) == '-'){
				this.map += "\n";
			}
			else if(map.charAt(i) == '=') {
				followers.add(new FollowingGhost(x * scale, y * scale, scale, "RedGhost.png"));
			}
			else if(map.charAt(i) == '@') {
				pinky.add(new Pinky(x * scale, y * scale, scale, "GreenGhost.png"));
			}
			else if(map.charAt(i) == 'e') {
				clyde.add(new Clyde(x * scale, y * scale, scale, "OrangeGhost.png"));
			}
			else if(map.charAt(i) == '&') {
				inky.add(new Inky(x * scale, y * scale, scale, "BlueGhost.png"));
			}
			else {
				this.map += "+";
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
	
	public Map(File file, int scale) {
		Scanner s;
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			s = null;
			e.printStackTrace();
		}
		
		map = "";
		
		while(s.hasNextLine()) {
			String next = s.nextLine();
			map += next;
			map  += "-";
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

	public void reset(int scale) {
		food.clear();
		powerup.clear();
		
		for(FollowingGhost fgs : followers) {
			fgs.restart();
		}
		
		for(Pinky p : pinky) {
			p.restart();
		}
		
		for(Inky i : inky) {
			i.restart();
		}
		
		for(Clyde c : clyde) {
			c.restart();
		}
		
		this.scale = scale;
		int x = 0; int y = 0;
		
		int counter = 0;
		for(int i = 0; i < map.length(); i++) {
			if(map.charAt(i) == ' ' || map.charAt(i) == '@' || map.charAt(i) == '&' || map.charAt(i) == '=' || map.charAt(i) == 'e') {
				food.add(new Food(x * scale, y * scale, scale));
			}
			
			if(map.charAt(i) == '?') {
				powerup.add(new Powerup(x * scale, y * scale, scale));
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
	
	public void log() {
		System.out.println(map);
	}
}
