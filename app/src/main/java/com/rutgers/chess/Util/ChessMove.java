package com.rutgers.chess.Util;

import java.io.Serializable;

public class ChessMove implements Serializable {
    private static final long serialVersionUID = 1L;
    public int fromCol;
    public int fromRow;
    public int toCol;
    public int toRow;
    public boolean draw;
    public boolean resign;
    public String player;

    public ChessMove(int fromCol, int fromRow, int toCol, int toRow, String player) {
        this.fromCol = fromCol;
        this.fromRow = fromRow;
        this.toCol = toCol;
        this.toRow = toRow;
        this.player = player;
    }

    public ChessMove(String player) {
        this.player = player;
    }

    public void setDraw() {
        draw = true;
    }

    public void setResign() {
        resign = true;
    }
}
