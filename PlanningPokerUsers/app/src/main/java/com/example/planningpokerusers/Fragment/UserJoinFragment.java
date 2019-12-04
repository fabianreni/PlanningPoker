package com.example.planningpokerusers.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planningpokerusers.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserJoinFragment extends Fragment {
    Button bt_join;
    EditText joinCode;
    EditText name,gname;
    SharedPreferences sharedpreferences;
    DatabaseReference databaseReference;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_user_join, container, false);

        //get edittext value tu join the group
        joinCode=v.findViewById(R.id.et_groupName);

        //sharePreferences
        name=v.findViewById(R.id.et_userName);
        gname=v.findViewById(R.id.et_groupName);
        sharedpreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());



        bt_join=v.findViewById(R.id.bt_join);
        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String n=name.getText().toString();
                final String gn=gname.getText().toString();

                if (!TextUtils.isEmpty(n)) {
                    databaseReference = FirebaseDatabase.getInstance().getReference();

                    databaseReference.child("planningpoker").child("Groups").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    if(snapshot.getKey().toString().equals(gn)){
                                        databaseReference.child("planningpoker").child("Groups").child(gn).child("Users").child(n).setValue(n);

                                        //pute value in sharedpreferences
                                        SharedPreferences.Editor editor=sharedpreferences.edit();
                                        editor.putString(getActivity().getString(R.string.Name),n);
                                        editor.putString(getActivity().getString(R.string.groupName),gn);
                                        editor.commit();
                                        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                                        fragmentTransaction.replace(R.id.mainActivity, new QuestionsRespunseSubmitFragment());
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.commit();
                                        Toast.makeText(getActivity(),"Saved!",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }
                                Toast.makeText(getActivity(),"group not exists!",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });



        return v;
    }


}
