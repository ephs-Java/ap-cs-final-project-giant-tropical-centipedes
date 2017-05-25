package V1_Game;

import java.awt.Graphics;

public class CostMap {
	int[][] map;

	Vector pos;

	Map m;

	public CostMap(Map m) {
		map = new int[m.height / m.scale][m.width / m.scale]; // int[row][col]

		this.m = m;
	}

	public void makeMap(Vector target) {

		reset();

		setCost(target.x() / m.scale, target.y() / m.scale, 0);
	}

	public void setCost(int col, int row, int counter) {
		counter++;

		boolean wall = false;
		for (Wall w : m.walls) {
			if (col * m.scale == w.x && row * m.scale == w.y) {
				wall = true;
			}
		}

		int temp = map[row][col];

		boolean ghost = false;
		for (FollowingGhost fg : m.followers) {
			if (col * m.scale == fg.pos.x() && row * m.scale == fg.pos.y()) {
				ghost = true;
			}
		}

		if (!wall && !ghost) {
			if (!(temp < counter && temp != 0)) {
				map[row][col] = counter;
			}
		} else {
			map[row][col] = -1;
		}

		if (!wall) {
			if (col != 0 && (counter + 1 < map[row][col - 1] || map[row][col - 1] == 0)) {
				setCost(col - 1, row, counter);
			}

			if (row != 0 && (counter + 1 < map[row - 1][col] || map[row - 1][col] == 0)) {
				setCost(col, row - 1, counter);
			}

			if (col != m.width / m.scale - 1 && (counter + 1 < map[row][col + 1] || map[row][col + 1] == 0)) {
				setCost(col + 1, row, counter);
			}

			if (row != m.height / m.scale - 1 && (counter + 1 < map[row + 1][col] || map[row + 1][col] == 0)) {
				setCost(col, row + 1, counter);
			}
		}
	}

	public void reset() {
		for (int col = 0; col < map[0].length; col++) {
			for (int row = 0; row < map.length; row++) {
				map[row][col] = 0;
			}
		}
	}

	/*
	 * public void drawMap() { for(int col = 0; col < map[0].length; col++) {
	 * for(int row = 0; row < map.length; row++) {
	 * System.out.print(map[row][col] + "\t"); } System.out.println(); } }
	 */

	/*
	 * public static void main(String[] args) { String map = "***********-"
	 * +"* x 0     *-" +"*         *-" +"*         *-" +"*  * *    *-"
	 * +"*  ***    *-" +"*         *-" +"*         *-" +"*     ?   *-"
	 * +"***********-"; Map m = new Map(map, 25);
	 * 
	 * m.log();
	 * 
	 * CostMap cm = new CostMap(m);
	 * 
	 * cm.makeMap(m.start); //cm.drawMap();
	 * 
	 * }
	 */

	public String bestDir(Vector pos) {
		int up = pos.y() / m.scale - 1 < 0 ? 0 : map[pos.y() / m.scale - 1][pos.x() / m.scale];
		int down = pos.y() / m.scale + 1 > m.height - m.scale ? 0 : map[pos.y() / m.scale + 1][pos.x() / m.scale];
		int left = pos.x() / m.scale - 1 < 0 ? 0 : map[pos.y() / m.scale][pos.x() / m.scale - 1];
		int right = pos.x() / m.scale + 1 > m.width - m.scale ? 0 : map[pos.y() / m.scale][pos.x() / m.scale + 1];

		String smallest = "none";
		int small = Integer.MAX_VALUE;

		if (up > 0 && up < small) {
			smallest = "up";
			small = up;
		}

		if (down > 0 && down < small) {
			smallest = "down";
			small = down;
		}

		if (left > 0 && left < small) {
			smallest = "left";
			small = left;
		}

		if (right > 0 && right < small) {
			smallest = "right";
		}

		return smallest;
	}

	public void draw(Graphics g) {
		for (int i = 2; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				g.drawString(map[i][j] + "", j * m.scale + 5, i * m.scale + 15);
			}
		}
	}
}
