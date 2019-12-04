package com.example.planningpokeradmin.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.planningpokeradmin.Class.GroupClass;
import com.example.planningpokeradmin.Adapter.MyAdapterGroup;
import com.example.planningpokeradmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//fo oldal
public class GroupFragment extends Fragment {
    Button addGroup;
    EditText grupName;
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private ArrayList<GroupClass> myDataset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_group, container, false);

        databaseReference= FirebaseDatabase.getInstance().getReference("planningpoker");

        grupName=v.findViewById(R.id.et_groupName);
        addGroup=v.findViewById(R.id.bt_add);

        recyclerView= v.findViewById(R.id.my_recyclerViewGroups);

        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = grupName.getText().toString();
                if (!TextUtils.isEmpty(groupName)) {
                    Map<String, String> groupinf=new HashMap<>();
                    groupinf.put("groupName",groupName);

                  databaseReference.child("Groups").child(groupName).setValue(groupinf);
                }
            }
        });

        //set recyclerView;
        myDataset=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("planningpoker").child("Groups").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    myDataset.add(snapshot.getValue(GroupClass.class));
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new MyAdapterGroup(getActivity(), myDataset));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        return v;
    }

}
