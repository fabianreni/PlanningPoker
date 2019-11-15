package com.example.planningpokerusers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class QuestionsRespunseSubmitFragment extends Fragment {
    CardView card1,card2,card3,card4,card5,card6,card7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_questions_respunse_submit, container, false);

       card1 = v.findViewById(R.id.cv_nr1);
        card1.setBackgroundResource(R.drawable.assz1);
       card2= v.findViewById(R.id.cv_nr2);
        card2.setBackgroundResource(R.drawable.kettes);
        card3= v.findViewById(R.id.cv_nr3);
        card3.setBackgroundResource(R.drawable.harom);
        card4= v.findViewById(R.id.cv_nr4);
        card4.setBackgroundResource(R.drawable.negy);
        card5= v.findViewById(R.id.cv_nr5);
        card5.setBackgroundResource(R.drawable.ot);
        card6= v.findViewById(R.id.cv_nr6);
        card6.setBackgroundResource(R.drawable.nyolc);
        card7= v.findViewById(R.id.cv_nr7);
        card7.setBackgroundResource(R.drawable.tiz);
        return v;
    }

}
