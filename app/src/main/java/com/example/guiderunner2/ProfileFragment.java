package com.example.guiderunner2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ProfileFragment extends Fragment {

    private Button AddSpeedRun;
    private Button Records;
    private Button AboutUs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        AddSpeedRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),SubmitSpeedRun.class);
                startActivity(intent);

            }
        });
        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),AboutUs.class);
                startActivity(intent);

            }
        });
        return view;
    }
    public void init(View view){
        AddSpeedRun = view.findViewById(R.id.AddSpeedRun);
        Records = view.findViewById(R.id.Records);
        AboutUs = view.findViewById(R.id.AboutUs);
    }
}