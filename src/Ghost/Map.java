package Ghost;

import java.util.ArrayList;

public class Map extends ArrayList<Wall> {
	int scale = 10;
	
	int w, h;
	
	public Map(int w, int h) {
		this.w = w;
		this.h = h;
		
		generateMap();
	}
	
	public void generateMap() {
		clear();
		for(int i = 0; i < h; i += scale) {
			for(int j = 0; j < w; j += scale) {
				int rand = (int)(Math.random() * 100);
				if(rand < 5) {
					add(new Wall(j, i, scale));
				}
			}
		}
		
		for(int i = 0; i < h; i+= scale) {
			add(new Wall(0, i, scale));
		}
		
		for(int i = 0; i < h; i+= scale) {
			add(new Wall(w - scale, i, scale));
		}
		
		for(int i = 0; i < w; i += scale) {
			add(new Wall(i, 0, scale));
		}
		
		for(int i = 0; i < w; i += scale) {
			add(new Wall(i, h - scale, scale));
		}
	}
	
	public static void main(String[] args) {
		Map m = new Map(500, 500);
	}
	
	public boolean up(int x, int y) {
		for(Wall w : this) {
			if(y - scale == w.y && x == w.x) {
				return false;
			}
		}
		return true;
	}
	
	public boolean down(int x, int y) {
		for(Wall w : this) {
			if(y + scale == w.y && x == w.x) {
				return false;
			}
		}
		return true;
	}
	
	public boolean left(int x, int y) {
		for(Wall w : this) {
			if(x - scale == w.x && y == w.y) {
				return false;
			}
		}
		return true;
	}
	
	public boolean right(int x, int y) {
		for(Wall w : this) {
			if(x + scale == w.x && y == w.y) {
				return false;
			}
		}
		return true;
	}
}
