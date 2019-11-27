package com.example.planningpokerusers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
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


public class UserJoinFragment extends Fragment {
    Button bt_join;
    EditText joinCode;
    EditText name,gname;
    SharedPreferences sharedpreferences;
    DatabaseReference databaseReference;
    private ArrayList<UserClass> myDataset;
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
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainActivity, new QuestionsRespunseSubmitFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                String n=name.getText().toString();
                String gn=gname.getText().toString();

                if (!TextUtils.isEmpty(n)) {
                    //String id=databaseReference.push().getKey();
                    //Toast.makeText(getActivity(),"Saving!",Toast.LENGTH_LONG).show();
                    UserClass nUser = new UserClass(n);
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("planningpoker").child("Groups").child(gn).child("Users").setValue(nUser);

                }
                myDataset=new ArrayList<>();
            //    loadData();
                SharedPreferences.Editor editor=sharedpreferences.edit();

                editor.putString(getActivity().getString(R.string.Name),n);
                editor.putString(getActivity().getString(R.string.groupName),gn);
                editor.commit();
                Toast.makeText(getActivity(),"Saved!",Toast.LENGTH_LONG).show();
            }
        });



        return v;
    }

//    public void loadData()
//    {
//        FirebaseDatabase.getInstance().getReference("planningpoker").child("Groups").addListenerForSingleValueEvent(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren())
//                {
//                    myDataset.add(snapshot.getValue(UserClass.class));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        });
//    }

}
