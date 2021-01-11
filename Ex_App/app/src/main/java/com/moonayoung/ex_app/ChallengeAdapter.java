package com.moonayoung.ex_app;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder>{
    ArrayList<Challenge> items = new ArrayList<Challenge>();

    public void addItem(Challenge item){
        items.add(item);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItem(Challenge item){
        items.add(item);
    }

    public void setItems(ArrayList<Challenge> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_challenge,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Challenge item = items.get(position);
        holder.setItem(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
        public void setItem(Challenge item){
            textView.setText("Challenge" + item.getChallenge());
            textView2.setText(item.getchallenge_title());
        }
    }

}
