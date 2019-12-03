package com.example.planningpokeradmin;

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


public class ViewResultsFragmen extends Fragment {
        String group;
        RecyclerView recyclerView;
        TextView question;
    private ArrayList<ResultsClass> myDataset=new ArrayList<>();
    private ArrayList<QuestionsClass> questionList=new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_results, container, false);

        //get sharedpref
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        group= preferences.getString(getString(R.string.GroupName), "defaultValue");

        //set groupname
        TextView group_name;
        group_name=v.findViewById(R.id.tv_groupName);
        group_name.setText(group);

        //inicialize recycler view
        recyclerView= v.findViewById(R.id.my_recyclerViewGroups);

        question=v.findViewById(R.id.tv_quest);


        //get query
        final Context context = getActivity();
        final Query query= FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions").orderByChild("groupCode").equalTo(group);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataset.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                      //  ResultsClass results = snapshot.child("Users").getChildren()
                        QuestionsClass question = snapshot.getValue(QuestionsClass.class);
                        questionList.add(question);

                    }
                    question.setText(questionList.get(0).getQuestions());
                    for(DataSnapshot snapshot : dataSnapshot.child(questionList.get(0).getQuestions()).child("Users").getChildren()){
                        String username = snapshot.child("UserName").getValue().toString();
                        String vote = snapshot.child("Vote").getValue().toString();
                        myDataset.add(new ResultsClass(username, vote));
                    }
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
