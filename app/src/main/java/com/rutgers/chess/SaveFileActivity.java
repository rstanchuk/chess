package com.rutgers.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rutgers.chess.view.ChessView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

                    //Save
                    ChessView cv = findViewById(R.id.chess_view);
                    String storeDir = "data";
                    try {
                        File file = new File(storeDir + File.separator + name);
                        FileOutputStream fos = getApplicationContext().openFileOutput(storeDir + File.separator +name, Context.MODE_PRIVATE);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(cv.save);
                        oos.close();
                        fos.close();
                    } catch (IOException ioe) {
                        MainActivity.getInstance().printCorruptSave();
                    }

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