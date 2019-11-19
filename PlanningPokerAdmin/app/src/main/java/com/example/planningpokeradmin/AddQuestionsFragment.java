package com.example.planningpokeradmin;

import android.content.Context;
import android.net.Uri;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AddQuestionsFragment extends Fragment {
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    Button addQuestions;
    EditText question;
    private ArrayList<QuestionsClass> myDataset;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_add_questions, container, false);
        // Inflate the layout for this fragment
        databaseReference= FirebaseDatabase.getInstance().getReference("planningpoker");
        question=v.findViewById(R.id.et_questions);
        addQuestions=v.findViewById(R.id.bt_addQuestion);
        addQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sQestion = question.getText().toString();
                if (!TextUtils.isEmpty(sQestion)) {
                    //String id=databaseReference.push().getKey();

                    QuestionsClass qcQuestion = new QuestionsClass(sQestion);
                    databaseReference.child("Questions").push().setValue(qcQuestion);

                }
            }
        });
        //recycler view initialization
        myDataset=new ArrayList<>();
        loadData();
        recyclerView= v.findViewById(R.id.recyclerViewQuestions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapterQuestions(this.getActivity(), myDataset));

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
                    myDataset.add(snapshot.getValue(QuestionsClass.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
