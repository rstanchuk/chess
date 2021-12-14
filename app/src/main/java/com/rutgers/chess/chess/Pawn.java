package chess;

/**
 * Pawn is an extended class from Piece. It represents a pawn on a chess game.
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */

//import java.util.ArrayList;

public class Pawn extends Piece {
	/**
	 * only first move is allowed 2 positions.
	 */
	private boolean isFirstMove = true;
    private boolean isEnpassantRisk=false;
	/**
	 * check if first move
	 * @return boolean value
	 */
	public boolean isFirstMove() {
		return isFirstMove;
	}

	/**
	 * set flag if first move
	 * @param isFirstMove boolean value
	 */
	public void setFirstMove(boolean isFirstMove) {
		this.isFirstMove = isFirstMove;
	}

	/**
	 * Default constructor
	 */
	public Pawn() {
		this.pic = "p";
	}

	/**
	 * Checks if Pawn can move from origin rank to destination rank.
	 */
	@Override
	public boolean isPossibleMove(int originRank, int originFile, int destinationRank, int destinationFile,
			String playerName, Square square[][]) {
		int steps = 2;
		if (!isFirstMove)
			steps = 1;
		boolean isMoving = false;
		if (playerName == "w")
			isMoving = originFile == destinationFile && (originRank - destinationRank <= steps);
		else
			isMoving = originFile == destinationFile && (destinationRank - originRank <= steps);

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

		boolean isKilling = false;
		if (playerName == "w")
			isKilling = (originRank - destinationRank == 1 && originFile - 1 == destinationFile)
					|| (originRank - destinationRank == 1 && originFile + 1 == destinationFile);
		else
			isKilling = (destinationRank - originRank == 1 && originFile - 1 == destinationFile)
					|| (destinationRank - originRank == 1 && originFile + 1 == destinationFile);

		return isKilling;
	}
	


}
