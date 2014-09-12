package simulator;

public class NodeIdentity {

	//coordinates in the plane where this node is located
	double x,y;

	//unique id for this node (can be used as an index for storing the nodes since the value of the identifiers for the nodes are all in range [0,N-1] where N is the total number of nodes
	int idx;

	public NodeIdentity(double x, double y, int idx) {
		super();
		this.x = x;
		this.y = y;
		this.idx = idx;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getIdx() {
		return idx;
	}

}
