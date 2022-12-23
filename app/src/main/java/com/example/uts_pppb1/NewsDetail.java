package com.example.uts_pppb1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewsDetail extends AppCompatActivity {
    TextView JudulBerita, IsiBerita;
    String judul, konten, key;

    DatabaseReference mDatabaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        JudulBerita = findViewById(R.id.judulBerita);
        IsiBerita = findViewById(R.id.isiBerita);

        //get intent dr newsAdapter
        Intent intent = getIntent();
        judul = intent.getStringExtra("judul");
        konten = intent.getStringExtra("konten");


        //me-set teks dari news detail
        JudulBerita.setText(judul);
        IsiBerita.setText(konten);

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), updateNews.class);
                intent.putExtra("TITLE", judul);
                intent.putExtra("KONTEN", konten);

                startActivity(intent);
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
                Intent intent = new Intent(getApplicationContext(), detailDataUser.class);
                startActivity(intent);
            }
        });
    }

    private void deleteData() {
        mDatabaseReference.child("News").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    if((item.getValue(News.class)).getJudul().equals(judul)) {
                        key = item.getKey();
                        mDatabaseReference.child(key).removeValue();
                        Toast.makeText(getApplicationContext(), "Update Succesfully!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}