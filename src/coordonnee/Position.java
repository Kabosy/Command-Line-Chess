package coordonnee;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x=x;
		this.y=y;	
	}
	/**
	 * retourne la coordonnee x
	 * @return la coordonnee x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x
	 * change la coordonnee x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * retourne la coordonnee y
	 * @return la coordonnee y
	 */
	public int getY() {
		return y;
	}
/**
 * change la coordonnee y
 * @param y
 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * soustrait une position
	 * @param pos
	 * @return une nouvelle position
	 */
	public Position differenceDePos(Position pos) {
		return new Position(this.getX()-pos.getX(), this.getY()-pos.getY());
	}

	
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}



}
