package V1_Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class FollowingGhost extends Ghost {

	boolean run = false;

	Vector start;
	Vector corner;

	int width = 0, height = 0;

	boolean up, down, left, right;

	boolean scatter = false;

	public FollowingGhost(int x, int y, int scale, String name) {
		super(x, y, scale, name);
		speed = 2;

		up = false;
		down = false;
		left = false;
		right = true;

		start = new Vector(x, y);
		corner = new Vector(start.x, start.y);
	}

	public void setCorner(int x, int y) {
		corner = new Vector(x, y);
	}

	public void setSize(int w, int h) {
		width = w;
		height = h;
	}

	int duration = 100 / 5;
	int runCounter = 0;

	int subSpeed = 20;
	int subCounter = subSpeed;

	public void move(Vector target, ArrayList<Wall> walls) {

		if (counter == 0) {

			if (subCounter <= 19) {
				scatter = false;
			} else {
				scatter = true;
			}

			//System.out.println(scatter);

			if (subCounter == 0) {

				subCounter = subSpeed;
			} else {
				subCounter--;
			}

			Vector prevPos = new Vector(pos.x, pos.y);

			Vector t = new Vector();
			if (!scatter) {
				t = new Vector(target.x, target.y);
			} else {
				t = new Vector(corner.x, corner.y);
			}

			Vector u = new Vector(), d = new Vector(), l = new Vector(), r = new Vector();
			boolean uOkay = true, dOkay = true, lOkay = true, rOkay = true;
			
			if (pos.x % size == 0 && pos.y % size == 0) {

				Vector posScale = new Vector(pos.x, pos.y);
				if (up) {
					posScale.y -= size;
				} else if (down) {
					posScale.y += size;
				} else if (left) {
					posScale.x -= size;
				} else if (right) {
					posScale.x += size;
				}

				u = new Vector(posScale.x, posScale.y - size);
				d = new Vector(posScale.x, posScale.y + size);
				l = new Vector(posScale.x - size, posScale.y);
				r = new Vector(posScale.x + size, posScale.y);

				for (Wall w : walls) {
					
					if ((w.collide(u)) || u.equals(prevPos)) {
						uOkay = false;
					}

					if ((w.collide(d)) || d.equals(prevPos)) {
						dOkay = false;
					}

					if ((w.collide(l)) || l.equals(prevPos)) {
						lOkay = false;
					}
					
					if ((w.collide(r)) || r.equals(prevPos)) {
						rOkay = false;
					}
				}

				u.sub(t);
				d.sub(t);
				l.sub(t);
				r.sub(t);
			}

			if (up) {
				pos.y -= size;
			} else if (down) {
				pos.y += size;
			} else if (left) {
				pos.x -= size;
			} else if (right) {
				pos.x += size;
			}

			if (pos.x >= width) {
				pos.x = 0;
			} else if (pos.x < 0) {
				pos.x = width - size;
			}

			if (pos.y >= height) {
				pos.y = 0;
			} else if (pos.y < 0) {
				pos.y = height - size;
			}

			if (pos.x % size == 0 && pos.y % size == 0) {
				String smallest = "none";
				double small = Integer.MAX_VALUE;

				if (uOkay && u.mag() < small) {
					smallest = "up";
					small = u.mag();
				}

				if (dOkay && d.mag() < small) {
					smallest = "down";
					small = d.mag();
				}

				if (lOkay && l.mag() < small) {
					smallest = "left";
					small = l.mag();
				}

				if (rOkay && r.mag() < small) {
					smallest = "right";
				}
				
				switch (smallest) {
				case "up":
					up = true;
					down = false;
					left = false;
					right = false;
					break;
				case "down":
					up = false;
					down = true;
					left = false;
					right = false;
					break;
				case "left":
					up = false;
					down = false;
					left = true;
					right = false;
					break;
				case "right":
					up = false;
					down = false;
					left = false;
					right = true;
					break;
				case "none":
					if (up) {
						down = true;
						up = false;
					} else if (down) {
						down = false;
						up = true;
					} else if (left) {
						left = false;
						right = true;
					} else if (right) {
						left = true;
						right = false;
					}
				}
			}
			counter = speed;
		} else {
			counter--;
		}
	}

}
