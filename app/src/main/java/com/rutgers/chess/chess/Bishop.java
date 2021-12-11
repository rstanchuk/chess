package chess;


/**
 * Bishop is a class that extends Piece class.
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */
public class Bishop extends Piece {
	
	/**
	 * Constructor for Bishop.
	 */
	public Bishop() {
		this.pic = "B";
	}
	
	/**
	 * Checks if Bishop can move from origin rank to destination rank.
	 */
	@Override
	public boolean isPossibleMove(int originRank, int originFile, int destinationRank, int destinationFile,
			String playerName, Square square[][]) {
		boolean isMoving = false;
		// check if is a possible move
		isMoving = Math.abs(originRank - destinationRank) == Math.abs(originFile - destinationFile);

		if (!isMoving)
			return isMoving;

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

	/**
	 * Checks if it is possible to kill the Piece.
	 */
	@Override
	public boolean isPossibleKill(int originRank, int originFile, int destinationRank, int destinationFile,
			String playerName, Square square[][]) {
		return isPossibleMove(originRank, originFile, destinationRank, destinationFile, playerName, square);
	}
	
	

}
