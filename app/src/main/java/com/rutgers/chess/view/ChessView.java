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
import android.view.View;

import androidx.annotation.Nullable;

import com.rutgers.chess.R;

import java.util.HashMap;


public class ChessView extends View {
    private static final int originX= 25; //origin position x
    private static final int originY = 100; //origin position y
    private static final int squareLength= 90; //length of the square
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

    public ChessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //start pieces

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);

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
       //black pieces
        drawPieceAt(canvas,0,0, R.drawable.chess_rdt60);
        drawPieceAt(canvas,1,0, R.drawable.chess_ndt60);
        drawPieceAt(canvas,2,0, R.drawable.chess_bdt60);
        drawPieceAt(canvas,3,0, R.drawable.chess_qdt60);
        drawPieceAt(canvas,4,0, R.drawable.chess_kdt60);
        drawPieceAt(canvas,5,0, R.drawable.chess_bdt60);
        drawPieceAt(canvas,6,0, R.drawable.chess_ndt60);
        drawPieceAt(canvas,7,0, R.drawable.chess_rdt60);

        drawPieceAt(canvas,0,1, R.drawable.chess_pdt60);
        drawPieceAt(canvas,1,1, R.drawable.chess_pdt60);
        drawPieceAt(canvas,2,1, R.drawable.chess_pdt60);
        drawPieceAt(canvas,3,1, R.drawable.chess_pdt60);
        drawPieceAt(canvas,4,1, R.drawable.chess_pdt60);
        drawPieceAt(canvas,5,1, R.drawable.chess_pdt60);
        drawPieceAt(canvas,6,1, R.drawable.chess_pdt60);
        drawPieceAt(canvas,7,1, R.drawable.chess_pdt60);

        //white pieces

        drawPieceAt(canvas,0,7, R.drawable.chess_rlt60);
        drawPieceAt(canvas,1,7, R.drawable.chess_nlt60);
        drawPieceAt(canvas,2,7, R.drawable.chess_blt60);
        drawPieceAt(canvas,3,7, R.drawable.chess_qlt60);
        drawPieceAt(canvas,4,7, R.drawable.chess_klt60);
        drawPieceAt(canvas,5,7, R.drawable.chess_blt60);
        drawPieceAt(canvas,6,7, R.drawable.chess_nlt60);
        drawPieceAt(canvas,7,7, R.drawable.chess_rlt60);

        drawPieceAt(canvas,0,6, R.drawable.chess_plt60);
        drawPieceAt(canvas,1,6, R.drawable.chess_plt60);
        drawPieceAt(canvas,2,6, R.drawable.chess_plt60);
        drawPieceAt(canvas,3,6, R.drawable.chess_plt60);
        drawPieceAt(canvas,4,6, R.drawable.chess_plt60);
        drawPieceAt(canvas,5,6, R.drawable.chess_plt60);
        drawPieceAt(canvas,6,6, R.drawable.chess_plt60);
        drawPieceAt(canvas,7,6, R.drawable.chess_plt60);

    }

    private void drawPieceAt(Canvas canvas,  int file,int rank, int pieceKey) {
        Bitmap  piece = bitmaps.get(pieceKey);
        canvas.drawBitmap(piece, null, new Rect(originX + file*squareLength, originY+rank*squareLength, originX + (file+1)*squareLength,originY+(rank+1)*squareLength), paint);
    }

    private void loadPieceImages() {
        for(int i=0;i<pieceKeys.length;i++) {
            bitmaps.put(pieceKeys[i],BitmapFactory.decodeResource(res, pieceKeys[i]));
        }
    }


}
