package com.umang_rathod.hms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

public class Fragment1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Fragment1() {}

    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentHolder = inflater.inflate(R.layout.fragment_1, container, false);

        //Fragment management
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        // Hooks
        TextView greet = parentHolder.findViewById(R.id.greet),
                name = parentHolder.findViewById(R.id.name),
                quote = parentHolder.findViewById(R.id.quote);
        LinearLayout takeAppointment = parentHolder.findViewById(R.id.take_appointment);



        int currentHour = new Date().getHours();
        String greeting = currentHour < 12 ? "Good Morning," : currentHour < 18 ? "Good Afternoon," : "Good Evening,";

        greet.setText(greeting);
        name.setText("Umang Rathod!");
        quote.setText(R.string.quote1);

        takeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction.replace(R.id.frameLayout, new Fragment2());
                fragmentTransaction.commit();
            }
        });




        return parentHolder;
    }
}