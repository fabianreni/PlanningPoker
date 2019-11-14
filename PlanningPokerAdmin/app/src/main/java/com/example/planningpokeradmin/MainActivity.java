package com.example.planningpokeradmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
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
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.mainactivity, new GroupFragment()).commit();
        }
        //set group names end save to database
//        databaseReference= FirebaseDatabase.getInstance().getReference("planningpoker");
//        grupName=(EditText)findViewById(R.id.et_groupName);
//        addGroup=(Button)findViewById(R.id.bt_add);
//        addGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String groupName = grupName.getText().toString();
//                if (!TextUtils.isEmpty(groupName)) {
//                    //String id=databaseReference.push().getKey();
//
//                    GroupClass group = new GroupClass(groupName);
//                    databaseReference.child("Groups").setValue(group);
//
//                }
//            }
//        });


//        set recyclerView
//        recyclerView= findViewById(R.id.my_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MyAdapter(this, myDataset));



    }
}

