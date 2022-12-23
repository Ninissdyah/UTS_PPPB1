package com.example.uts_pppb1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateNews extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String Spinner, newsTitle, category, content, key;
    int minUsia;
    EditText inputTitle, inputContent, inputMinUsia, inputCategory;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_news);

        inputTitle = findViewById(R.id.newsTitleInput_update);
        inputContent = findViewById(R.id.newsContentInput_update);
        inputMinUsia = findViewById(R.id.minUsiaInput_update);
        inputCategory = findViewById(R.id.newsCategoryInput_update);

        Spinner spinners = findViewById(R.id.label_spinner);
        //menciptakan array adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.label_array, android.R.layout.simple_spinner_item);
        //menampilkan adapter ke spinner
        spinners.setAdapter(adapter);
        spinners.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        newsTitle = intent.getStringExtra("TITLE");
        content = intent.getStringExtra("KONTEN");
        inputCategory.setText(category);
        inputMinUsia.setText(minUsia);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
                Intent intent = new Intent(view.getContext(), detailDataUser.class);
                startActivity(intent);
            }
        });
    }

    private void updateData() {
        News updateNews = new News();

        mDatabaseReference.child("News").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item : snapshot.getChildren()) {
                    if((item.getValue(News.class)).getJudul().equals(newsTitle)){
                        key = item.getKey();
                        updateNews.setJudul(inputTitle.getText().toString());
                        updateNews.setIsiBerita(inputContent.getText().toString());
                        updateNews.setKategori(inputCategory.getText().toString());
                        updateNews.setMinUsia(inputMinUsia.getText().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Spinner = adapterView.getItemAtPosition(0).toString();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}