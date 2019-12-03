package com.example.planningpokeradmin;

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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//kerdes hozza adas a group setingsbe katintva jon elo
public class AddQuestionsFragment extends Fragment {
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    Button addQuestions;
    EditText question;
    EditText groupId;
    private ArrayList<QuestionsClass> myDataset;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_questions, container, false);
        //database connect
        databaseReference= FirebaseDatabase.getInstance().getReference("planningpoker");
        question=v.findViewById(R.id.questions);
        groupId=v.findViewById(R.id.et_groupid);
        //button inicializalas
        addQuestions=v.findViewById(R.id.bt_addQuestions);
        //   recycler view initialization
        recyclerView= v.findViewById(R.id.recyclerViewQuestions);
        addQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sQestion = question.getText().toString();
                String sGroupId=groupId.getText().toString();
                if (!TextUtils.isEmpty(sQestion)) {

                    //QuestionsClass qcQuestion = new QuestionsClass(sQestion,sGroupId);
                    //set questions in database
                    Map<String, String> questioninf=new HashMap<>();
                    questioninf.put("questions",sQestion);
                    questioninf.put("groupCode",sGroupId);
                    questioninf.put("Status","Inactive");
                    databaseReference.child("Questions").child(sQestion).setValue(questioninf);
                    myDataset=new ArrayList<>();
                    FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions").addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)
                        {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren())
                            {
                                myDataset.add(snapshot.getValue(QuestionsClass.class));
                            }
                            //recycler inicializer/feltoltes
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(new MyAdapterQuestions(getActivity(), myDataset));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });

                }
            }
        });


        return v;
    }
}
