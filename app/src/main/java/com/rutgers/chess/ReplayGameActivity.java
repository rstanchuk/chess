package com.rutgers.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rutgers.chess.Util.ChessMove;
import com.rutgers.chess.view.ChessView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReplayGameActivity extends AppCompatActivity {
    private final String TAG = "ReplayGameActivity";
    public String fileName;
    private static ReplayGameActivity instance;
    Button exit;
    Button next;
    ArrayList<ChessMove> moves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_game);
        instance = this;

        fileName = getIntent().getExtras().getString("fileName");
        Log.d(TAG, fileName);

        try {
            FileInputStream fis = new FileInputStream(getApplicationContext().getFilesDir() + "/" + fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            moves = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        }  catch (IOException ioe) {
            MainActivity.getInstance().printCorruptSave();
        }
        catch (ClassNotFoundException c)  {
            MainActivity.getInstance().printCorruptSave();
        }

        exit = findViewById(R.id.exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChessView cv = ChessView.getInstance();
                cv.reset();
                Intent intent = new Intent(MainActivity.getInstance(), MainActivity.class);
                startActivity(intent);
            }
        });

        next = findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeChessMove();
            }
        });
    }

    public static ReplayGameActivity getInstance() {
        return instance;
    }

    private int moveIndex = 0;
    private void executeChessMove() {
        if(moveIndex >= moves.size()) {
            return;
        }
        ChessMove move = moves.get(moveIndex);
        ChessView cv = ChessView.getInstance();
        if(move.resign){
            Log.d(TAG, move.player + " resign");
        } else if(move.draw) {
            Log.d(TAG, move.player + " draw");
        } else {
            Log.d(TAG, move.player + " " + cv.getMove(move.fromCol, move.fromRow, move.toCol, move.toRow));
            int piece = cv.ChessBoard[move.fromRow][move.fromCol];
            cv.ChessBoard[move.fromRow][move.fromCol] = 0;
            cv.ChessBoard[move.toRow][move.toCol] = piece;
            cv.invalidate();
        }

        moveIndex++;
    }
}