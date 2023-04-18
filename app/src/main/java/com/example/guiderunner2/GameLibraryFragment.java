package com.example.guiderunner2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.guiderunner2.games.LegendsOfZeldaBOTW;


public class GameLibraryFragment extends Fragment {
    private ImageView LegendsOFZeldaBOTW;
    private ImageView Hades;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_library, container, false);
        init(view);
        LegendsOFZeldaBOTW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), LegendsOfZeldaBOTW.class);
                startActivity(intent);
            }
        });
        Hades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.Hades.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void init(View view){
        LegendsOFZeldaBOTW = view.findViewById(R.id.LegendsOFZeldaBOTW);
        Hades = view.findViewById(R.id.Hades);





    }


}
