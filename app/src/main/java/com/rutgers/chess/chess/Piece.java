package chess;

/**
 * Piece is an abstract class that represents a piece on a chess game.
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */


public abstract class Piece {
	protected String pic;
	private String name;
	protected int moves;
	private chess.Player player;
	
	/**
	 * Default constructor
	 */
	public Piece() {
	}

	
	/**
	 * to String
	 */
	public String toString() {
		return pic;
	}

	/**
	 * get name of Piece
	 * @return String name
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * set name of Piece
	 * @param pic String name
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}

	/**
	 * get name with color of Piece
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name with color of Piece
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get moves
	 * @return integer
	 */
	public int getMoves() {
		return moves;
	}

	/**
	 * set moves
	 * @param moves integer
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}

	/**
	 * get player of Piece
	 * @return Player class
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * set player of Piece
	 * @param player Player class
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * function to detect if it is possible to move piece 
	 * @param originRank orginal rank
	 * @param originFile original file
	 * @param destRank destination rank
	 * @param destFile destination file
	 * @param playerName name of player
	 * @param square chess board
	 * @return boolean value
	 */
	public abstract boolean isPossibleMove(int originRank, int originFile, int destRank, int destFile,
			String playerName, Square square[][]);

	/**
	 * function to detect if it is possible to kill 
	 * @param originRank orginal rank
	 * @param originFile original file
	 * @param destRank destination rank
	 * @param destFile destination file
	 * @param playerName name of player
	 * @param square chess board
	 * @return boolean value
	 */
	public abstract boolean isPossibleKill(int originRank, int originFile, int destRank, int destFile,
			String playerName, Square square[][]);

}
