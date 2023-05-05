package com.example.sts_registration_test;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TicketFragment extends Fragment {


    Button btn1,btn2,btn3;



    public TicketFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ticket, container, false);
        btn1 = rootView.findViewById(R.id.button1);
        btn2 = rootView.findViewById(R.id.button2);
        btn3 = rootView.findViewById(R.id.button3);
        return rootView;
    }
}