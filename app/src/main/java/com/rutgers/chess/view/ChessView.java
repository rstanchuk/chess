package com.rutgers.chess.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rutgers.chess.MainActivity;
import com.rutgers.chess.R;

import java.util.HashMap;

public class ChessView extends View {
    private final String TAG = "ChessView";
    private static final int originX = 3; //origin position x
    private static final int originY = 3; //origin position y
    private static final int squareLength = 95; //length of the square
    private final Paint paint = new Paint();
    private final int[] pieceKeys = {
            R.drawable.chess_rdt60,
            R.drawable.chess_rlt60,
            R.drawable.chess_bdt60,
            R.drawable.chess_blt60,
            R.drawable.chess_ndt60,
            R.drawable.chess_nlt60,
            R.drawable.chess_qdt60,
            R.drawable.chess_qlt60,
            R.drawable.chess_kdt60,
            R.drawable.chess_klt60,
            R.drawable.chess_pdt60,
            R.drawable.chess_plt60};
    Resources res = getContext().getResources();
    HashMap<Integer, Bitmap> bitmaps = new HashMap<Integer, Bitmap>();

    private int[][] ChessBoard = new int[8][8];
    private int[][] prevChessBoard = new int[8][8];

    private int fromCol = -1;
    private int fromRow = -1;
    private int movingPiece = -1;

    private float movingPieceX = -1;
    private float movingPieceY = -1;

    //for undo
    private chess.Square[][] square;

    public boolean isWhiteMove = true;

    public ChessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //start pieces

        ChessBoard[0][0] = R.drawable.chess_rdt60;
        ChessBoard[0][1] = R.drawable.chess_ndt60;
        ChessBoard[0][2] = R.drawable.chess_bdt60;
        ChessBoard[0][3] = R.drawable.chess_qdt60;
        ChessBoard[0][4] = R.drawable.chess_kdt60;
        ChessBoard[0][5] = R.drawable.chess_bdt60;
        ChessBoard[0][6] = R.drawable.chess_ndt60;
        ChessBoard[0][7] = R.drawable.chess_rdt60;

        ChessBoard[1][0] = R.drawable.chess_pdt60;
        ChessBoard[1][1] = R.drawable.chess_pdt60;
        ChessBoard[1][2] = R.drawable.chess_pdt60;
        ChessBoard[1][3] = R.drawable.chess_pdt60;
        ChessBoard[1][4] = R.drawable.chess_pdt60;
        ChessBoard[1][5] = R.drawable.chess_pdt60;
        ChessBoard[1][6] = R.drawable.chess_pdt60;
        ChessBoard[1][7] = R.drawable.chess_pdt60;

        ChessBoard[7][0] = R.drawable.chess_rlt60;
        ChessBoard[7][1] = R.drawable.chess_nlt60;
        ChessBoard[7][2] = R.drawable.chess_blt60;
        ChessBoard[7][3] = R.drawable.chess_qlt60;
        ChessBoard[7][4] = R.drawable.chess_klt60;
        ChessBoard[7][5] = R.drawable.chess_blt60;
        ChessBoard[7][6] = R.drawable.chess_nlt60;
        ChessBoard[7][7] = R.drawable.chess_rlt60;

        ChessBoard[6][0] = R.drawable.chess_plt60;
        ChessBoard[6][1] = R.drawable.chess_plt60;
        ChessBoard[6][2] = R.drawable.chess_plt60;
        ChessBoard[6][3] = R.drawable.chess_plt60;
        ChessBoard[6][4] = R.drawable.chess_plt60;
        ChessBoard[6][5] = R.drawable.chess_plt60;
        ChessBoard[6][6] = R.drawable.chess_plt60;
        ChessBoard[6][7] = R.drawable.chess_plt60;

        chess.Board.createBoard();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
    }

    private String getMove(int fromCol, int fromRow, int toCol, int toRow) {
        char fromColA = '-';
        switch(fromCol) {
            case 0:
                fromColA = 'a';
                break;
            case 1:
                fromColA = 'b';
                break;
            case 2:
                fromColA = 'c';
                break;
            case 3:
                fromColA = 'd';
                break;
            case 4:
                fromColA = 'e';
                break;
            case 5:
                fromColA = 'f';
                break;
            case 6:
                fromColA = 'g';
                break;
            case 7:
                fromColA = 'h';
                break;
        }

        char toColA = '-';
        switch(toCol) {
            case 0:
                toColA = 'a';
                break;
            case 1:
                toColA = 'b';
                break;
            case 2:
                toColA = 'c';
                break;
            case 3:
                toColA = 'd';
                break;
            case 4:
                toColA = 'e';
                break;
            case 5:
                toColA = 'f';
                break;
            case 6:
                toColA = 'g';
                break;
            case 7:
                toColA = 'h';
                break;
        }

        return fromColA+""+(8-fromRow)+" "+toColA+(8-toRow);
    }

    public static boolean draw = false;

    public static boolean executeMove(String move, String playerName) {

        if (draw == true) {
            if (move.equals("draw")) {
                //System.exit(0);
                MainActivity.getInstance().printDraw();
                return true;
            } else {
               // chess.Board.printIllegalMove();
               // Log.d("error","Illegal move");
                Log.d("Illegal","1");
                MainActivity.getInstance().printIllegalMove();
                return false;
            }

        }

        if (move.trim().equals("resign")) {
            if (playerName.equals("w")) {
                //System.out.println("Black wins");
                MainActivity.getInstance().printBlackWins();
            } else {
                //System.out.println("White wins");
                MainActivity.getInstance().printWhiteWins();
            }
            //System.exit(0);
            return true;
        }

        HashMap<String, Integer> cols = new HashMap<String, Integer>();
        cols.put("a", 0);
        cols.put("b", 1);
        cols.put("c", 2);
        cols.put("d", 3);
        cols.put("e", 4);
        cols.put("f", 5);
        cols.put("g", 6);
        cols.put("h", 7);
        HashMap<String, Integer> rows = new HashMap<String, Integer>();
        rows.put("8", 0);
        rows.put("7", 1);
        rows.put("6", 2);
        rows.put("5", 3);
        rows.put("4", 4);
        rows.put("3", 5);
        rows.put("2", 6);
        rows.put("1", 7);

        //System.out.println(move.length());
        // if it may be a regular command to move piece
        if (move.length() == 5) {

            if ((int) move.substring(0, 1).charAt(0) >= 97 && (int) move.substring(0, 1).charAt(0) <= 104
                    && (int) move.substring(3, 4).charAt(0) >= 97 && (int) move.substring(3, 4).charAt(0) <= 104
                    && (int) move.substring(1, 2).charAt(0) >= 49 && (int) move.substring(1, 2).charAt(0) <= 56
                    && (int) move.substring(4, 5).charAt(0) >= 49 && (int) move.substring(4, 5).charAt(0) <= 56) {

                int originCol = cols.get(move.substring(0, 1)).intValue();
                int originRow = rows.get(move.substring(1, 2)).intValue();
                int destinationCol = cols.get(move.substring(3, 4)).intValue();
                int destinationRow = rows.get(move.substring(4, 5)).intValue();



                boolean isMoving = chess.Board.move(originCol, originRow, destinationCol, destinationRow, playerName, null);
              //  if(!isMoving) chess.Board.printIllegalMove();

                if(!isMoving)  {
                    Log.d("Illegal","2");
                    MainActivity.getInstance().printIllegalMove();
                }

                return isMoving;

            } else {
                //chess.Board.printIllegalMove();
                Log.d("Illegal","3");
                MainActivity.getInstance().printIllegalMove();
                return false;
            }
        } else
        if (move.length() > 5 && (move.substring(6).equals("N") || move.substring(6).equals("Q") || move.substring(6).equals("R") || move.substring(6).equals("B"))) {


            if ((int) move.substring(0, 1).charAt(0) >= 97 && (int) move.substring(0, 1).charAt(0) <= 104
                    && (int) move.substring(3, 4).charAt(0) >= 97 && (int) move.substring(3, 4).charAt(0) <= 104
                    && (int) move.substring(1, 2).charAt(0) >= 49 && (int) move.substring(1, 2).charAt(0) <= 56
                    && (int) move.substring(4, 5).charAt(0) >= 49 && (int) move.substring(4, 5).charAt(0) <= 56) {

                int originCol = cols.get(move.substring(0, 1)).intValue();
                int originRow = rows.get(move.substring(1, 2)).intValue();
                int destinationCol = cols.get(move.substring(3, 4)).intValue();
                int destinationRow = rows.get(move.substring(4, 5)).intValue();



                if(playerName=="w" && destinationRow==0) {
                    boolean isMoving = chess.Board.move(originCol, originRow, destinationCol, destinationRow, playerName, move.substring(6));
                    //if(!isMoving) chess.Board.printIllegalMove();
                    if(!isMoving) {
                        Log.d("Illegal","4");
                        MainActivity.getInstance().printIllegalMove();
                    }
                    return isMoving;
                }
                if(playerName=="b" && destinationRow==7) {
                    boolean isMoving = chess.Board.move(originCol, originRow, destinationCol, destinationRow, playerName, move.substring(6));
                    //if(!isMoving) chess.Board.printIllegalMove();
                    if(!isMoving)  {
                        Log.d("Illegal","5");
                        MainActivity.getInstance().printIllegalMove();
                    }
                    return isMoving;

                }
                else {
                    Log.d("Illegal","6");
                    MainActivity.getInstance().printIllegalMove();
                    //chess.Board.printIllegalMove();
                    return false;
                }


            } else {
                Log.d("Illegal","7");
                MainActivity.getInstance().printIllegalMove();
                //chess.Board.printIllegalMove();
                return false;
            }
        } else



        if (move.length() > 5 && move.substring(6).equals("draw?")) {
            draw = true;
            return draw;
        }

        else
        {
            //chess.Board.printIllegalMove();
            Log.d("Illegal","8");
            MainActivity.getInstance().printIllegalMove();
            return false;
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event == null) {
            return false;
        }
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fromCol = (int) (Math.floor(((event.getX() - originX) / squareLength)));
                fromRow = (int) (Math.floor(((event.getY() - originY) / squareLength)));
                //int selectedRow = (int)(movingPieceY - originY)/squareLength;
                //int selectedCol = (int)(movingPieceX - originX)/squareLength;
                //Log.d(TAG,"(x,y) "+ (int)(movingPieceX - originX)/squareLength + "," + (int)(movingPieceY - originY)/squareLength );

                if(fromRow < 8 && fromCol < 8)
                movingPiece = ChessBoard[fromRow][fromCol];
                break;
            case MotionEvent.ACTION_MOVE:
                movingPieceX = event.getX();
                movingPieceY = event.getY();
                invalidate();

                break;
            case MotionEvent.ACTION_UP:
                int col = (int) Math.floor((event.getX() - originX) / squareLength);
                int row = (int) Math.floor(((event.getY() - originY) / squareLength));
                //Log.d(TAG, "from (" + fromCol + ", " + fromRow + ") to (" + col + ", " + row + ")\n");

                String move = getMove(fromCol, fromRow, col, row);

                prevChessBoard = copyChessBoard(ChessBoard);

                if (isWhiteMove) {
                    if (executeMove(move, "w")) {
                        isWhiteMove = false;
                        int piece = ChessBoard[fromRow][fromCol];
                        ChessBoard[fromRow] [fromCol]= 0;
                        ChessBoard[row][col] = piece;
                        Log.d(TAG, move);
                        movingPiece = piece;
                    }
                } else {
                    if (executeMove(move, "b")) {
                        isWhiteMove = true;
                        int piece = ChessBoard[fromRow][fromCol];
                        movingPiece = piece;
                        ChessBoard[fromRow][fromCol] = 0;
                        ChessBoard[row] [col]= piece;
                        Log.d(TAG, move);
                    }
                }
                invalidate();
                movingPiece=-1;
                break;
        }
        return true;
    }

    private void drawBoard(Canvas canvas) {
        loadPieceImages();
        //draw floor
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++) {
                paint.setColor((i+j)%2==0?Color.LTGRAY:Color.DKGRAY);
                canvas.drawRect(originX+ i*squareLength,originY+ j*squareLength,originX+(i+1)*squareLength,originY+(j+1)*squareLength,paint);
            }
        drawPieces(canvas);
    }

    private void drawPieces(Canvas canvas) {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(ChessBoard[y][x]!= 0) {
                   drawPieceAt(canvas, x, y, ChessBoard[y][x]);
                }
            }
        }
        if(movingPiece!=0) {
            //Log.d(TAG,"Moving Piece: " + movingPiece);
            //Bitmap bitmap = bitmaps.get(movingPiece);

            //Log.d(TAG,"(x,y) "+ (int)(movingPieceX - originX)/squareLength + "," + (int)(movingPieceY - originY)/squareLength );

            Bitmap bitmap = bitmaps.get(movingPiece);
            if(bitmap!=null)
            canvas.drawBitmap(bitmap, null, new Rect((int)(movingPieceX - squareLength/2), (int)(movingPieceY-squareLength/2), (int)(movingPieceX + squareLength/2),(int)(movingPieceY + squareLength/2)), paint);

        }
    }


    private void drawPieceAt(Canvas canvas,  int file, int rank, int pieceKey) {
        Bitmap  piece = bitmaps.get(pieceKey);
        canvas.drawBitmap(piece, null, new Rect(originX + file*squareLength, originY+rank*squareLength, originX + (file+1)*squareLength,originY+(rank+1)*squareLength), paint);
    }

    private void loadPieceImages() {
        for (int i = 0; i < pieceKeys.length; i++) {
            bitmaps.put(pieceKeys[i], BitmapFactory.decodeResource(res, pieceKeys[i]));
        }
    }

    private int[][] copyChessBoard(int[][] matrix) {
        int [][] myInt = new int[matrix.length][];
        for(int i = 0; i < matrix.length; i++)
            myInt[i] = matrix[i].clone();

        return myInt;
    }

    public void undo() {
        ChessBoard = copyChessBoard(prevChessBoard);
        invalidate();
        isWhiteMove = !isWhiteMove;
    }
}
