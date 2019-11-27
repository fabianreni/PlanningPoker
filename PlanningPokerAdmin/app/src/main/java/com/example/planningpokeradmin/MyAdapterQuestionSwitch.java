package com.example.planningpokeradmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterQuestionSwitch extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    ArrayList<QuestionsClass> item;
    public class Item extends RecyclerView.ViewHolder {
        Switch questions;


        public Item(View itemView) {
            super(itemView);
            questions = (Switch) itemView.findViewById(R.id.questionswitch);

        }
    }

    public MyAdapterQuestionSwitch(Context context,ArrayList<QuestionsClass> item){
        this.context=context;
        this.item=item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(context);
        View questions=inflater.inflate(R.layout.questions,parent,false);
        MyAdapterQuestionSwitch.Item item=new MyAdapterQuestionSwitch.Item(questions);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
        ((Item)holder).questions.setText(item.get(position).getQuestions());
    }

    @Override
    public int getItemCount(){
        return item.size();
    }



}

