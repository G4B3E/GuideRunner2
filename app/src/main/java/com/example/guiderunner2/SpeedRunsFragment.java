package com.example.guiderunner2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpeedRunsFragment extends Fragment {

    private ListView list_view_library;

    public String URL = "http://10.0.2.2:3000/records";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speed_runs, container, false);
        init(view);
        return view;
    }
    public void init(View view) {
        list_view_library = view.findViewById(R.id.list_view_library);
    }

}