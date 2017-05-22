package Kyle;

import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
	
	String map;
	
	int scale;
	
	int w, h;
	
	ArrayList board = new ArrayList();
	
	public Map(String m, int scale) {
		this.scale = scale;
		map = m;
		
		makeMap();
	}
	
	public void makeMap() {
		int x = 0, y = 0;
		
		int mx = 0;
		int my = 0;
		
		for(int i = 0; i < map.length(); i++) {
			
			
			if(map.charAt(i) == '*') {
				board.add(new Wall(x * scale, y * scale, scale));
			}
			
			if(map.charAt(i) == '-') {
				y++;
				x = 0;
			}
			else {
				x++;
			}
			
			mx = x > mx ? x : mx;
			my = y > my ? y : my;
		}
		
		w = (mx) * scale + 1;
		h = (my + 1) * scale + 23;
	}
	
	int start = 0;
	
	public void draw(Graphics g) {
		for(int i = 0; i < board.size(); i++) {
			((Wall) board.get(i)).draw(g);
		}
	}
	
	public void update() {
		for(int i = 0; i < board.size(); i++) {
			
		}
	}
	
	public void press() {
		for(int i = 0; i < board.size(); i++) {
			
		}
	}
	
	public void release() {
		for(int i = 0; i < board.size(); i++) {
			
		}
	}
}
