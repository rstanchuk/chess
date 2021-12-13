package com.rutgers.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SaveFileActivity extends AppCompatActivity {
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
                if(editFileName.getText().length()>0)
                {
                    System.out.println(editFileName.getText());
                }
            }
        });
    }
}