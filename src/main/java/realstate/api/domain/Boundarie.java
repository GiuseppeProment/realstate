package realstate.api.domain;

public class Boundarie {
	
	private Coordinate bottomRight;
	private Coordinate upperLeft;

	public Boundarie() {
		super();
	}
	
	public Boundarie(Coordinate upperLeft, Coordinate bottomRight) {
		setUpperLeft(upperLeft);
		setBottomRight(bottomRight);
	}
	public Coordinate getBottomRight() {
		return bottomRight;
	}
	public Coordinate getUpperLeft() {
		return upperLeft;
	}
	public void setBottomRight(Coordinate bottomRight) {
		this.bottomRight = bottomRight;
	}
	public void setUpperLeft(Coordinate upperLeft) {
		this.upperLeft = upperLeft;
	}
	
	public boolean contains(int x, int y) {
		if ( x >= upperLeft.getX() && x <= bottomRight.getX() )
			if ( y <= upperLeft.getY() && y >= bottomRight.getY() )
				return true;
	return false;
	}
	
}
