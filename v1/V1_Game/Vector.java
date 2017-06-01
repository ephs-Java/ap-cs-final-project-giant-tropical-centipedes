package V1_Game;

public class Vector {
	public double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector() {
		x = 0;
		y = 0;
	}
	
	public int x() {
		return (int)x;
	}
	
	public int y() {
		return (int)y;
	}
	
	public int quadrant(Vector origin) {
		Vector diff = new Vector(x, y);
		diff.sub(origin);
		
		if(diff.x > 0 && diff.y < 0) {
			return 1;
		}
		else if(diff.x < 0 && diff.y < 0) {
			return 2;
		}
		else if(diff.x < 0 && diff.y > 0) {
			return 3;
		}
		else if(diff.x > 0 && diff.y > 0) {
			return 4;
		}
		else if(diff.x == 0 && diff.y == 0) {
			return 0;
		}
		else if(diff.x == 0) {
			return 5;
		}
		else if(diff.y == 0) {
			return 6;
		}
		else {
			return -1;
		}
	}
	
	public void sub(double num) {
		x -= num;
		y -= num;
	}
	
	public void sub(Vector v) {
		x -= v.x;
		y -= v.y;
	}
	
	public static Vector sub(Vector v1, Vector v2) {
		Vector diff = new Vector(v1.x, v1.y);
		diff.sub(v2);
		return diff;
	}
	
	public void add(double num) {
		x += num;
		y += num;
	}
	
	public void add(Vector v) {
		x += v.x;
		y += v.y;
	}
	
	public void mult(double scale) {
		x *= scale;
		y *= scale;
	}
	
	public void div(double scale) {
		x /= scale;
		y /= scale;
	}
	
	public String toString() {
		return "(" + (int)x + ", " + (int)y + ")";
	}
	
	public void limit(double num) {
		if(mag() > num) {
			setMag(num);
		}
	}
	
	public double mag() {
		return Math.sqrt((x * x) + (y * y));
	}
	
	
	public void clear() {
		x = 0;
		y = 0;
	}
	
	public void base() {
		double mag = mag();
		double val = 1 / mag;
		x *= val;
		y *= val;
	}
	
	public void setMag(double num) {
		base();
		mult(num);
	}
	
	public boolean equals(Vector v) {
		return (v.x == x && v.y == y);
	}
	
	public static void main(String[] args) {
		Vector v = new Vector(4, 3);
		double mag1 = v.mag();
		System.out.println("Before: " + mag1 + ", " + v);
		
		v.base();
		double mag2 = v.mag();
		System.out.println("After: " + mag2 + ", " + v);
		
		v.setMag(10);
		double mag3 = v.mag();
		System.out.println("Set to 10: " + mag3 + ", " + v);
	}
}
