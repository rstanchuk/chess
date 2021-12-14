package com.rutgers.chess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.rutgers.chess.view.ChessView;

public class ListSavesActivity extends AppCompatActivity {
    private final String TAG = "ListSavesActivity";
    Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_saves);

        exit = findViewById(R.id.exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.getInstance(), ChessView.class);
                startActivity(intent);
            }
        });
    }
}

