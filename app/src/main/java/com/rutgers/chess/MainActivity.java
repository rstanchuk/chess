package com.rutgers.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.rutgers.chess.view.ChessView;

public class MainActivity extends AppCompatActivity {

    ChessView chessView;
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //chessView = new ChessView(this);
        //setContentView(chessView);
        instance = this;
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



}