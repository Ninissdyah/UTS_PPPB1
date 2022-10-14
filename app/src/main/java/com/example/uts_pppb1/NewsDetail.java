package com.example.uts_pppb1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewsDetail extends AppCompatActivity {
    TextView JudulBerita, IsiBerita;
    String judul, konten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        JudulBerita = findViewById(R.id.judulBerita);
        IsiBerita = findViewById(R.id.isiBerita);

        //get intent dr newsAdapter
        Intent intent = getIntent();
        judul = intent.getStringExtra("judul");
        konten = intent.getStringExtra("konten");

        //me-set teks dari news detail
        JudulBerita.setText(judul);
        IsiBerita.setText(konten);

    }
}