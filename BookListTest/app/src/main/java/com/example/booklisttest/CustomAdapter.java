package com.example.booklisttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList books_id, books_title, books_author, books_pages;

    CustomAdapter(Activity activity, Context context, ArrayList books_id, ArrayList books_title, ArrayList books_author, ArrayList books_pages){
        this.activity = activity;
        this.context = context;
        this.books_id = books_id;
        this.books_title = books_title;
        this.books_author = books_author;
        this.books_pages = books_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder,final int position) {

        holder.card_book_id.setText(String.valueOf(books_id.get(position)));
        holder.card_book_title.setText(String.valueOf(books_title.get(position)));
        holder.card_book_author.setText(String.valueOf(books_author.get(position)));
        holder.card_book_pages.setText(String.valueOf(books_pages.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("books_id", String.valueOf(books_id.get(position)));
                intent.putExtra("books_title", String.valueOf(books_title.get(position)));
                intent.putExtra("books_author", String.valueOf(books_author.get(position)));
                intent.putExtra("books_pages", String.valueOf(books_pages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView card_book_id, card_book_title, card_book_author, card_book_pages;

        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card_book_id = itemView.findViewById(R.id.card_book_id);
            card_book_title = itemView.findViewById(R.id.card_book_title);
            card_book_author = itemView.findViewById(R.id.card_book_author);
            card_book_pages = itemView.findViewById(R.id.card_book_pages);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
