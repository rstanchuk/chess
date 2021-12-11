package chess;

/**
 * Knight is an extended class from Piece. It represents a knight on a chess game.
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */

public class Knight extends Piece {

	/**
	 * Default constructor
	 */
	public Knight() {
		this.pic = "N";
	}

	/**
	 * Checks if Knight can move from origin rank to destination rank.
	 */
	@Override
	public boolean isPossibleMove(int originRank, int originFile, int destinationRank, int destinationFile,
			String playerName, Square square[][]) {
		if ((Math.abs(originRank - destinationRank) == 2 && Math.abs(originFile - destinationFile) == 1)
				|| (Math.abs(originFile - destinationFile) == 2 && Math.abs(originRank - destinationRank) == 1)) {
			return true;

		}
		return false;
	}

	/**
	 * Checks if it is possible to kill the Piece.
	 */
	@Override
	public boolean isPossibleKill(int originRank, int originFile, int destinationRank, int destinationFile,
			String playerName, Square square[][]) {
		return isPossibleMove(originRank, originFile, destinationRank, destinationFile, playerName, square);

	}
	
	


}
