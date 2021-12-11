package chess;

/**
 * Board represents a 8x8 set of squares containing pieces
 * 
 * @author Roman Stanchuk
 * @author Ivan Pomajulca
 */
public class Board {

	private static Square[][] square = new Square[8][8];
	private static boolean isWhiteCheckmate = false;
	private static boolean isBlackCheckmate = false;
	private static boolean isWhiteCheck = false;
	private static boolean isBlackCheck = false;

	private static int wKingRank = 7; // keep the rank of the white king
	private static int bKingRank = 0; // keep the rank of the black king
	private static int wKingFile = 4; // keep the file of the white king
	private static int bKingFile = 4; // keep the file of the black king
	private static boolean isWKingDead = false;
	private static boolean isBKingDead = false;

	
	private static int[] wK = new int[2];
	private static int[] bK = new int[2];

	/**
	 * Populates board with Pieces.
	 */
	public static void createBoard() {
		Player white = new Player("w");
		Player black = new Player("b");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						square[i][j] = new Square("  ");
					} else {
						square[i][j] = new Square("##");
					}
				} else {
					if (j % 2 == 0) {
						square[i][j] = new Square("##");
					} else {
						square[i][j] = new Square("  ");
					}
				}

			}
		}

		square[6][0] = new Square(white, new Pawn(), square[6][0].getFloor());
		square[6][1] = new Square(white, new Pawn(), square[6][1].getFloor());
		square[6][2] = new Square(white, new Pawn(), square[6][2].getFloor());
		square[6][3] = new Square(white, new Pawn(), square[6][3].getFloor());
		square[6][4] = new Square(white, new Pawn(), square[6][4].getFloor());
		square[6][5] = new Square(white, new Pawn(), square[6][5].getFloor());
		square[6][6] = new Square(white, new Pawn(), square[6][6].getFloor());
		square[6][7] = new Square(white, new Pawn(), square[6][7].getFloor());
		square[7][0] = new Square(white, new Rook(), square[7][0].getFloor());
		square[7][1] = new Square(white, new Knight(), square[7][1].getFloor());
		square[7][2] = new Square(white, new Bishop(), square[7][2].getFloor());
		square[7][3] = new Square(white, new Queen(), square[7][3].getFloor());
		square[7][4] = new Square(white, new King(), square[7][4].getFloor());
		square[7][5] = new Square(white, new Bishop(), square[7][5].getFloor());
		square[7][6] = new Square(white, new Knight(), square[7][6].getFloor());
		square[7][7] = new Square(white, new Rook(), square[7][7].getFloor());
		square[1][0] = new Square(black, new Pawn(), square[1][0].getFloor());
		square[1][1] = new Square(black, new Pawn(), square[1][1].getFloor());
		square[1][2] = new Square(black, new Pawn(), square[1][2].getFloor());
		square[1][3] = new Square(black, new Pawn(), square[1][3].getFloor());
		square[1][4] = new Square(black, new Pawn(), square[1][4].getFloor());
		square[1][5] = new Square(black, new Pawn(), square[1][5].getFloor());
		square[1][6] = new Square(black, new Pawn(), square[1][6].getFloor());
		square[1][7] = new Square(black, new Pawn(), square[1][7].getFloor());
		square[0][0] = new Square(black, new Rook(), square[0][0].getFloor());
		square[0][1] = new Square(black, new Knight(), square[0][1].getFloor());
		square[0][2] = new Square(black, new Bishop(), square[0][2].getFloor());
		square[0][3] = new Square(black, new Queen(), square[0][3].getFloor());
		square[0][4] = new Square(black, new King(), square[0][4].getFloor());
		square[0][5] = new Square(black, new Bishop(), square[0][5].getFloor());
		square[0][6] = new Square(black, new Knight(), square[0][6].getFloor());
		square[0][7] = new Square(black, new Rook(), square[0][7].getFloor());

	}

	/**
	 * method to draw Chess Board including pieces
	 */
	public static void drawChessBoard() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(square[i][j] + " ");
			}
			System.out.print(8 - i);
			System.out.println();
		}
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();

	}

	/**
	 * method to print if a move is illegal
	 */
	public static void printIllegalMove() {
		System.out.print("Illegal move, try again");
	}

	/**
	 * method to  get if it is ok to move a piece from one place to another
	 * @param origin Piece instance
	 * @param originRow x
	 * @param originCol y
	 * @param destinationRow x
	 * @param destinationCol y
	 * @param square chess board
	 * @return boolean value
	 */
	public static boolean isPathClearToMove(Piece origin, int originRow, int originCol, int destinationRow,
			int destinationCol, Square square[][]) {
		// System.out.println(origin.getPic());
		return origin.isPossibleMove(originRow, originCol, destinationRow, destinationCol, origin.getPlayer().getName(),
				square);

	}

	/**
	 * method to  get if it is ok to kill an opponent  
	 * @param origin Piece instance
	 * @param originRow x
	 * @param originCol y
	 * @param destinationRow x
	 * @param destinationCol y
	 * @param square chess board
	 * @return boolean value
	 */
	public static boolean isPathClearToKill(Piece origin, int originRow, int originCol, int destinationRow,
			int destinationCol, Square square[][]) {
		return origin.isPossibleKill(originRow, originCol, destinationRow, destinationCol, origin.getPlayer().getName(),
				square);

	}

	/**
	 * move the piece after checking if it is possible, after moving we need to verify special situation
	 * for example, if there is a check, if there is a checkmate, a promotion
	 * @param originCol x
	 * @param originRow y
	 * @param destinationCol x
	 * @param destinationRow y
	 * @param playerName name
	 * @param promotion rank adjustment
	 * @return boolean that states if move is legal
	 */
	public static boolean move(int originCol, int originRow, int destinationCol, int destinationRow, String playerName,
			String promotion) {

		// already checked if it is under the board range

		// if there is a piece in the origin position
		// System.out.println(square[originRow][originCol].getPiece().getPic());

		if (!square[originRow][originCol].isEmpty()) {

			// if right player
			if (playerName.equals(square[originRow][originCol].getPiece().getPlayer().getName())) {
				// check if the path allow to move the piece from origin to destination

				// check if the destination is empty
				if (square[destinationRow][destinationCol].isEmpty()) {

					if (isPathClearToMove(square[originRow][originCol].getPiece(), originRow, originCol, destinationRow,
							destinationCol, square)) {
						// you can move from origin to destination
						square[destinationRow][destinationCol].setPiece(square[originRow][originCol].getPiece());
						square[destinationRow][destinationCol].setEmpty(false);
						// empty origin
						square[originRow][originCol].setPiece(null);
						square[originRow][originCol].setEmpty(true);

		
						
						/******* BEGIN: Check if it is PAWN to promote ****/
						Player white = new Player("w");
						Player black = new Player("b");

						if (playerName == "w") {
							if ((square[destinationRow][destinationCol].getPiece().getPic() == "p")
									&& destinationRow == 0) {


								if (promotion == null)
									promotion = "Q";
								if (promotion.equals("Q"))
									square[destinationRow][destinationCol] = new Square(white, new Queen(),
											square[destinationRow][destinationCol].getFloor());
								if (promotion.equals("R"))
									square[destinationRow][destinationCol] = new Square(white, new Rook(),
											square[destinationRow][destinationCol].getFloor());
								if (promotion.equals("N")) {
									// System.out.println("entre");
									square[destinationRow][destinationCol] = new Square(white, new Knight(),
											square[destinationRow][destinationCol].getFloor());
									// System.out.println(square[destinationRow][destinationCol].getPiece().getPic());
								}
								if (promotion.equals("B"))
									square[destinationRow][destinationCol] = new Square(white, new Bishop(),
											square[destinationRow][destinationCol].getFloor());

							}

						} else {
							if ((square[destinationRow][destinationCol].getPiece().getPic() == "p")
									&& destinationRow == 7) {
								if (promotion == null)
									promotion = "Q";
								if (promotion.equals("Q"))
									square[destinationRow][destinationCol] = new Square(black, new Queen(),
											square[destinationRow][destinationCol].getFloor());
								if (promotion.equals("R"))
									square[destinationRow][destinationCol] = new Square(black, new Rook(),
											square[destinationRow][destinationCol].getFloor());
								if (promotion.equals("N"))
									square[destinationRow][destinationCol] = new Square(black, new Knight(),
											square[destinationRow][destinationCol].getFloor());
								if (promotion.equals("B"))
									square[destinationRow][destinationCol] = new Square(black, new Bishop(),
											square[destinationRow][destinationCol].getFloor());

							}
						}

						if (square[destinationRow][destinationCol].getPiece().getPic() == "K") {

							if (playerName == "w") {
								wKingRank = destinationRow;
								wKingFile = destinationCol;

							}
							if (playerName == "b") {
								bKingRank = destinationRow;
								bKingFile = destinationCol;

							}

						}

						if (playerName == "w") {

							if (isPathClearToKill(square[destinationRow][destinationCol].getPiece(), destinationRow,
									destinationCol, bKingRank, bKingFile, square)) {
								isWhiteCheck = true;

							}


						} else {
							if (isPathClearToKill(square[destinationRow][destinationCol].getPiece(), destinationRow,
									destinationCol, wKingRank, wKingFile, square)) {
								isBlackCheck = true;

							}
						}

						return true;

					}

					// not empty destination
				} else {
					// if you get the opponent, you kill it
					if (playerName != square[destinationRow][destinationCol].getPiece().getPlayer().getName()) {

						if (isPathClearToKill(square[originRow][originCol].getPiece(), originRow, originCol,
								destinationRow, destinationCol, square)) {
							// if the destination piece is KING then checkmate
							if (square[destinationRow][destinationCol].getPiece().getPic() == "K") {
								// TODO: check if KING still have any alternative to move

								// if(playerName=="w") maxPiecesB=0; else maxPiecesW=0;
								// isCheckmate = true;
							} else {

								// if(playerName=="w") maxPiecesB--; else maxPiecesW--;

							}

							square[destinationRow][destinationCol].setPiece(square[originRow][originCol].getPiece());
							square[destinationRow][destinationCol].setEmpty(false);
							// empty origin
							square[originRow][originCol].setPiece(null);
							square[originRow][originCol].setEmpty(true);

							// check if it is PAWN to promote
							Player white = new Player("w");
							Player black = new Player("b");
							if (playerName == "w") {
								if ((square[destinationRow][destinationCol].getPiece().getPic() == "p")
										&& destinationRow == 0) {

									if (promotion == null)
										promotion = "Q";
									if (promotion.equals("Q"))
										square[destinationRow][destinationCol] = new Square(white, new Queen(),
												square[destinationRow][destinationCol].getFloor());
									if (promotion.equals("R"))
										square[destinationRow][destinationCol] = new Square(white, new Rook(),
												square[destinationRow][destinationCol].getFloor());
									if (promotion.equals("N"))
										square[destinationRow][destinationCol] = new Square(white, new Knight(),
												square[destinationRow][destinationCol].getFloor());
									if (promotion.equals("B"))
										square[destinationRow][destinationCol] = new Square(white, new Bishop(),
												square[destinationRow][destinationCol].getFloor());

								}

							} else {
								if ((square[destinationRow][destinationCol].getPiece().getPic() == "p")
										&& destinationRow == 7) {
									if (promotion == null)
										promotion = "Q";
									if (promotion.equals("Q"))
										square[destinationRow][destinationCol] = new Square(black, new Queen(),
												square[destinationRow][destinationCol].getFloor());
									if (promotion.equals("R"))
										square[destinationRow][destinationCol] = new Square(black, new Rook(),
												square[destinationRow][destinationCol].getFloor());
									if (promotion.equals("N"))
										square[destinationRow][destinationCol] = new Square(black, new Knight(),
												square[destinationRow][destinationCol].getFloor());
									if (promotion.equals("B"))
										square[destinationRow][destinationCol] = new Square(black, new Bishop(),
												square[destinationRow][destinationCol].getFloor());

								}
							}

							/******* END: Check if it is PAWN to promote ****/

							
							
							/*******BEGIN:  Check KING check & checkmate ****/
							/*TODO: Work on check and checkmate situation*/

							if (square[destinationRow][destinationCol].getPiece().getPic() == "K") {

								if (playerName == "w") {
									wKingRank = destinationRow;
									wKingFile = destinationCol;

								}
								if (playerName == "b") {
									bKingRank = destinationRow;
									bKingFile = destinationCol;

								}

							}

							if (playerName == "w") {

								if (isPathClearToKill(square[destinationRow][destinationCol].getPiece(), destinationRow,
										destinationCol, bKingRank, bKingFile, square)) {
									isWhiteCheck = true;

								} 


							} else {
								if (isPathClearToKill(square[destinationRow][destinationCol].getPiece(), destinationRow,
										destinationCol, wKingRank, wKingFile, square)) {
									isBlackCheck = true;

								}

							}

							/*******END:  Check KING check & checkmate ****/
							
							return true;

						}

					}

				}

			}
		}
		return false;

	}

	//method to determine if there is a checkmate to finalize the game
	//under construction

	/**
	 * was white king killed
	 * @return boolean value
	 */
	public static boolean isWKingDead() {
		return isWKingDead;
	}

	/**
	 * set white king dead
	 * @param isWKingDead boolean value
	 */
	public static void setWKingDead(boolean isWKingDead) {
		Board.isWKingDead = isWKingDead;
	}

	/**
	 * was black king killed
	 * @return isBKingDead boolean value
	 */
	public static boolean isBKingDead() {
		return isBKingDead;
	}

	/**
	 * set black king dead
	 * @param isBKingDead boolean value
	 */
	public static void setBKingDead(boolean isBKingDead) {
		Board.isBKingDead = isBKingDead;
	}

	/**
	 * get if white king is checkmate
	 * @return boolean value
	 */
	public static boolean isWhiteCheckmate() {
		return isWhiteCheckmate;
	}

	/**
	 * set white king checkmate
	 * @param isWhiteCheckmate boolean value
	 */
	public static void setWhiteCheckmate(boolean isWhiteCheckmate) {
		Board.isWhiteCheckmate = isWhiteCheckmate;
	}

	/**
	 * was black king checkmated
	 * @return boolean value
	 */
	public static boolean isBlackCheckmate() {
		return isBlackCheckmate;
	}

	/**
	 * set black king checkmate
	 * @param isBlackCheckmate boolean value
	 */
	public static void setBlackCheckmate(boolean isBlackCheckmate) {
		Board.isBlackCheckmate = isBlackCheckmate;
	}

	/**
	 * was white king checked
	 * @return boolean value
	 */
	public static boolean isWhiteCheck() {
		return isWhiteCheck;
	}

	/**
	 * set white check
	 * @param isWhiteCheck boolean value
	 */
	public static void setWhiteCheck(boolean isWhiteCheck) {
		Board.isWhiteCheck = isWhiteCheck;
	}

	/**
	 * was black king checked
	 * @return boolean value
	 */
	public static boolean isBlackCheck() {
		return isBlackCheck;
	}

	/**
	 * set black check
	 * @param isBlackCheck boolean value
	 */
	public static void setBlackCheck(boolean isBlackCheck) {
		Board.isBlackCheck = isBlackCheck;
	}
	
	/**
	 * method for "checkmate" checking
	 */
	public static void checkmate() {
		String[][] board = new String[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = square[i][j].toString();
			}
		}
		
		int result = checkBoard(board);
		int[] K;
		String king;
		if(result == 1) { //White king check
			K = wK;
			king = "wK";
		} else if(result == 2) { //Black king check
			K = bK;
			king = "bK";
		} else {
			Board.setWhiteCheck(false);
    		Board.setBlackCheck(false);
			return;
		}
		
		// Store all possible moves of the king
        int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
 
        for (int k = 0; k < 8; k++) {
 
            // incrementing index values
            int m = K[0] + x[k];
            int n = K[1] + y[k];
 
            // checking boundary conditions for every possible king move
            // and checking for legality
            // then checking that move for check (checkmate)
            if (inBounds(m, n) && board[K[0]][K[1]].charAt(0) != board[m][n].charAt(0)) {
            	String[][] tBoard = board;
            	tBoard[K[0]][K[1]] = "  ";
            	tBoard[m][n] = king;
            	result = checkBoard(tBoard);
            	if(result == 0) {
            		Board.setWhiteCheckmate(false);
            		Board.setBlackCheckmate(false);
            		return;
            	} else if(result == 1) {
            		Board.setWhiteCheckmate(true);
            	} else if(result == 2) {
            		Board.setBlackCheckmate(true);
            	}
            }
        }
	}
	
	/**
	 * Function to check if any of the two
	 * kings is unsafe or not
	 * @param board copy of chess board that only contains strings
	 * @return returns 1 if white king is checkmated, 2 if black king is checkmated, and 0 for neither
	 */
    private static int checkBoard(String[][] board) {
 
        // Find the position of both the kings
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
 
                // Check for all pieces which
                // can attack White King
                if (board[i][j].equals("wK")) {
                	wK[0] = i;
                	wK[1] = j;
                    // Check for Knight
                    if (lookForn(board, "bN", i, j))
                        return 1;
 
                    // Check for Pawn
                    if (lookForp(board, "bp", i, j))
                        return 1;
 
                    // Check for Rook
                    if (lookForr(board, "bR", i, j))
                        return 1;
 
                    // Check for Bishop
                    if (lookForb(board, "bB", i, j))
                        return 1;
 
                    // Check for Queen
                    if (lookForq(board, "bQ", i, j))
                        return 1;
 
                    // Check for King
                    if (lookFork(board, "bK", i, j))
                        return 1;
                }
 
                // Check for all pieces which
                // can attack Black King
                if (board[i][j].equals("bK")) {
                	bK[0] = i;
                	bK[1] = j;
                    // Check for Knight
                    if (lookForn(board, "wN", i, j))
                        return 2;
 
                    // Check for Pawn
                    if (lookForp(board, "wp", i, j))
                        return 2;
 
                    // Check for Rook
                    if (lookForr(board, "wR", i, j))
                        return 2;
 
                    // Check for Bishop
                    if (lookForb(board, "wB", i, j))
                        return 2;
 
                    // Check for Queen
                    if (lookForq(board, "wQ", i, j))
                        return 2;
 
                    // Check for King
                    if (lookFork(board, "wK", i, j))
                        return 2;
                }
            }
        }
        return 0;
    }
 
    /**
     * Function to check if King can attack another King
     * @param board chess board
     * @param c Piece name
     * @param i x
     * @param j y
     * @return boolean value
     */
    private static boolean lookFork(String[][] board, String c, int i, int j) {
 
        // Store all possible moves of the king
        int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
 
        for (int k = 0; k < 8; k++) {
 
            // incrementing index values
            int m = i + x[k];
            int n = j + y[k];
 
            // checking boundary conditions
            // and character match
            if (inBounds(m, n) && board[m][n].equals(c))
                return true;
        }
        return false;
    }
 
    /**
     * Function to check if Queen can attack the King
     * @param board chess board
     * @param c Piece name
     * @param i x
     * @param j y
     * @return boolean value
     */
    private static boolean lookForq(String[][] board, String c, int i, int j) {
 
        // Queen's moves are a combination
        // of both the Bishop and the Rook
        if (lookForb(board, c, i, j) || lookForr(board, c, i, j))
            return true;
 
        return false;
    }
 
    /**
     * Function to check if bishop can attack the king
     * @param board chess board
     * @param c Piece name
     * @param i x
     * @param j y
     * @return boolean value
     */
    private static boolean lookForb(String[][] board, String c, int i, int j) {
 
        // Check the lower right diagonal
        int k = 0;
        while (inBounds(i + ++k, j + k)) {
 
            if (board[i + k][j + k].equals(c))
                return true;
            if (!board[i + k][j + k].equals("##") && !board[i + k][j + k].equals("  "))  //board[i + k][j + k] != '-'
                break;
        }
 
        // Check the lower left diagonal
        k = 0;
        while (inBounds(i + ++k, j - k)) {
 
            if (board[i + k][j - k].equals(c))
                return true;
            if (!board[i + k][j - k].equals("##") && !board[i + k][j - k].equals("  ")) //board[i + k][j - k] != '-'
                break;
        }
 
        // Check the upper right diagonal
        k = 0;
        while (inBounds(i - ++k, j + k)) {
 
            if (board[i - k][j + k].equals(c))
                return true;
            if (!board[i - k][j + k].equals("##") && !board[i - k][j + k].equals("  ")) //board[i - k][j + k] != '-'
                break;
        }
 
        // Check the upper left diagonal
        k = 0;
        while (inBounds(i - ++k, j - k)) {
 
            if (board[i - k][j - k].equals(c))
                return true;
            if (!board[i - k][j - k].equals("##") && !board[i - k][j - k].equals("  ")) //board[i - k][j - k] != '-'
                break;
        }
 
        return false;
    }
 
    /**
     * Function to check if rook can attack the king
     * @param board chess board
     * @param c Piece name
     * @param i x
     * @param j y
     * @return boolean value
     */
    private static boolean lookForr(String[][] board, String c, int i, int j) {
 
        // Check downwards
        int k = 0;
        while (inBounds(i + ++k, j)) {
            if (board[i + k][j].equals(c))
                return true;
            if (!board[i + k][j].equals("##") && !board[i + k][j].equals("  "))//board[i + k][j] != '-'
                break;
        }
 
        // Check upwards
        k = 0;
        while (inBounds(i + --k, j)) {
            if (board[i + k][j].equals(c))
                return true;
            if (!board[i + k][j].equals("##") && !board[i + k][j].equals("  ")) //board[i + k][j] != '-'
                break;
        }
 
        // Check right
        k = 0;
        while (inBounds(i, j + ++k)) {
            if (board[i][j + k].equals(c))
                return true;
            if (!board[i][j + k].equals("##") && !board[i][j + k].equals("  ")) //board[i][j + k] != '-'
                break;
        }
 
        // Check left
        k = 0;
        while (inBounds(i, j + --k)) {
            if (board[i][j + k].equals(c))
                return true;
            if (!board[i][j + k].equals("##") && !board[i][j + k].equals("  ")) //board[i][j + k] != '-'
                break;
        }
        return false;
    }
 
    /**
     * Check if the knight can attack the king
     * @param board chess board
     * @param c Piece name
     * @param i x
     * @param j y
     * @return boolean value
     */
    private static boolean lookForn(String[][] board, String c, int i, int j) {
 
        // All possible moves of the knight
        int[] x = { 2, 2, -2, -2, 1, 1, -1, -1 };
        int[] y = { 1, -1, 1, -1, 2, -2, 2, -2 };
 
        for (int k = 0; k < 8; k++) {
 
            // Incrementing index values
            int m = i + x[k];
            int n = j + y[k];
 
            // Checking boundary conditions
            // and character match
            if (inBounds(m, n) && board[m][n].equals(c))
                return true;
        }
        return false;
    }
 
    /**
     * Function to check if pawn can attack the king
     * @param board chess board
     * @param c Piece name
     * @param i x
     * @param j y
     * @return boolean value
     */
    private static boolean lookForp(String[][] board, String c, int i, int j) {
 
        String lookFor;
        //white
        if (c.charAt(0) == 'w') { 
 
            // Check for white pawn
            lookFor = "wp";
            if (inBounds(i + 1, j - 1)
                && board[i + 1][j - 1].equals(lookFor))
                return true;
 
            if (inBounds(i + 1, j + 1)
                && board[i + 1][j + 1].equals(lookFor))
                return true;
        } else {
 
            // Check for black pawn
            lookFor = "bp";
            if (inBounds(i - 1, j - 1)
                && board[i - 1][j - 1].equals(lookFor))
                return true;
            if (inBounds(i - 1, j + 1)
                && board[i - 1][j + 1].equals(lookFor))
                return true;
        }
        return false;
    }
 
    /**
     * Check if the indices are within the matrix.
     * @param i x
     * @param j y
     * @return boolean value
     */
    private static boolean inBounds(int i, int j) {
 
        // Checking boundary conditions
        return i >= 0 && i < 8 && j >= 0 && j < 8;
    }
    
}
