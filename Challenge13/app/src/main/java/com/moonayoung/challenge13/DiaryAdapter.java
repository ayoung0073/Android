package com.moonayoung.challenge13;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    EditText list_date_item;
    EditText list_content_item;

    ArrayList<Diary> diarys=new ArrayList<Diary>();


    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            list_date_item=itemView.findViewById(R.id.list_date_item);
            list_content_item=itemView.findViewById(R.id.list_content_item);

        }
        public void setItem(Diary item){
            list_date_item.setText("작성 시간: "+item.getTimeStamp());
            list_content_item.setText(item.getContent());

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext()); //이거 PersonAdapter 보러 감
        View itemView=inflater.inflate(R.layout.diary_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Diary item=diarys.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return diarys.size();
    }

    public void addItem(Diary diary){
        diarys.add(diary);
    }
}
