package com.example.planningpokeradmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterQuestions extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
             Context context;
             ArrayList<QuestionsClass> item;
        public class Item extends RecyclerView.ViewHolder {
        TextView et_questions;


        public Item(View itemView) {
            super(itemView);
            et_questions = (TextView) itemView.findViewById(R.id.addedQuestions);

        }
    }

public MyAdapterQuestions(Context context,ArrayList<QuestionsClass> item){
        this.context=context;
        this.item=item;
        }

@Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        LayoutInflater inflater=LayoutInflater.from(context);
        View questions=inflater.inflate(R.layout.questionadd,parent,false);
        MyAdapterQuestions.Item item=new MyAdapterQuestions.Item(questions);
        return item;
        }

@Override
public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
        ((MyAdapterQuestions.Item)holder).et_questions.setText(item.get(position).getQuestions());
        }

@Override
public int getItemCount(){
        return item.size();
        }



    }

