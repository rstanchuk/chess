package chess;
/**
 * Square represents a spot on the chess board
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */

public class Square {
	// private Player player;
	private Piece piece;
	private String floor;
	private boolean isEmpty;

	/**
	 * Construct chess board square with:
	 * @param floor value
	 */
	public Square(String floor) {
		this.floor = floor;
		isEmpty = true;
	}

	/**
	 * construct square with:
	 * @param player player instance
	 * @param piece piece instance
	 * @param floor floor  value
	 */
	public Square(Player player, Piece piece, String floor) {
		this.piece = piece;
		this.piece.setPlayer(player);
		this.floor = floor;
		isEmpty = false;

	}

	/**
	 * construct square with:
	 * @param square square instance
	 * @param floor floor value
	 */
	public Square(Square square, String floor) {
		// this.player = square.getPlayer();
		this.piece = square.getPiece();
		this.floor = floor;
		isEmpty = false;
	}

	/**
	 * check if square is empty
	 * @return boolean value
	 */
	public boolean isEmpty() {
		return isEmpty;
	}

	/**
	 * make square empty
	 * @param isEmpty boolean
	 */
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	/**
	 * toString
	 */
	public String toString() {
		if (!isEmpty())
			return piece.getPlayer().getName() + piece.getPic();
		else
			return floor;
	}

	/**
	 * get Piece in square
	 * @return Piece instance
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * set piece in square
	 * @param piece Piece instance
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * get floor value of square
	 * @return String
	 */
	public String getFloor() {
		return floor;
	}

	/**
	 * set floor value of square
	 * @param floor String
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}

}
