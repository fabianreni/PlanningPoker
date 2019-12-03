package com.example.planningpokerusers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QuestionsRespunseSubmitFragment extends Fragment {
    Button b1,b2,b3,b4,b5,b6,b7;
    TextView tv_question;
    String groupn, username;
    String active_question="";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_questions_respunse_submit, container, false);

        //get text view to set the question;
        tv_question=v.findViewById(R.id.tv_question);

        //get SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        groupn = preferences.getString(getString(R.string.groupName), "defaultValue");
        username = preferences.getString(getString(R.string.Name), "defaultValue");

        //set the question
        final Query query=FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions").orderByChild("groupCode").equalTo(groupn);
        //bealit kerdest ha nincs aktiv akor nem
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Question question = snapshot.getValue(Question.class);
                        String status = snapshot.child("Status").getValue().toString();
                        if(status.equals("Active")){
                            active_question = question.getQuestions();
                        }
                    }
                    if(active_question.equals("")){
                        tv_question.setText("No active questions");
                        return;
                    }
                    tv_question.setText(active_question);

                    // Inflate the layout for this fragment
                    b1=v.findViewById(R.id.bt_1);
                    b1.setBackgroundResource(R.drawable.assz1);
                    b2= v.findViewById(R.id.bt_2);
                    b2.setBackgroundResource(R.drawable.kettes);
                    b3= v.findViewById(R.id.bt_3);
                    b3.setBackgroundResource(R.drawable.harom);
                    b4= v.findViewById(R.id.bt_4);
                    b4.setBackgroundResource(R.drawable.negy);
                    b5= v.findViewById(R.id.bt_5);
                    b5.setBackgroundResource(R.drawable.ot);
                    b6= v.findViewById(R.id.bt_6);
                    b6.setBackgroundResource(R.drawable.nyolc);
                    b7= v.findViewById(R.id.bt_7);
                    b7.setBackgroundResource(R.drawable.tiz);

                    //onclicklistener for vots
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendResponse(b1);
                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendResponse(b2);
                        }
                    });
                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendResponse(b3);
                        }
                    });
                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendResponse(b4);
                        }
                    });
                    b5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendResponse(b5);
                        }
                    });
                    b6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendResponse(b6);
                        }
                    });
                    b7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendResponse(b7);
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

    //vote submit end send to database
    public void sendResponse(Button b){
        String vote = b.getContentDescription().toString();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Map<String, String> userinf=new HashMap<>();
        userinf.put("UserName",username);
        userinf.put("Vote",vote);
        databaseReference.child("planningpoker").child("Questions").child(tv_question.getText().toString()).child("Users").child(username).setValue(userinf);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainActivity, new ViewResults());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
