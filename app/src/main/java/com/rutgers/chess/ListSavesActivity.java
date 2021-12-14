package com.rutgers.chess;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.rutgers.chess.view.ChessView;

import java.io.File;

public class ListSavesActivity extends AppCompatActivity {
    private final String TAG = "ListSavesActivity";
    Button exit;
    ListView fileList ;

    String[] mobileArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_saves);

        File[] files = new File(getApplicationContext().getFilesDir().toString()).listFiles();
        mobileArray = new String[files.length];
        for(int i = 0; i < files.length; i++) {
            mobileArray[i] = files[i].getName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,mobileArray);
        ListView fileList = findViewById(R.id.file_list);
        fileList.setAdapter(adapter);


        fileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                String fileName = (String)adapter.getItem(pos);
                Intent intent = new Intent(MainActivity.getInstance(), ReplayGameActivity.class);
                startActivity(intent);
            }
        });

        exit = findViewById(R.id.exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.getInstance(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

