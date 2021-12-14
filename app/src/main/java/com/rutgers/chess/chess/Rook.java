package chess;

/**
 * Rook is an extended class from Piece. It represents a rook on a chess game.
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */

public class Rook extends Piece {
	/**
	 * to control if rook was moved before, if it is true then castling
	 */
	private boolean isFirstMove = true;

	/**
	 * constructor
	 */
	public Rook() {
		this.pic = "R";
	}

	/**
	 * check if first move
	 * @return boolean value
	 */ 
	public boolean isFirstMove() {
		return isFirstMove;
	}

	/**
	 * set first move
	 * @param isFirstMove boolean value
	 */
	public void setFirstMove(boolean isFirstMove) {
		this.isFirstMove = isFirstMove;
	}

	/**
	 * Check if it possible to make a move for Rook
	 * @param originRank x
	 * @param originFile y
	 * @param destinationRank x
	 * @param destinationFile y
	 * @param playerName String
	 * @param square chess board
	 * @return boolean value
	 */
	@Override
	public boolean isPossibleMove(int originRank, int originFile, int destinationRank, int destinationFile,
			String playerName, Square square[][]) {
		boolean isMoving = false;
		// check if is a possible move
		isMoving = ((originFile == destinationFile && Math.abs(originRank - destinationRank) > 0)
				|| (originRank == destinationRank && Math.abs(originFile - destinationFile) > 0));

		if (!isMoving)
			return isMoving;

		boolean isBlockFree = true;

		// check if there is any "roadblock"
		if (originRank == destinationRank) {
			if (originFile < destinationFile) {
				for (int file = originFile + 1; file < destinationFile; file++) {
					isBlockFree = isBlockFree && square[originRank][file].isEmpty();
				}

			} else {
				for (int file = destinationFile + 1; file < originFile; file++) {
					isBlockFree = isBlockFree && square[originRank][file].isEmpty();
				}
			}

		} else if (originFile == destinationFile) {
			if (originRank < destinationRank) {
				for (int rank = originRank + 1; rank < destinationRank; rank++) {
					isBlockFree = isBlockFree && square[rank][originFile].isEmpty();
				}

			} else {
				for (int rank = destinationRank + 1; rank < originRank; rank++) {
					isBlockFree = isBlockFree && square[rank][originFile].isEmpty();
				}
			}

		} else
			isBlockFree = false;

		return isMoving && isBlockFree;
	}

	/**
	 * check if it possible to kill Piece
	 * @param originRank x
	 * @param originFile y
	 * @param destinationRank x
	 * @param destinationFile y
	 * @param playerName String
	 * @param square chess board
	 * @return boolean value
	 */
	@Override
	public boolean isPossibleKill(int originRank, int originFile, int destinationRank, int destinationFile,
			String playerName, Square square[][]) {
		return isPossibleMove(originRank, originFile, destinationRank, destinationFile, playerName, square);
	}

	

}
