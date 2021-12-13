package com.rutgers.chess;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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
                ChessView cv  = findViewById(R.id.chess_view);
                cv.undo();
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
                AlertDialog builder = new AlertDialog
                        .Builder(MainActivity.this)
                        .setTitle("Draw?")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ChessView cv  = findViewById(R.id.chess_view);
                                cv.draw = true;
                                if(cv.isWhiteMove) {
                                    cv.executeMove("draw", "w");
                                } else {
                                    cv.executeMove("draw", "b");
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        resignButton = findViewById(R.id.resign_button);
        resignButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChessView cv  = findViewById(R.id.chess_view);
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

    public void printDraw() {
        Context context = getApplicationContext();
        CharSequence text = "draw";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}