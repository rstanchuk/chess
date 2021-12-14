package chess;

/**
 * Queen is an extended class from Piece. It represents a queen on a chess game.
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */

public class Queen extends Piece {

	/**
	 * Default constructor
	 */
	public Queen() {
		this.pic = "Q";
	}

	/**
	 * Check if it possible to make a move for Queeen
	 * @param originRow x
	 * @param originCol y
	 * @param destinationRow x
	 * @param destinationCol y
	 * @param isDestinationEmpty is there already Piece in that location
	 * @param playerName name of the player
	 * @return boolean value
	 */
	public boolean isPossibleMove(int originRow, int originCol, int destinationRow, int destinationCol,
			boolean isDestinationEmpty, String playerName) {
		return ((originCol == destinationCol && Math.abs(originRow - destinationRow) > 0)
				|| (originRow == destinationRow && Math.abs(originCol - destinationCol) > 0)
				|| (Math.abs(originRow - destinationRow) == Math.abs(originCol - destinationCol)));
	}

	/**
	 *  Check if it possible to make a move for Queeen
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
		// if moving as bishop
		if (Math.abs(originRank - destinationRank) == Math.abs(originFile - destinationFile)) {
			isMoving = true;
			boolean isBlockFree = true;
			// check if there is any "roadblock"
			int x = originFile;
			int y = originRank;
			while (x != destinationFile && y != destinationRank) {

				if (originFile < destinationFile)
					x++;
				else
					x--;

				if (originRank < destinationRank)
					y++;
				else
					y--;

				if (x != destinationFile && y != destinationRank) {
					isBlockFree = isBlockFree && square[y][x].isEmpty();
				}

			}
			return isMoving && isBlockFree;

		}
		// if moving as rook
		else if (((originFile == destinationFile && Math.abs(originRank - destinationRank) > 0)
				|| (originRank == destinationRank && Math.abs(originFile - destinationFile) > 0))) {
			isMoving = true;
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

		return isMoving;
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
