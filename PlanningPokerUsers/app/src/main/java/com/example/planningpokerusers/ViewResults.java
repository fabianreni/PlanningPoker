package com.example.planningpokerusers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewResults extends Fragment {
    String groupn, username;
    TextView question;
    private ArrayList<ResultsClass> myDataset=new ArrayList<>();
    private ArrayList<Question> questionList=new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_view_results, container, false);

       //get sharedpref
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        groupn = preferences.getString(getString(R.string.groupName), "defaultValue");

        //set groupname
        TextView group_name;
        group_name=v.findViewById(R.id.tv_groupName);
        group_name.setText(groupn);

        //inicialize recycler view
        recyclerView= v.findViewById(R.id.my_recyclerViewGroups);

        question=v.findViewById(R.id.tv_quest);


        //get query
        final Context context = getActivity();
        final Query query=FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions").orderByChild("groupCode").equalTo(groupn);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              myDataset.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ResultsClass results = snapshot.getValue(ResultsClass.class);
                        Question question = snapshot.getValue(Question.class);
                        questionList.add(question);
                        myDataset.add(results);
                    }
                    question.setText(questionList.get(0).getQuestions());

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(new MyAdapterResults(context, myDataset));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }



}
