package com.rutgers.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rutgers.chess.view.ChessView;

public class MainActivity extends AppCompatActivity {

    ChessView chessView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //chessView = new ChessView(this);
        //setContentView(chessView);
    }

}