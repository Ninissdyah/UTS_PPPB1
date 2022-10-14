
package com.example.uts_pppb1;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String tanggalLahir;
    public String kategoriTeks;
    private TextView kategori;

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    public ArrayList<News> news = new ArrayList<>();
    public ArrayList<News> NewsFilter = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nambah data berita
        addData();

        newsAdapter = new NewsAdapter(this, news);
        recyclerView = findViewById(R.id.news_rv);
        kategori = findViewById(R.id.kategori);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newsAdapter);

        //get intent dari detail
        Intent intent = getIntent();
        tanggalLahir = intent.getStringExtra("tanggalLahir");
        kategoriTeks = intent.getStringExtra("kategoriTeks");

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

    }

    //menambahkan data
    private void addData(){
        news.add(new News("Ini Judul1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "17", "Politics"));
        news.add(new News("Ini Judul2", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "17", "Educations"));
        news.add(new News("Ini Judul3", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "17", "Sports"));
    }

}