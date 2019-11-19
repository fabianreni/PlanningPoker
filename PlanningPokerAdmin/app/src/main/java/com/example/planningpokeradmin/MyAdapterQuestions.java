package com.example.planningpokeradmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterQuestions extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        Context context;
        ArrayList<QuestionsClass> item;

public MyAdapterQuestions(Context context,ArrayList<QuestionsClass> item){
        this.context=context;
        this.item=item;
        }

@Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        LayoutInflater inflater=LayoutInflater.from(context);
        View group=inflater.inflate(R.layout.groups,parent,false);
        MyAdapterQuestions.Item item=new MyAdapterQuestions.Item(group);
        return item;
        }

@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
        ((MyAdapterGroup.Item)holder).textView.setText(item.get(position).getQuestions());
        }

@Override
public int getItemCount(){
        return item.size();
        }

public class Item extends RecyclerView.ViewHolder {
    TextView textView;

    public Item(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_groupName);
    }
}

    }

