package com.example.uts_pppb1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addNews extends AppCompatActivity {
    EditText inputJudul, inputKonten, inputUmur, inputKategori;
    Button addNewsBtn;
    DatabaseReference mDatabaseReference;
    News mNews;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference(News.class.getSimpleName());
        inputJudul = findViewById(R.id.newsTitleInput);
        inputKonten = findViewById(R.id.newsContentInput);
        inputUmur = findViewById(R.id.minUsiaInput);
        inputKategori = findViewById(R.id.newsCategoryInput);
        addNewsBtn = findViewById(R.id.btn_addnews);

        addNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewsData();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }

        });
    }

    private void addNewsData(){
        String newsTitle = inputJudul.getText().toString();
        String newsContent = inputKonten.getText().toString();
        String minUsiaInput = inputUmur.getText().toString();
        String newsCatergory = inputKategori.getText().toString();

        News mNews = new News();
        if(newsTitle != "" && newsContent != "" && minUsiaInput != "" && newsCatergory != ""){
            mNews.setJudul(newsTitle);
            mNews.setIsiBerita(newsContent);
            mNews.setMinUsia(minUsiaInput);
            mNews.setKategori(newsCatergory);

            mDatabaseReference.push().setValue(mNews);
            Toast.makeText(this, "Succesfully add your data!", Toast.LENGTH_SHORT).show();
        }

    }
}