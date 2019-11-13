package com.example.planningpokeradmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
        {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.mainactivity, new GroupFragment()).commit();
        }

    }
}
