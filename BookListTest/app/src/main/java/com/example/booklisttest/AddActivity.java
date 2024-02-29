package com.example.booklisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText input_title, input_author, input_pages;
    Button add_book_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        input_title = findViewById(R.id.input_title);
        input_author = findViewById(R.id.input_author);
        input_pages = findViewById(R.id.input_pages);
        add_book_btn = findViewById(R.id.add_book_btn);

        add_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper myDB = new dbHelper(AddActivity.this);
                myDB.addBook(input_title.getText().toString().trim(),
                            input_author.getText().toString().trim(),
                            Integer.parseInt(input_pages.getText().toString().trim()));
            }
        });

    }
}