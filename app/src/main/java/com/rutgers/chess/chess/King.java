package chess;

/**
 * King is an extended class from Piece. It represents a king on a chess game.
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */

public class King extends Piece {
	/**
	 * to check if king was moved before, if it is true then castling otherwise, illegal move
	 */
	private boolean isFirstMove = true;
	/**
	 * to check if king is in check, if so, it is not possible to use castling
	 */
	private boolean isInCheck = false;

	/**
	 * Default constructor
	 */
	public King() {
		this.pic = "K";
	}

	/**
	 * check if it is first move
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
	 * check if king was checked
	 * @return boolean value
	 */
	public boolean isInCheck() {
		return isInCheck;
	}

	/**
	 * set flag that king is in check
	 * @param isInCheck boolean value
	 */
	public void setInCheck(boolean isInCheck) {
		this.isInCheck = isInCheck;
	}

	/**
	 * Checks if King can move from origin rank to destination rank.
	 */
	@Override
	public boolean isPossibleMove(int originRank, int originFile, int destinationRank, int destinationFile,
			String playerName, Square square[][]) {
		boolean isMoving = false;
		isMoving = (originFile == destinationFile && Math.abs(originRank - destinationRank) == 1
				|| originRank == destinationRank && Math.abs(originFile - destinationFile) == 1
				|| Math.abs(originFile - destinationFile) == 1 && Math.abs(originRank - destinationRank) == 1);

		if (!isMoving) { // check if king wants castling
			int steps = Math.abs(originFile - destinationFile);

			if (isFirstMove && !isInCheck && steps == 2 && originRank == destinationRank) { // king has not moved before
																							// and it is not in check

				boolean isPathFree = true;

				if (originFile < destinationFile) {
					for (int file = originFile + 1; file < originFile + 2; file++) {
						isPathFree = isPathFree && square[originRank][file].isEmpty();
					}

				} else {
					for (int file = destinationFile - 1; file < destinationFile + 1; file++) {
						isPathFree = isPathFree && square[originRank][file].isEmpty();
						// System.out.println(square[originRank][file].getPiece().getPic());

					}
				}

				if (isPathFree) {
					if (playerName == "w") { // white
						// check if there is a rook next to destination
						if (originFile < destinationFile) // go to right
						{
							if (square[destinationRank][destinationFile + 1].getPiece() != null) {
								if (square[destinationRank][destinationFile + 1].getPiece().getPic() == "R") {

									Rook rook = (Rook) square[destinationRank][destinationFile + 1].getPiece();
									if (rook.isFirstMove()) {
										// move rook
										((Rook) square[destinationRank][destinationFile + 1].getPiece())
												.setFirstMove(false);
										square[destinationRank][destinationFile - 1]
												.setPiece(square[destinationRank][destinationFile + 1].getPiece());

										// System.out.println(((Rook)square[destinationRank][destinationFile+1].getPiece()).isFirstMove());
										square[destinationRank][destinationFile - 1].setEmpty(false);

										// empty origin
										square[destinationRank][destinationFile + 1].setPiece(null);
										square[destinationRank][destinationFile + 1].setEmpty(true);

										isMoving = true;

									}
								}

							}

						} else { // go to left
							if (originFile > destinationFile) {
								if (square[destinationRank][destinationFile - 2].getPiece() != null) {
									if (square[destinationRank][destinationFile - 2].getPiece().getPic() == "R") {

										Rook rook = (Rook) square[destinationRank][destinationFile - 2].getPiece();
										if (rook.isFirstMove()) {
											// move rook
											((Rook) square[destinationRank][destinationFile - 2].getPiece())
													.setFirstMove(false);
											square[destinationRank][destinationFile + 1]
													.setPiece(square[destinationRank][destinationFile - 2].getPiece());

											// System.out.println(((Rook)square[destinationRank][destinationFile+1].getPiece()).isFirstMove());
											square[destinationRank][destinationFile + 1].setEmpty(false);

											// empty origin
											square[destinationRank][destinationFile - 2].setPiece(null);
											square[destinationRank][destinationFile - 2].setEmpty(true);

											isMoving = true;

										}
									}

								}

							}
						}

					} else { // black
								// check if there is a rook next to destination
								// System.out.println(originFile);
								// System.out.println(destinationFile);
						if (originFile < destinationFile) {
							if (square[destinationRank][destinationFile + 1].getPiece() != null) {
								if (square[destinationRank][destinationFile + 1].getPiece().getPic() == "R") {

									Rook rook = (Rook) square[destinationRank][destinationFile + 1].getPiece();
									if (rook.isFirstMove()) {
										// move rook
										((Rook) square[destinationRank][destinationFile + 1].getPiece())
												.setFirstMove(false);
										square[destinationRank][destinationFile - 1]
												.setPiece(square[destinationRank][destinationFile + 1].getPiece());

										// System.out.println(((Rook)square[destinationRank][destinationFile+1].getPiece()).isFirstMove());
										square[destinationRank][destinationFile - 1].setEmpty(false);

										// empty origin
										square[destinationRank][destinationFile + 1].setPiece(null);
										square[destinationRank][destinationFile + 1].setEmpty(true);

										isMoving = true;

									}
								}

							}

						} else {
							if (originFile > destinationFile) {
								if (square[destinationRank][destinationFile - 2].getPiece() != null) {
									if (square[destinationRank][destinationFile - 2].getPiece().getPic() == "R") {

										Rook rook = (Rook) square[destinationRank][destinationFile - 2].getPiece();
										if (rook.isFirstMove()) {
											// move rook
											((Rook) square[destinationRank][destinationFile - 2].getPiece())
													.setFirstMove(false);
											square[destinationRank][destinationFile + 1]
													.setPiece(square[destinationRank][destinationFile - 2].getPiece());

											// System.out.println(((Rook)square[destinationRank][destinationFile+1].getPiece()).isFirstMove());
											square[destinationRank][destinationFile + 1].setEmpty(false);

											// empty origin
											square[destinationRank][destinationFile - 2].setPiece(null);
											square[destinationRank][destinationFile - 2].setEmpty(true);

											isMoving = true;

										}
									}

								}

							}
						}
					}

				}

			}
		}

		if (isMoving)
			setFirstMove(false);
		return isMoving;
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
