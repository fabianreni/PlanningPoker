package com.example.planningpokerusers.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.planningpokerusers.Class.Question;
import com.example.planningpokerusers.Class.ResultsClass;
import com.example.planningpokerusers.Adapter.MyAdapterResults;
import com.example.planningpokerusers.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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
                if (dataSnapshot.exists()) {
                    String active_question="";
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Question q = snapshot.getValue(Question.class);
                        String status = snapshot.child("Status").getValue().toString();
                        if(status.equals("Active")){
                            active_question = q.getQuestions();
                            question.setText(q.getQuestions());
                            break;
                        }
                    }

                    for(DataSnapshot snapshot : dataSnapshot.child(active_question).child("Users").getChildren()){
                        //ResultsClass results = snapshot.getValue(ResultsClass.class);
                        String username = snapshot.child("UserName").getValue().toString();
                        String vote = snapshot.child("Vote").getValue().toString();
                        myDataset.add(new ResultsClass(username, vote));
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(new MyAdapterResults(context, myDataset));

                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions").child(active_question);
                    dbRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("Status").getValue().toString().equals("Inactive")) {

                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.mainActivity, new QuestionsRespunseSubmitFragment());
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return v;
    }



}
