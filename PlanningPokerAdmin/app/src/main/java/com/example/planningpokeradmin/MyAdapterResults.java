package com.example.planningpokeradmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterResults extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        Context context;
        ArrayList<ResultsClass> item;
        public class Item extends RecyclerView.ViewHolder {
            TextView result;


            public Item(View itemView) {
                super(itemView);
                result = (TextView) itemView.findViewById(R.id.tv_results);

            }
        }

        public MyAdapterResults(Context context,ArrayList<ResultsClass> item){
            this.context=context;
            this.item=item;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater inflater=LayoutInflater.from(context);
            View questions=inflater.inflate(R.layout.results,parent,false);
            MyAdapterResults.Item item=new MyAdapterResults.Item(questions);
            return item;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
            ((Item)holder).result.setText((item.get(position).getName()+":"+item.get(position).getVote()));
        }

        @Override
        public int getItemCount(){
            return item.size();
        }



    }

