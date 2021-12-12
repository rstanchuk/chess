package com.rutgers.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rutgers.chess.view.ChessView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    ChessView chessView;
    private static MainActivity instance;

    private Button undoButton;
    private Button aiButton;
    private Button drawButton;
    private Button resignButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //chessView = new ChessView(this);
        //setContentView(chessView);
        instance = this;

        undoButton = findViewById(R.id.undo_button);
        undoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "hello1");
            }
        });

        aiButton = findViewById(R.id.ai_button);
        aiButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "hello2");
            }
        });

        drawButton = findViewById(R.id.draw_button);
        drawButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "hello3");
            }
        });

        resignButton = findViewById(R.id.resign_button);
        resignButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChessView cv  = findViewById(R.id.chess_view);
                Log.d(TAG, cv.isWhiteMove+"");
                if(cv.isWhiteMove) {
                    cv.executeMove("resign", "w");
                } else {
                    cv.executeMove("resign", "b");
                }

            }
        });
    }
    public static MainActivity getInstance() {
        return instance;
    }
    public void printIllegalMove() {
        Context context = getApplicationContext();
        CharSequence text = "Illegal move, try again!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printWhiteWins() {
        Context context = getApplicationContext();
        CharSequence text = "White wins";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printBlackWins() {
        Context context = getApplicationContext();
        CharSequence text = "Black wins";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}