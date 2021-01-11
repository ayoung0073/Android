package com.moonayoung.volley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    ArrayList<Movie> items=new ArrayList<Movie>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.movie_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie item=items.get(position);
        holder.setItem(item); //View에 보이게 하기
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Movie item){
        items.add(item);
    }

    public void setItems(ArrayList<Movie> items){
        this.items=items;
    }

    public Movie getItem(int position){
        return items.get(position); //Movie 객체 추가
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView2;
        TextView textView3;

        public ViewHolder(View itemView){
            super(itemView);

            textView2=itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);
        }

        public void setItem(Movie item){
            textView2.setText(item.movieNm);
            textView3.setText(item.audiCnt+"명");
        }
    }
}
