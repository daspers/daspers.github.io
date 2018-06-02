public class QuadNode{
	private int info;
	public QuadNode(){
		info = Point.DEFAULT_VALUE;
	}
	public QuadNode(int _info){
		info = _info;
	}
	public int getInfo(){
		return info;
	}
	public void setInfo(int _info){
		info = _info;
	}
	public boolean equals(QuadNode Q){
		return info == Q.info;
	}
}
