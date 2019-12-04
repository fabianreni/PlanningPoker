package com.example.planningpokeradmin.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokeradmin.Class.GroupClass;
import com.example.planningpokeradmin.Fragment.GroupSettingsFragment;
import com.example.planningpokeradmin.Fragment.ViewResultsFragmen;
import com.example.planningpokeradmin.MainActivity;
import com.example.planningpokeradmin.R;

import java.util.ArrayList;

public class MyAdapterGroup extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<GroupClass> item;
    public class Item extends RecyclerView.ViewHolder {
    TextView textView;
    Button settings, results;


    public Item(View itemView) {


        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_groupName);
        settings = (Button) itemView.findViewById(R.id.bt_settings);
        results = (Button) itemView.findViewById(R.id.bt_result);

    }

}


    public MyAdapterGroup(Context context, ArrayList<GroupClass> item){
        this.context=context;
        this.item=item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View group=inflater.inflate(R.layout.groups,parent,false);
        MyAdapterGroup.Item item=new MyAdapterGroup.Item(group);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((Item)holder).textView.setText(item.get(position).getGroupName());

        //a fooldal 2 gombja fragment atvitel
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
        ((Item)holder).results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor=sharedpreferences.edit();

                editor.putString(context.getString(R.string.GroupName),item.get(position).getGroupName());
                editor.commit();
                FragmentTransaction fragmentTransaction=((MainActivity) context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainActivity, new ViewResultsFragmen());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }


}