package com.example.guiderunner2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.guiderunner2.games.LegendsOfZeldaBOTW;


public class GameLibraryFragment extends Fragment {
    private ImageView LegendsOFZeldaBOTW;
    private ImageView Hades;
    private ImageView Undertale;
    private ImageView Kotor;
    private ImageView KotorII;
    private ImageView ResidentEvilIIre;
    private ImageView HalfLife;
    private ImageView Minecraft;
    private ImageView Cuphead;
    private ImageView JumpKing;
    private ImageView HollowKnight;

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
        Undertale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.Undertale.class);
                startActivity(intent);
            }
        });
        Kotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.Kotor.class);
                startActivity(intent);
            }
        });
        KotorII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.KotorII.class);
                startActivity(intent);
            }
        });
        ResidentEvilIIre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.ResidentEvilIIRE.class);
                startActivity(intent);
            }
        });
        HalfLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.HalfLife.class);
                startActivity(intent);
            }
        });
        Minecraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.Minecraft.class);
                startActivity(intent);
            }
        });
        Cuphead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.CupHead.class);
                startActivity(intent);
            }
        });
        JumpKing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.JumpKing.class);
                startActivity(intent);
            }
        });
        HollowKnight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), com.example.guiderunner2.games.HollowKnight.class);
                startActivity(intent);
            }
        });


        return view;
    }


    private void init(View view) {
        LegendsOFZeldaBOTW = view.findViewById(R.id.LegendsOFZeldaBOTW);
        Hades = view.findViewById(R.id.Hades);
        Undertale = view.findViewById(R.id.Undertale);
        KotorII = view.findViewById(R.id.KotorII);
        Kotor = view.findViewById(R.id.Kotor);
        ResidentEvilIIre = view.findViewById(R.id.ResidentEvilIIre);
        HalfLife = view.findViewById(R.id.HalfLife);
        Minecraft = view.findViewById(R.id.Minecraft);
        Cuphead = view.findViewById(R.id.Cuphead);
        JumpKing = view.findViewById(R.id.JumpKing);
        HollowKnight = view.findViewById(R.id.HollowKnight);


    }


}
