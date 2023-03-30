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
    private Button Settings;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        AddSpeedRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),AddSpeedRun.class);
                startActivity(intent);

            }
        });
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Settings.class);
                startActivity(intent);

            }
        });
        return view;
    }
    public void init(View view){
        AddSpeedRun = view.findViewById(R.id.AddSpeedRun);
        Records = view.findViewById(R.id.Records);
        Settings = view.findViewById(R.id.Settings);
    }
}