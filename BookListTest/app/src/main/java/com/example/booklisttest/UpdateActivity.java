package com.example.booklisttest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText update_title, update_author, update_pages;
    Button update_book_btn, delete_book_btn;

    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update_title = findViewById(R.id.update_title);
        update_author = findViewById(R.id.update_author);
        update_pages = findViewById(R.id.update_pages);
        update_book_btn = findViewById(R.id.update_book_btn);
        delete_book_btn = findViewById(R.id.delete_book_btn);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper myDB = new dbHelper(UpdateActivity.this);
                title = update_title.getText().toString().trim();
                author = update_author.getText().toString().trim();
                pages = update_pages.getText().toString().trim();
                myDB.updateData(id, title, author, pages);
            }
        });
        delete_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("books_id") && getIntent().hasExtra("books_title") &&
                getIntent().hasExtra("books_author") && getIntent().hasExtra("books_pages")){
            id = getIntent().getStringExtra("books_id");
            title = getIntent().getStringExtra("books_title");
            author = getIntent().getStringExtra("books_author");
            pages = getIntent().getStringExtra("books_pages");

            update_title.setText(title);
            update_author.setText(author);
            update_pages.setText(pages);
            Log.d("stev", title+" "+author+" "+pages);
        }
        else Toast.makeText(this, "No data!!", Toast.LENGTH_LONG).show();
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper myDB = new dbHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}