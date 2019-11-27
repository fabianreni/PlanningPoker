package com.example.planningpokeradmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

//    Button addGroup;
//    EditText grupName;
//    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set fragment
        if (savedInstanceState == null)
        {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.mainActivity, new GroupFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }
//    public void settingsGroup(View v){
//
//    }

    public  void qouestionsAdd(View v){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainActivity, new AddQuestionsFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}

