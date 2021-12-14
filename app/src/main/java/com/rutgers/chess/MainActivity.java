package com.rutgers.chess;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rutgers.chess.Util.ChessMove;
import com.rutgers.chess.view.ChessView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    ChessView chessView;
    private static MainActivity instance;

    public Button undoButton;
    private Button aiButton;
    private Button drawButton;
    private Button resignButton;
    private Button playBackButton;

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
        undoButton.setEnabled(false);

        aiButton = findViewById(R.id.ai_button);
        aiButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChessView cv  = findViewById(R.id.chess_view);
                cv.executeAImove();
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
                                ChessView inst = ChessView.getInstance();
                                ArrayList<ChessMove> save = inst.getSave();

                                cv.draw = true;
                                if(cv.isWhiteMove) {
                                    cv.executeMove("draw", "w");
                                    ChessMove cm = new ChessMove("w");
                                    cm.setDraw();
                                    inst.addToSave(cm);

                                } else {
                                    cv.executeMove("draw", "b");
                                    ChessMove cm = new ChessMove("b");
                                    cm.setDraw();
                                    inst.addToSave(cm);
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
                ChessView inst = ChessView.getInstance();
                ArrayList<ChessMove> save = inst.getSave();

                if(cv.isWhiteMove) {
                    cv.executeMove("resign", "w");
                    ChessMove cm = new ChessMove("w");
                    cm.setResign();
                    inst.addToSave(cm);
                } else {
                    cv.executeMove("resign", "b");
                    ChessMove cm = new ChessMove("b");
                    cm.setResign();
                    inst.addToSave(cm);
                }
            }
        });

        playBackButton = findViewById(R.id.playback_button);
        playBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openListSavesWindow();
            }
        });

        if (shouldAskPermissions()) {
            askPermissions();
        }
    }

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
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
        CharSequence text = "White wins!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printBlackWins() {
        Context context = getApplicationContext();
        CharSequence text = "Black wins!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printDraw() {
        Context context = getApplicationContext();
        CharSequence text = "Draw!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printNoMove() {
        Context context = getApplicationContext();
        CharSequence text = "Can't find any move!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printCheck() {
        Context context = getApplicationContext();
        CharSequence text = "Check!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printCheckmate() {
        Context context = getApplicationContext();
        CharSequence text = "Checkmate!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printCorruptSave() {
        Context context = getApplicationContext();
        CharSequence text = "Corrupt save!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printFileAlreadyExists() {
        Context context = getApplicationContext();
        CharSequence text = "Such file already exists!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printResign() {
        Context context = getApplicationContext();
        CharSequence text = "Resign!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void openSaveFileWindow() {
        Intent intent = new Intent(this, SaveFileActivity.class);
        startActivity(intent);
    }

    public void openListSavesWindow() {
        Intent intent = new Intent(this, ListSavesActivity.class);
        startActivity(intent);
    }

}