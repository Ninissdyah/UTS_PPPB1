package com.example.uts_pppb1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final ArrayList<News> values; //menampung data rv
    private final LayoutInflater inflater; //ngeet view nya

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    //constructor adapter
    public NewsAdapter(Context context, ArrayList<News> values) {
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //menampilkan rv
        View view = inflater.inflate(R.layout.recycle_view_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        //mengambil data" dari news sesuai posisi
        final News news = values.get(position);

        //menampilkan add data yg di main di rv
        holder.NewsTitle.setText(news.getJudul());
        holder.NewsContent.setText(news.getIsiBerita());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), NewsDetail.class);
                intent.putExtra("Judul", news.getJudul());
                intent.putExtra("isiBerita", news.getIsiBerita());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    //inisiasi yg ada di rv xml
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView NewsTitle;
        TextView NewsContent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NewsTitle = itemView.findViewById(R.id.newsTitle);
            NewsContent = itemView.findViewById(R.id.newsContent);
        }
    }


}
