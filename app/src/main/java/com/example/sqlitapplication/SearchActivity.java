package com.example.sqlitapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    private static final String KEY_WORD
            = null;
    private Button searchButton;
DBHandler mDB;
private TextView search;
private EditText enterText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchButton = findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //we should call showResulted class here, but isn't work
            }

        });
search = ((TextView) findViewById(R.id.mysearch));
enterText = ((EditText) findViewById(R.id.search_resulted));
}
public void showResulted( ){
        String[] columns = new String[]{KEY_WORD};
        String word = enterText.getText().toString();
        search.setText("Resulted for "+ word + ":\n\n");
        Cursor cursor = mDB.search(word);
        if(cursor != null && cursor.getCount()>0 ){
            cursor.moveToFirst();
            int index;
            String resulted;
            do{

                index = cursor.getColumnIndex("ID_COLUMN");
                resulted = cursor.getString(index);
                enterText.append(resulted + "\n");


            }while (cursor.moveToNext());
            cursor.close();
        }




}
}
