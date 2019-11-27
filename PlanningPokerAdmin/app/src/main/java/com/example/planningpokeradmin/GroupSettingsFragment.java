package com.example.planningpokeradmin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class GroupSettingsFragment extends Fragment {
    private RecyclerView recyclerView;
    Button bt_add;
    String group;

    private ArrayList<QuestionsClass> myDataset;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_group_settings, container, false);

        bt_add=v.findViewById(R.id.bt_questions);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainActivity, new AddQuestionsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        myDataset=new ArrayList<>();

        recyclerView= v.findViewById(R.id.my_recyclerViewQuestions);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        group= preferences.getString(getString(R.string.GroupName), "defaultValue");
        TextView group_name;
        group_name=v.findViewById(R.id.tv_Groupname);
        group_name.setText(group);
        final Context context = getActivity();
        final Query query=FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions").orderByChild("groupCode").equalTo(group);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataset.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        QuestionsClass question = snapshot.getValue(QuestionsClass.class);
                        myDataset.add(question);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                           recyclerView.setAdapter(new MyAdapterQuestionSwitch(context, myDataset));
                    //questionList.notify();
                    //tv_question.setText(questionList.get(0).getQuestions());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions").addListenerForSingleValueEvent(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren())
//                {
//                    QuestionsClass groupClass = snapshot.getValue(QuestionsClass.class);
//                }
//                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                recyclerView.setAdapter(new MyAdapterGroup(context, myDataset));
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        });

        return v;

    }

}


