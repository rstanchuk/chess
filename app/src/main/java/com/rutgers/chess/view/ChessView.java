package com.rutgers.chess.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ChessView extends View {
    private static final int originX= 25; //origin position x
    private static final int originY = 100; //origin position y
    private static final int squareLength= 90; //length of the square

    public ChessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void createBoard(Canvas canvas) {
        Paint paint = new Paint();
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++) {
                paint.setColor((i%2==0)?(j%2==0)?Color.LTGRAY:Color.DKGRAY:(j%2==0)?Color.DKGRAY:Color.LTGRAY);
                canvas.drawRect(originX+ i*squareLength,originY+ j*squareLength,originX+(i+1)*squareLength,originY+(j+1)*squareLength,paint);
            }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        createBoard(canvas);

    }
}
