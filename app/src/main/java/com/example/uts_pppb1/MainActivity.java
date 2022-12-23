
package com.example.uts_pppb1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingAction_btn;
    private String tanggalLahir;
    public String kategoriTeks;
    private TextView kategori;
    String key, user_tag, user_email;
    int usia;

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    public ArrayList<News> news = new ArrayList<>(); //menyimpan semua berita
    public ArrayList<News> NewsFilter = new ArrayList<>();

    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //nambah data berita
//        addData();

        newsAdapter = new NewsAdapter(this, news);
        kategori = findViewById(R.id.kategori);
        recyclerView.setAdapter(newsAdapter);
        floatingAction_btn = findViewById(R.id.floatingActionBtn);

        //get intent dari detail
        Intent intent = getIntent();
        tanggalLahir = intent.getStringExtra("tanggalLahir");
        kategoriTeks = intent.getStringExtra("kategoriTeks");

        //menampilkan data
        showData();
        recyclerView = findViewById(R.id.news_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //menghitung usia
        String tanggalLahirUser[] = tanggalLahir.split("-");
        int usia = 2022-Integer.parseInt(tanggalLahirUser[2]);
        Toast.makeText(this, String.valueOf(usia), Toast.LENGTH_SHORT).show();

        //filter berita
        for (News dataContent : news){
            Integer minUsia = Integer.valueOf(dataContent.getMinUsia());
            if(minUsia <= usia && dataContent.getKategori().toLowerCase().equals(kategoriTeks.toLowerCase())){
                NewsFilter.add(dataContent);
            }
        }
        kategori.setText(kategoriTeks);

        floatingAction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addNews.class);
                startActivity(intent);
            }
        });
    }

    private void showData() {
        mDatabaseReference.child("News").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    key = item.getKey();
                    News item_news = item.getValue(News.class);
                    news.add(item_news);
                }
                for(News a: news) {
                    if(a.getKategori().equals(user_tag) && Integer.valueOf(a.getMinUsia()) <= usia) {
                        findViewById(R.id.none).setVisibility(View.GONE);
                        NewsFilter.add(a);
                    }
                    else{
                        TextView info = findViewById(R.id.none);
                        info.setText("There is no news that suits you");
                    }
                }
                newsAdapter = new NewsAdapter(MainActivity.this, NewsFilter);
                recyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //menambahkan data
//    private void addData(){
//        news.add(new News("Ini Judul1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "17", "Politics"));
//        news.add(new News("Ini Judul2", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "17", "Educations"));
//        news.add(new News("Ini Judul3", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "17", "Sports"));
//    }

}