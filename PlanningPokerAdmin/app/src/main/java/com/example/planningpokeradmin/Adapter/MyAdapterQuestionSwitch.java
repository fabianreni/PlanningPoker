package com.example.planningpokeradmin.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.planningpokeradmin.Class.QuestionsClass;
import com.example.planningpokeradmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapterQuestionSwitch extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    ArrayList<QuestionsClass> item;
    public class Item extends RecyclerView.ViewHolder {
        Switch questions;


        public Item(View itemView) {
            super(itemView);
            questions = (Switch) itemView.findViewById(R.id.questionswitch);

        }
    }

    public MyAdapterQuestionSwitch(Context context,ArrayList<QuestionsClass> item){
        this.context=context;
        this.item=item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(context);
        View questions=inflater.inflate(R.layout.questions,parent,false);
        MyAdapterQuestionSwitch.Item item=new MyAdapterQuestionSwitch.Item(questions);
        return item;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position){
        ((Item)holder).questions.setText(item.get(position).getQuestions());

        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions").child(item.get(position).getQuestions());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String status = dataSnapshot.child("Status").getValue().toString();
                if (status.equals("Active")) {
                    ((Item)holder).questions.setChecked(true);
                    //bealitja a switch erteket ha az igaz a viewban
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        ((Item)holder).questions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //atintasra elenorzi s aktival egy egy kerdest
                final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("planningpoker").child("Questions");
                if(((Item)holder).questions.isChecked()){
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)
                        {
                            SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
                            String groupn = sharedpreferences.getString(context.getString(R.string.GroupName), "defaultValue");
                            for (DataSnapshot snapshot : dataSnapshot.getChildren())
                            {
                                String status = snapshot.child("Status").getValue().toString();
                                String groupCode = snapshot.child("groupCode").getValue().toString();
                                if(status.equals("Active") && groupCode.equals(groupn)){
                                    Toast.makeText(context,"A question is already active!",Toast.LENGTH_LONG).show();
                                    ((Item)holder).questions.setChecked(false);
                                    return;
                                }
                            }
                            databaseReference.child(item.get(position).getQuestions()).child("Status").setValue("Active");
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });
                }
                else{
                    databaseReference.child(item.get(position).getQuestions()).child("Status").setValue("Inactive");
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return item.size();
    }



}

