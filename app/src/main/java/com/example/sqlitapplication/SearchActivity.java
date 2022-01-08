package com.example.sqlitapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
private static  final String TAG = SearchActivity.class.getSimpleName();
private TextView mTextView;
private EditText mEditWordView;
private DBHandler mDB;
private Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mEditWordView = (EditText) findViewById(R.id.search_resulted);
        mTextView = ((TextView) findViewById(R.id.action_search));
        mDB = new DBHandler(this);
        search = findViewById(R.id.button_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SearchActivity.this, "Hello Search Activity", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showResulted(View view){
        String word = mEditWordView.getText().toString();
        mTextView.setText("Resulted for " + word + ": \n\n");
        // Search for the word in the database.
        Cursor cursor = mDB.search(word);
        // Only process a non-null cursor with rows.
        if (cursor != null & cursor.getCount() > 0) {
            // You must move the cursor to the first item.
            cursor.moveToFirst();
            int index;
            String result;
            // Iterate over the cursor, while there are entries.
            do {
                // Don't guess at the column index.
                // Get the index for the named column.
                index = cursor.getColumnIndex(mDB.getDatabaseName());
                // Get the value from the column for the current cursor.
                result = cursor.getString(index);
                // Add result to what's already in the text view.
                mTextView.append(result + "\n");
            } while (cursor.moveToNext()); // Returns true or false
            cursor.close();
        } // You should add some handling of null case. Right now, nothing happens.
    }
    }
