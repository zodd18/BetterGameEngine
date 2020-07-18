package model.utils;

public class Vector2D {
	private int x, y;

	public Vector2D() {
		x = 0;
		y = 0;
	}

	public Vector2D(Vector2D pos) {
		new Vector2D(pos.getX(), pos.getY());
	}

	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void addX(int f) {
		x += f;
	}

	public void addY(int f) {
		y += f;
	}

	public void setVector(Vector2D v) {
		this.x = v.x;
		this.y = v.y;
	}

	public void setVector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
