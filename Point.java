public class Point{
	private int x, y;
	public static final int DEFAULT_VALUE = -999;
	public Point(){
		x = DEFAULT_VALUE;
		y = DEFAULT_VALUE;
	}
	public Point(int _x, int _y){
		x = _x;
		y = _y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int _x){
		x = _x;
	}
	public void setY(int _y){
		y = _y;
	}
	public boolean equals(Point P){
		return x == P.x && y == P.y;
	}
}
