package com.moonayoung.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    //adapter에는 데이터가 들어가있어야함
    ArrayList<Person> items=new ArrayList<Person>();

    OnPersonItemClickListener listener;



    public void addItem(Person item){
        items.add(item);
    }

    public void setItems(ArrayList<Person> items){ //ArrayList전체를 설정할 수 있는 함수
        this.items=items;
    }

    public Person getItem(int position){
        return items.get(position);
    }

    public void setItem(int position,Person item){
        items.set(position,item);
    }

    public void setOnItemClickListener(OnPersonItemClickListener listener){ //아이템뷰에 onClickListener 설정하기
        this.listener=listener;
    }

    @NonNull
    //onCreateViewHolder()와 onBindViewHolder()메서드는 뷰홀더 객체가 만들어질 때와 재사용될 때 자동 호출 됨.
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //뷰홀더가 새로 만들어질 때 그 안에서 각 아이템을 위해 정의한 XML레이아웃을 이용해 뷰 객체를 만들어줌.
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        //getSystemService에서 참조하는 것과 동일한 코드
        View itemView=inflater.inflate(R.layout.person_item,parent,false);

        return new ViewHolder(itemView,listener);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //뷰홀더가 재사용될 때 호출되므로 뷰 객체는 기존 것을 그대로 사용하고 데이터만 바꿔줌.
        Person item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2; //하나의 레이아웃 안에 두개의 텍스트뷰를 넣겠다.

        public ViewHolder(final View itemView, final OnPersonItemClickListener listener){
            super(itemView);

            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                    if(listener!=null){
                            listener.onItemClick(ViewHolder.this,view,position);
                    }
                }
            });
        }

        public void setItem(Person item){
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }


    }
}
