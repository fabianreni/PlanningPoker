package com.example.planningpokeradmin;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterGroup extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
   ArrayList<GroupClass> item;

    public MyAdapterGroup(Context context, ArrayList<GroupClass> item){
        this.context=context;
        this.item=item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View group=inflater.inflate(R.layout.groups,parent,false);
        Item item= new Item(group);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((Item)holder).textView.setText(item.get(position).getGroupName());
        ((Item)holder).settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor=sharedpreferences.edit();

                editor.putString(context.getString(R.string.GroupName),item.get(position).getGroupName());
                editor.commit();
                FragmentTransaction fragmentTransaction=((MainActivity) context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainActivity, new GroupSettingsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
    public class Item extends  RecyclerView.ViewHolder{
        TextView textView;
        Button settings;
        public Item( View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.tv_groupName);
            settings=(Button) itemView.findViewById(R.id.bt_settings);
        }
    }

}