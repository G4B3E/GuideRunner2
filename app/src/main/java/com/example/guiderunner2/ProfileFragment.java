package com.example.guiderunner2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class ProfileFragment extends Fragment {

    private Button AddSpeedRun;
    private Button AboutUs;

    private Button ToLogOut;
    private Button ToDeleteAccount;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        AddSpeedRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean containsToken = isTokenInXmlFile();
                if(containsToken){
                    Intent intent = new Intent(getActivity().getApplicationContext(), SubmitSpeedRun.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "Please create an account or login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity().getApplicationContext(), OpenScreenMenu.class);
                    startActivity(intent);
                }



            }
            public boolean isTokenInXmlFile() {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Important", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", null);

                return token != null;
            }
        });
        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AboutUs.class);
                startActivity(intent);

            }
        });
        ToLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), LogOutConfirmation.class);
                startActivity(intent);
            }
        });
        ToDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DeleteAccountConfirmation.class);
                startActivity(intent);
            }
        });



        return view;
    }

    public void init(View view) {
        AddSpeedRun = view.findViewById(R.id.AddSpeedRun);
        AboutUs = view.findViewById(R.id.AboutUs);
        ToLogOut = view.findViewById(R.id.ToLogOut);
        ToDeleteAccount = view.findViewById(R.id.ToDeleteAccount);
    }

}