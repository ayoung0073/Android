package com.moonayoung.challenge13;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    //Adapter에서 item 관리함

    ArrayList<Person> customers=new ArrayList<Person>();
    OnPersonItemClickListener listener;


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textNum;
        TextView textMobile;

        public ViewHolder(@NonNull View itemView, final OnPersonItemClickListener listener) {
            super(itemView);
            textNum=itemView.findViewById(R.id.textNum);
            textMobile=itemView.findViewById(R.id.textMobile);


/*            itemView.setOnClickListener(new View.OnClickListener() {
                //customerClickListener이 아닌 ClickListener
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                    if(listener!=null){
                        listener.onItemClick(ViewHolder.this,view,position);
                    }
                }
            });*/
        }

        public void setItem(Person item){
            textNum.setText(item.getName());
            textMobile.setText(item.getMobile());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //new ViewHolder객체를 생성할 때
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());


        View itemView=inflater.inflate(R.layout.person_item,parent,false);

        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item=customers.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return customers.size(); }

    public void addItem(Person item) {
        customers.add(item);
    }

    public void setItems(ArrayList<Person> customers){
        this.customers=customers;
    }

    public void setItem(Person item, int position){
        customers.set(position,item);
    }

    public void setOnItemClickListener(OnPersonItemClickListener listener){
        this.listener=listener;
    }
}
