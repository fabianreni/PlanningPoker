package com.example.planningpokeradmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.planningpokeradmin.Fragment.GroupFragment;

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



}

