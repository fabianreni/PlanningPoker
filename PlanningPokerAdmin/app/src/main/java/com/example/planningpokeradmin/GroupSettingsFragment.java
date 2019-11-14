package com.example.planningpokeradmin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class GroupSettingsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<GroupClass> myDataset;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_group, container, false);

        myDataset=new ArrayList<>();
        loadData();
        recyclerView= v.findViewById(R.id.my_recyclerViewGroups);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(this.getActivity(), myDataset));


        return v;
    }

    public void loadData()
    {
        FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    myDataset.add(snapshot.getValue(GroupClass.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}