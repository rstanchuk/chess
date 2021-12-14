package com.rutgers.chess;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.rutgers.chess.view.ChessView;

public class ListSavesActivity extends AppCompatActivity {
    private final String TAG = "ListSavesActivity";
    Button exit;
    ListView fileList ;

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_saves);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,mobileArray);
        ListView fileList = findViewById(R.id.file_list);
        fileList.setAdapter(adapter);

        exit = findViewById(R.id.exit_button);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   ChessView cv = findViewById(R.id.chess_view);
             //   Intent intent = new Intent(MainActivity.getInstance(), ChessView.class);
             //   startActivity(intent);
            }
        });
    }
}

