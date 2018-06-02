import java.lang.Math;

public class QuadTree{
	private QuadNode root;
	private Point topLeftBound;
	private Point bottomRightBound;
	private QuadTree topLeft;
	private QuadTree topRight;
	private QuadTree bottomLeft;
	private QuadTree bottomRight;
	public QuadTree(){
		root = new QuadNode();
		topLeftBound = new Point();
		bottomRightBound = new Point();
		topLeft = topRight = bottomLeft = bottomRight = null;
	}
	public QuadTree(int[] arr, Point P1, Point P2){
		root = null;
		topLeftBound = P1;
		bottomRightBound = P2;
		// Calculate Average
		long avgr = 0, avgb = 0, avgg = 0, nump;
		nump = (P2.getY() - P1.getY() + 1) * (P2.getX() - P1.getX() + 1);
		for(int i=P1.getX();i<=P2.getX();++i){
			for(int j=P1.getY();j<=P2.getY();++i){
				int tmp = arr[i*arr.length + j];
				avgr += (tmp>>16)&0xFF;
				avgg += (tmp>>8)&0xFF;
				avgb += tmp&0xFF;
			}
		}
		avgr /= nump;
		avgg /= nump;
		avgb /= nump;
		if(P1.getX()==P2.getX()||P1.getY()==P2.getY()){
			root = new QuadNode(int((avgr<<16)+(avgg<<8)+avgb));
		}
		topLeft = topRight = bottomLeft = bottomRight = null;
	}
	public QuadTree(QuadNode Q){
		root = new QuadNode(Q.getCenter(), Q.getInfo());
		topLeftBound = new Point();
		bottomRightBound = new Point();
		topLeft = topRight = bottomLeft = bottomRight = null;
	}
	public QuadTree(Point P1, Point P2, QuadNode Q){
		root = new QuadNode(Q.getCenter(), Q.getInfo());
		topLeftBound = P1;
		bottomRightBound = P2;
		topLeft = topRight = bottomLeft = bottomRight = null;
	}
	public QuadNode getRoot(){
		return root;
	}
	public Point getTopLeftBound(){
		return topLeftBound;
	}
	public Point getBottomRightBound(){
		return bottomRightBound;
	}
	public QuadTree getTopLeft(){
		return topLeft;
	}
	public QuadTree getTopRight(){
		return topRight;
	}
	public QuadTree getBottomLeft(){
		return bottomLeft;
	}
	public QuadTree getBottomRight(){
		return bottomRight;
	}
	public void setRoot(QuadNode _root){
		root = _root;
	}
	public void setTopLeftBound(Point P){
		topLeftBound = P;
	}
	public void setBottomRightBound(Point P){
		bottomRightBound = P;
	}
	public void setTopLeft(QuadTree Q){
		topLeft = Q;
	}
	public void setTopRight(QuadTree Q){
		topRight = Q;
	}
	public void setBottomLeft(QuadTree Q){
		bottomLeft = Q;
	}
	public void setBottomRight(QuadTree Q){
		bottomRight = Q;
	}
	public boolean isOneElement(){
		return topLeft == null && topRight == null && bottomLeft == null && bottomRight == null;
	}
	public boolean isInBoundary(Point P){
		return P.getX() >= topLeftBound.getX() && P.getX() <= bottomRightBound.getX()
		&& P.getY() >= topLeftBound.getY() && P.getY() <= bottomRightBound.getY();
	}
	public int countNodes(){
		if(isOneElement()){
			return 1;
		}
		else{
			int c1 = 0, c2 = 0, c3 = 0, c4 = 0;
			if(topLeft != null){
				c1 = topLeft.countNodes();
			}
			if(topRight != null){
				c1 = topRight.countNodes();
			}
			if(bottomLeft != null){
				c1 = bottomLeft.countNodes();
			}
			if(bottomRight != null){
				c1 = bottomRight.countNodes();
			}
			return 1 + c1 + c2 + c3 + c4;
		}
	}
	public int countLeaves(){
		if(isOneElement()){
			return 1;
		}
		else{
			int c1 = 0, c2 = 0, c3 = 0, c4 = 0;
			if(topLeft != null){
				c1 = topLeft.countNodes();
			}
			if(topRight != null){
				c1 = topRight.countNodes();
			}
			if(bottomLeft != null){
				c1 = bottomLeft.countNodes();
			}
			if(bottomRight != null){
				c1 = bottomRight.countNodes();
			}
			return c1 + c2 + c3 + c4;
		}
	}
	public int depth(){
		if(isOneElement()){
			return 0;
		}
		else{
			int h1 = 1, h2 = 1, h3 = 1, h4 = 1;
			if(topLeft != null){
				h1 += topLeft.depth();
			}
			if(topRight != null){
				h2 += topRight.depth();
			}
			if(bottomLeft != null){
				h3 += bottomLeft.depth();
			}
			if(bottomRight != null){
				h4 += bottomRight.depth();
			}
			return Math.max(h1, Math.max(h2, Math.max(h3, h4)));
		}
	}
	public int search(Point P){
		if(isInBoundary(P)){
			if(P.getY() <= (topLeftBound.getY() + bottomRightBound.getY())/2){
				if(P.getX() <= (topLeftBound.getX() + bottomRightBound.getX())/2){
					if(topLeft != null){
						return topLeft.search(P);
					}
					else{
						return root.getInfo();
					}
				}
				else{
					if(topRight != null){
						return topRight.search(P);
					}
					else{
						return root.getInfo();
					}
				}
			}
			else{
				if(P.getX() <= (topLeftBound.getX() + bottomRightBound.getX())/2){
					if(bottomLeft != null){
						return bottomLeft.search(P);
					}
					else{
						return root.getInfo();
					}
				}
				else{
					if(bottomRight != null){
						return bottomRight.search(P);
					}
					else{
						return root.getInfo();
					}
				}
			}
		}
		else{
			return Point.DEFAULT_VALUE;
		}
	}
	public void insert(QuadNode QN){
		if(isInBoundary(QN.getCenter())){
			if(QN.getCenter().getY() <= (topLeftBound.getY() + bottomRightBound.getY())/2){
				if(QN.getCenter().getX() <= (topLeftBound.getX() + bottomRightBound.getX())/2){
					if(topLeft != null){
						topLeft.insert(QN);
					}
					else{
						Point P1 = new Point(topLeftBound.getX(), topLeftBound.getY());
						Point P2 = new Point((topLeftBound.getX() + bottomRightBound.getX())/2, (topLeftBound.getY() + bottomRightBound.getY())/2);
						topLeft = new QuadTree(P1, P2, QN);
					}
				}
				else{
					if(topRight != null){
						topRight.insert(QN);
					}
					else{
						Point P1 = new Point((topLeftBound.getX() + bottomRightBound.getX())/2, topLeftBound.getY());
						Point P2 = new Point(bottomRightBound.getX(), (topLeftBound.getY() + bottomRightBound.getY())/2);
						topRight = new QuadTree(P1, P2, QN);
					}
				}
			}
			else{
				if(QN.getCenter().getX() <= (topLeftBound.getX() + bottomRightBound.getX())/2){
					if(bottomLeft != null){
						bottomLeft.insert(QN);
					}
					else{
						Point P1 = new Point(topLeftBound.getX(), (topLeftBound.getY() + bottomRightBound.getY())/2);
						Point P2 = new Point((topLeftBound.getX() + bottomRightBound.getX())/2, bottomRightBound.getY());
						bottomLeft = new QuadTree(P1, P2, QN);
					}
				}
				else{
					if(bottomRight != null){
						bottomRight.insert(QN);
					}
					else{
						Point P1 = new Point((topLeftBound.getX() + bottomRightBound.getX())/2, (topLeftBound.getY() + bottomRightBound.getY())/2);
						Point P2 = new Point(bottomRightBound.getX(), bottomRightBound.getY());
						bottomRight = new QuadTree(P1, P2, QN);
					}
				}
			}
		}
	}
}
