package com.rutgers.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rutgers.chess.Util.Util;
import com.rutgers.chess.view.ChessView;

public class SaveFileActivity extends AppCompatActivity {
    private final String TAG = "SaveFileActivity";
    Button saveButton;
    Button cancelButton;
    EditText editFileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_file);

        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        editFileName = findViewById(R.id.editTextFileName);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editFileName.getText().length()>0) {
                    String name = editFileName.getText().toString().trim()+".dat";
                    Log.d(TAG, name);

                    //Save
                    ChessView cv = findViewById(R.id.chess_view);
                    Util.writeSave(cv.save, name);

                    cv.reset();
                    Intent intent = new Intent(MainActivity.getInstance(), ChessView.class);
                    startActivity(intent);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Name can't be empty";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChessView cv = findViewById(R.id.chess_view);
                cv.reset();
                Intent intent = new Intent(MainActivity.getInstance(), ChessView.class);
                startActivity(intent);
            }
        });
    }
}